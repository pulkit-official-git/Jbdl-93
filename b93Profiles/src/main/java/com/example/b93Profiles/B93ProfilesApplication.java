package com.example.b93Profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class B93ProfilesApplication implements CommandLineRunner {

	@Value("${paytm.users}")
	Integer paytmUsers;

	@Value("${spring.datasource.url}")
	String url;


	Logger logger = LoggerFactory.getLogger(B93ProfilesApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(B93ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println( "The database url is " + this.url + " and paytm users are " + paytmUsers);

		logger.debug(" The database url is {} and paytm users are {}",this.url,paytmUsers );


	}
}
