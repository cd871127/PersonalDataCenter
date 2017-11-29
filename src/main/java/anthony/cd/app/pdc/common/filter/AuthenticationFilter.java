package anthony.cd.app.pdc.common.filter;

import anthony.cd.app.pdc.common.util.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "authenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        redisTemplate.opsForValue().get("token");
        //注册和登陆页面直接lu撸
        if (("GET".equals(method) || "POST".equals(method)) && uri.contains("/users/")) {
            chain.doFilter(request, response);

        } else {

            String token = httpServletRequest.getHeader("token");
            if (token == null) {
                //没有token 返回客户端没有登陆
                logger.debug("found no token");
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/error/" + SystemConst.RequestResult.NO_TOKEN);
                requestDispatcher.forward(request, response);
            } else {
                //todo 验证token
                logger.debug("token: " + token);
                redisTemplate.opsForValue().get("token");
                chain.doFilter(request, response);

            }
        }

    }

    @Override
    public void destroy() {

    }
}
