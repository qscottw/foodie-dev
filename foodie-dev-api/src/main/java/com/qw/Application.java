package com.qw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages="com.qw.mapper")
@ComponentScan(basePackages = {"com.qw", "org.n3r.idworker"})
public class Application {

    public static void main(String[] args) {
        try{
            SpringApplication.run(Application.class, args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
