package com.example.databuddy.config;

import com.example.databuddy.domain.UserDetails;
import com.example.databuddy.exception.ErrorResponse;
import com.example.databuddy.util.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private List<UserDetails> userDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(Constant.HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(Constant.HEADER_AUTHORIZATION_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication;
        try {
            UserDetails user = getUser(header);

            if (user == null) {
                writeErrorToResponse(res);
                return;
            }

            authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetails(req));

            SecurityContextHolder.getContext().setAuthentication((Authentication) authentication);

        } catch (ParseException e) {
            writeErrorToResponse(res);
            return;
        }

        chain.doFilter(req, res);
    }

    private UserDetails getUser(String token) throws ParseException {
        token = token.replace("Bearer", "");
        String userId = JWTParser.parse(token).getJWTClaimsSet().getClaim("sub").toString();
        return userDetails.stream().filter(x -> x.getUserId().equals(userId)).findFirst().orElse(null);
    }

    private void writeErrorToResponse(HttpServletResponse httpResponse) throws JsonProcessingException, IOException {
        ObjectMapper objMapper = new ObjectMapper();

        ErrorResponse resBody = new ErrorResponse(Constant.UNAUTHORIZED, messageProperties.getMessage("UNAUTHORIZED"));

        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.addHeader("Content_Type", "application/json");
        httpResponse.getWriter().print(objMapper.writeValueAsString(resBody));
    }
}
