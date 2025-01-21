package com.itheima.Filter;

import com.itheima.Util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/login")
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing JwtFilter...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从请求头中获取 Authorization
        String token = httpRequest.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            log.warn("缺少 Authorization Header");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Missing Authorization Token");
            return;
        }

        // 验证 JWT 令牌
        if (!JwtUtils.validateToken(token)) {
            log.warn("无效的 JWT 令牌");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Invalid Authorization Token");
            return;
        }

        // 解析 JWT 令牌中的角色信息
        Claims claims = JwtUtils.getClaimsFromToken(token);
        String role = (String) claims.get("role");
        String requestURI = httpRequest.getRequestURI();

        // 简单权限控制
        if ("/admin".equals(requestURI) && !"admin".equals(role)) {
            log.warn("权限不足：用户角色为 {}, 尝试访问 {}", role, requestURI);
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Access Denied: Insufficient Permissions");
            return;
        }

        // JWT 验证通过，继续请求
        log.info("JWT 验证成功，用户角色：{}", role);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("Destroying JwtFilter...");
    }
}
