package anthony.cd.app.pdc.common.filter;


import anthony.cd.app.pdc.common.util.TokenManager;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "tokenExpireFilter", urlPatterns = "/*")
public class TokenExpireFilter implements Filter {

    @Resource
    private TokenManager tokenManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("token");
        if (token != null)
            tokenManager.resetTokenExpire(token);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
