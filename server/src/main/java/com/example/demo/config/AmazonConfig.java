package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("aws")
@Configuration
public class AmazonConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    // AWS 의존성 추가, 주석 해제 후 사용:
    // @Bean
    // public AmazonS3 amazonS3() {
    //     BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    //     return AmazonS3ClientBuilder.standard()
    //             .withCredentials(new AWSStaticCredentialsProvider(credentials))
    //             .withRegion(region)
    //             .build();
    // }
}
