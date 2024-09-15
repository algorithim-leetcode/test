package com.toy.truffle;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TruffleApplication {
    public static void main(String[] args) {
        // .env 파일 로딩
        Dotenv dotenv = Dotenv.load();

        // 데이터 베이스 연결 설정
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("spring.datasource.driver-class-name", dotenv.get("DB_DRIVER"));

        SpringApplication.run(TruffleApplication.class, args);
    }

}
