package com.matheusbiasi.api_docker;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ApiDockerApplication {
	
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ApiDockerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void openBrowser() {
		try {
			String url = "http://localhost:8080";
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		String port = environment.getProperty("server.port", "8080");
		String contextPath = environment.getProperty("server.servlet.context-path", "");
		String profileActive = environment.getProperty("spring.profiles.active", "");
		System.out.println("***************************");
		System.out.println("Profile: "+profileActive);
		System.out.println("***************************");
		System.out.println("------------------------------------------------------");
		System.out.println("Aplicação rodando em: http://localhost:" + port + contextPath);
		System.out.println("------------------------------------------------------");
	}
}
