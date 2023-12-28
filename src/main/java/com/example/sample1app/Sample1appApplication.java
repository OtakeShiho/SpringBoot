package com.example.sample1app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

@SpringBootApplication
public class Sample1appApplication {
	public static void main(String[] args){
		SpringApplication.run(Sample1appApplication.class, args);
	}

	@RequestMapping("/")
	public String index(
		HttpServletRequest request,
		HttpServletResponse response) {
	  response.setContentType(MediaType.TEXT_HTML_VALUE);
	  String content = """
		<html>
			<head>
			<title>Sample App</title>
			</head>
			<body>
			<h1>Sample App</h1>
			<p>This is sample app page!</p>
		</html>
		""";
		return content;
	}
}