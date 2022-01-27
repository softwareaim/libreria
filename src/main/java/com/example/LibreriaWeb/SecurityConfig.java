
package com.example.LibreriaWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*","/img/*","/js/*","/","/usuario/registro","/usuario/guardar").permitAll()
                .antMatchers("/usuario/editar/*","/usuario/editar/*","/autor/**","/editorial/**","/prestamo/**","/cliente/**",
                "/libro/editar/*","/libro/eliminar/*").hasAnyAuthority("ROLE_ADMIN").anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login") 
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/logincheck")
                .failureUrl("/login?error=error").permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/errores/403")
                .and().csrf().disable();



    }
}
