package ar.com.osdepym.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan("ar.com.osdepym.context.rest")
@EnableWebMvc
public class AppConfig {

}
