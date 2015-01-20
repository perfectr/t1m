package nz.govt.doc.t1m;

import nz.govt.doc.t1m.config.MyWebMvcConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.security.SecureRandom;


@Configuration
@ComponentScan("nz.govt.doc.t1m")
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class Application {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(@Value("${version}")String version) {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new MyWebMvcConfigurerAdapter(version);
        return webMvcConfigurerAdapter;
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());
        return passwordEncoder;
    }


    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
