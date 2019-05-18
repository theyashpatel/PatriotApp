package ypdp.filter;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ypdp.dao.repository.SessionRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationFilter implements Filter {

    @Autowired
    private SessionRepository sessionRepository;

    private static final List<String> NO_TOKEN_APIS = Arrays.asList(
            "/login", "/courses/list");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = ((HttpServletRequest) servletRequest);
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURI();
        if (isTokenRequired(path)) {
            String token = httpServletRequest.getHeader("Authorization");
            if (token == null || !isTokenValid(token)) {
                writeErrorResponse(res, new Error("BAD_TOKEN"), 401);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isTokenValid(String token) {
        return sessionRepository.existsById(token);
    }

    private void writeErrorResponse(HttpServletResponse r, Error e, int statusCode) throws IOException {
        String errorJson = new Gson().toJson(e);
        r.setStatus(statusCode);
        r.getWriter().write(errorJson);
    }

    private boolean isTokenRequired(String path) {
        for (String api: NO_TOKEN_APIS) {
            if (path.equals(api)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void destroy() {

    }
}
