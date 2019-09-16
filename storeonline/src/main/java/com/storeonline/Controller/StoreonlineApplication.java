package com.storeonline.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages={
		"com.storeonline.Controller","com.storeonline.DAO","com.storeonline.DTO","com.storeonline.mapper"})
public class StoreonlineApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(StoreonlineApplication.class, args);
	}
	

}
