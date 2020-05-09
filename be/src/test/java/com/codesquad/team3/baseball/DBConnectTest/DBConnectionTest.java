package com.codesquad.team3.baseball.DBConnectTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
public class DBConnectionTest {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    private String url = System.getenv("DATASOURCE_URL");

    private String username = System.getenv("DATASOURCE_USERNAME");

    private String password = System.getenv("DATASOURCE_PASSWORD");

    @Test
    void DB_연결() throws ClassNotFoundException {
        Class.forName(driver);
        try (Connection con = DriverManager.getConnection(url, username, password)) {
        } catch (SQLException e) {
            e.printStackTrace();
            fail("DB Connection 실패 설정 정보를 확인해주세요.");
        }
    }
}
