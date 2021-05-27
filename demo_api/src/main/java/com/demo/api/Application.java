/*************************************************************************************************
 * Copyright (C) 2020   KAVYA, PVT LTD   legal@kavyaComapnay.com
 * 
 * This file is part of www.kavyaComapnay.com 
 * All Rights Reserved.  Unauthorized copying of this file, via any medium is strictly prohibited
 *
 * We KAVYA COMPANY can not be copied in part or in full 
 * and/or distributed without the express permission of kavyaComapnay
**************************************************************************************************/

package com.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		
		
		System.out.println("This is my first Spring boot project");
		
		System.out.println("My git commit");
		System.out.println("My git commit1");
		SpringApplication.run(Application.class, args);
        }  

}
