package com.ingenio.logisticIngenio.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**"
    };
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Deshabilitar CSRF (cross site request forgery)
        http.csrf().disable();

        // Spring Security no creará ni utilizará ninguna sesión.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/users/signin").permitAll()//
                .antMatchers("/users/signup").permitAll()//
                .antMatchers("/customers/register").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                // Disallow everything else..
                .anyRequest().authenticated();

        // Si un usuario intenta acceder a un recurso sin tener suficientes permisos
        http.exceptionHandling().accessDeniedPage("/login");

        // Aplicando JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Opcional, si desea probar la API desde un navegador
         //http.httpBasic();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // Permitir el acceso a swagger sin autenticación
//        web.ignoring().antMatchers("/v2/api-docs")//
//                .antMatchers("/swagger-resources/**")//
//                .antMatchers("/swagger-ui/index.html/**")//
//                .antMatchers("/configuration/**")//
//                .antMatchers("/webjars/**")//
//                .antMatchers("/public");
//
//                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
//                //.and()
//                //.ignoring()
//                //.antMatchers("/h2-console/**/**");;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
