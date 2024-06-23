package com.Mangos.MangosBlog;

import com.Mangos.MangosBlog.repository.PostRepository;
import com.Mangos.MangosBlog.s3.S3Buckets;
import com.Mangos.MangosBlog.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Home {

	public static void main(String[] args) {
		SpringApplication.run(Home.class, args);
	}

	@Bean
	CommandLineRunner runner(
			PostRepository postRepository,
			S3Service s3Service,
			S3Buckets s3Buckets) {
		return args -> {
			//createPost(postRepository);
			// testBucketUploadAndDownload(s3Service, s3Buckets);

			System.out.println("started");
		};
	}


}


