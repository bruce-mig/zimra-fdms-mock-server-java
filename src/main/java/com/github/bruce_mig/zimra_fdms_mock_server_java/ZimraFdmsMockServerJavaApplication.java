package com.github.bruce_mig.zimra_fdms_mock_server_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * A mock server for the Zimra FDMS API
 * @author Bruce Migeri
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class ZimraFdmsMockServerJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZimraFdmsMockServerJavaApplication.class, args);
	}

}
