package com.min.valid.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MembercontrollerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void 메인페이지_로딩() {
		
		String body = restTemplate.getForObject("/", String.class);
		
		assertThat(body).contains("회원가입");
	}

}
