package nz.govt.doc.t1m.config;

import nz.govt.doc.t1m.services.credentials.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    protected CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
        auth.eraseCredentials(false);
    }

    @Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/partials/**").permitAll()
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/secureLogin").fullyAuthenticated()
//            .and().requiresChannel()
//                .antMatchers("/rest/**").requiresSecure()
//                .antMatchers("/app/**").requiresSecure()
//                .antMatchers("/resource/**").requiresSecure()
//                .antMatchers("/secureLogin").requiresSecure()

//                .and()
//                    .httpBasic().realmName("t1m")
              .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
            //.and()
                //.logout().logoutSuccessUrl("/login?logout")
                //.logout().logoutSuccessUrl("http://localhost:8080/")
            .and()
                .csrf().disable().headers().frameOptions().disable();
    }

}