package com.crest.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Arun on 10/12/16.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    public  static void main(String[] args) throws  Exception{
    SpringApplication.run(Application.class, args);
    }
}
