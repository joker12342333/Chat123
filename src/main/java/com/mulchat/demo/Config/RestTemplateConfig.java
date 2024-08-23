package com.mulchat.demo.Config;


import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws IOException {
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\\\Users\\\\HP\\\\Desktop\\\\graphite-pad-433303-r4-2617d4e39520.json"));
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
			.setCredentials(credentials)
			.build();
		Firestore db = firestoreOptions.getService();
	
		return builder.build();
	}
	}