package com.jmtebar.applications.api;

import com.jmtebar.applications.api.controller.ApplicationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationsApiApplicationTests {

	@Autowired
	private ApplicationController applicationController;

	@Test
	void contextLoads() {
		assertThat(applicationController).isNotNull();
	}

}
