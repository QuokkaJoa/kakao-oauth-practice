package com.quokkajoa.practice.kakaooauth.kakaooauthpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KakaoOauthPracticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(KakaoOauthPracticeApplication.class, args);
  }

}
