package ru.eastwind.santa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
 *  TODO сделайте так, чтобы можно было легко 
 *  	отрефакторить название пакета business
 */
@ComponentScan(basePackages = "ru.eastwind.santa.business")
public class AppConfig {

}
