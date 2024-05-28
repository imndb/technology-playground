package com.imndb.demo;

import com.imndb.demo.entity.Url;
import com.imndb.demo.repository.UrlRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {

	private final UrlRepository urlRepository;

	public DemoApplication(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initDb(){

	}

}
