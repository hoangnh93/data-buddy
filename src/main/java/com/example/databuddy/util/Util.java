package com.example.databuddy.util;

import com.example.databuddy.domain.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

    public static UserDetails getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principle = authentication.getPrincipal();

            if (principle instanceof UserDetails) {
                return (UserDetails) authentication.getPrincipal();
            }
        }

        return null;
    }
}
