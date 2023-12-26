/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




/**
 *
 * @author aleks
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Autowired
    private Filter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable().authorizeRequests().
//                requestMatchers(null).permitAll().// WHITE LIST
//                anyRequest().authenticated().and().
//                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authenticationProvider(authenticationProvider)
//                .addFilter(authenticationFilter);
        httpSecurity
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(
                        (authorise)-> authorise
                                .requestMatchers("/user/**")//white list mora ovo
//                                .hasAnyAuthority(authorities)
                                .hasAuthority("USER")
                                .anyRequest().authenticated()
                                )
                .sessionManagement((mgmt)-> mgmt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
        
        return httpSecurity.build();
    }

}
