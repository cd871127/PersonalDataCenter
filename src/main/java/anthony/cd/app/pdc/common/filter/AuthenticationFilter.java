package anthony.cd.app.pdc.common.filter;

import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TokenManager tokenManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        //注册和登陆页面直接lu撸
        if (("POST".equals(method) && uri.contains("/users/info")) || ("POST".equals(method) && uri.contains("/users/registry")) ||
                ("GET".equals(method) && uri.contains("/security/rsaPublicKey"))) {
            chain.doFilter(request, response);
        } else {
            String token = httpServletRequest.getHeader("token");
            if (token != null) {
                //验证token是否存在
                String userName = stringRedisTemplate.opsForValue().get("TOKEN." + token);
                if (userName != null) {
                    //延长token过期时间
                    tokenManager.resetTokenExpire(token);
                    logger.debug("user:" + userName);
                    logger.debug("uri:" + uri);
                    chain.doFilter(request, response);
                } else {
                    logger.debug("invalid token");
                    RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/error/" + SystemConst.RequestResult.INVALID_TOKEN);
                    requestDispatcher.forward(request, response);
                }
            } else {
                //没有token 返回客户端没有登陆
                logger.debug("found no token");
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/error/" + SystemConst.RequestResult.NO_TOKEN);
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
