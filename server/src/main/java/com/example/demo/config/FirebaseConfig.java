package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Profile("firebase")
@Configuration
public class FirebaseConfig {
    @Value("${extends.firebase.admin-key-path}")
    private String adminKeyPath;

    @Bean
    public void firebaseConfiguration() {
        FileInputStream serviceAccount =
                null;

        try {
            serviceAccount = new FileInputStream(adminKeyPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Firebase 의존성 추가, 주석 해제 후 사용:
        // FirebaseOptions options = null;
        // try {
        //     options = new FirebaseOptions.Builder()
        //             .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        //             .build();
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
        //
        // FirebaseApp.initializeApp(options);
    }
}
