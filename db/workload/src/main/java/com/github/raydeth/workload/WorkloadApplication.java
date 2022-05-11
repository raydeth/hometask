package com.github.raydeth.workload;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class WorkloadApplication {

    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost/cdp";


    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "Maxim_Sherstoboyev");
        props.setProperty("password", "123qwe123QWE");
        Connection conn = DriverManager.getConnection(JDBC_CONNECTION, props);


        int studentSize = 100_000;
        for (int i = 2; i <= studentSize; i++) {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into students(id, name, surname, birthday, phone, primary_skill, created_datetime, updated_datetime) values (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, WordGenerator.generateName());
            preparedStatement.setString(3, WordGenerator.generateSurname());
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
            preparedStatement.setInt(5, i);
            preparedStatement.setString(6, WordGenerator.generatePrimarySkill());
            preparedStatement.setDate(7, new Date(new java.util.Date().getTime()));
            preparedStatement.setDate(8, new Date(new java.util.Date().getTime()));
            preparedStatement.executeUpdate();
        }

        int subjectSize = 1_000;
        for (int i = 1; i <= subjectSize; i++) {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into subjects(id, subject_name, tutor) values (?, ?, ?)");
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, "Subject " + i);
            preparedStatement.setString(3, WordGenerator.generateName());
            preparedStatement.executeUpdate();
        }

        for (int i = 0; i < 1_000_000_000; i++) {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("insert into exam_result(student_id, subject_id, mark) values (?, ?, ?)");
                preparedStatement.setInt(1, ThreadLocalRandom.current().nextInt(1, studentSize));
                preparedStatement.setInt(2, ThreadLocalRandom.current().nextInt(1, subjectSize));
                preparedStatement.setInt(3, ThreadLocalRandom.current().nextInt(1, 11));
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                i--;
            }
        }
    }
}
