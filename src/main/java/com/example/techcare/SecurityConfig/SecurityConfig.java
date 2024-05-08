package com.example.techcare.SecurityConfig;

import com.example.techcare.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/category/get-all", "/api/v1/technician/register", "/api/v1/customer/register",

                        "/api/v1/trainer/register","/api/v1/services/get-by-type/**", "/api/v1/technician/service-technician/**",

                        "/api/v1/technician/filter-by-name/**", "/api/v1/technician/top-rated", "/api/v1/technician/get-trainer-level/", "/api/v1/services/get-by-hours/**",

                        "/api/v1/technician/filter-by-experience/**", "/api/v1/services/get-all","/api/v1/product/get-all","/api/v1/product/get-product/{productId}", "/api/v1/services/get-by-price/**","/api/v1/services/get-by-type/**",
                        "/api/v1/services/get-by-category/**","/api/v1/product/get-product-category/{category}","/api/v1/product/filter-price/{minPrice}/{maxPrice}","/api/v1/product/top-rated").permitAll()


                .requestMatchers("/api/v1/technician/update", "/api/v1/technician/get-profile","/api/v1/services/add-trainer-service",

                        "/api/v1/services/update/**", "/api/v1/services/add-customer-service", "/api/v1/services/delete/**", "/api/v1/request/get-by-status/**",

                        "/api/v1/request/tech-get-request",  "/api/v1/request/set-status/{status}/{requestId}", "/api/v1/request/change-customer-request-status/{reqId}",

                        "/api/v1/request/change-trainer-request-status/{reqId}", "/api/v1/services/get-tech-services").hasAuthority("TECHNICIAN")


                .requestMatchers("/api/v1/auth/get-all","/api/v1/technician/get-all", "/api/v1/customer/get-all",

                        "/api/v1/category/add", "/api/v1/trainer/get-all", "/api/v1/category/update/**",

                        "/api/v1/category/delete/**", "/api/v1/order/get-all", "api/v1/customer/delete/**",

                        "/api/v1/trainer/delete/**", "/api/v1/technician/delete/**", "/api/v1/order/update-status/**",

                        "/api/v1/order/delete/**", "/api/v1/order/update/**", "/api/v1/order/get-all",

                        "/api/v1/request/delete/{request_num}","/api/v1/product/add-product/**","/api/v1/product/update-product/{productId}/{catId}",

                        "/api/v1/product/delete-product/{productId}").hasAuthority("ADMIN")


                .requestMatchers("/api/v1/customer/rate-tech/**", "/api/v1/customer/get-profile", "/api/v1/order/create",

                        "/api/v1/order/get-my-orders","/api/v1/order/get-order-status/{status}", "/api/v1/order/get-order-status/{date}","/api/v1/customer/update", "/api/v1/request/customer-get-my-request",

                        "/api/v1/request/customer-send-request/{serviceId}","/api/v1/request/delete/{request_num}","/api/v1/request/customer-rate-request/{reqId}/{rate}",

                        "/api/v1/request/customer-request-status/{status}","/api/v1/product/rate-product/**").hasAuthority("CUSTOMER")


                .requestMatchers("/api/v1/trainer/get-profile", "/api/v1/trainer/update/**", "/api/v1/request/trainer-send-request/{serviceId}",

                        "/api/v1/trainer/rate-tech/**","/api/v1/request/trainer-get-request","/api/v1/request/trainer-rate-request/{reqId}/{rate}").hasAuthority("TRAINER")

                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
