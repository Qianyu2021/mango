package com.Mangos.MangosBlog;

import com.Mangos.MangosBlog.s3.S3Buckets;
import com.Mangos.MangosBlog.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class Home {

	public static void main(String[] args) {
		SpringApplication.run(Home.class, args);
	}

	@Bean
	CommandLineRunner runner(S3Service s3Service, S3Buckets s3Buckets) {
		return args -> {
			System.out.println(s3Buckets.getBlog());
//			s3Service.uploadFile(
//					s3Buckets.getBlog(),
//					"post-images/temp/test",
//					"I am some test".getBytes()
//			);
		};
	}
}


