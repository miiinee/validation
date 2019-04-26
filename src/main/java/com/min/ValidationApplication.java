package com.min;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.WebRequest;

import com.min.valid.exception.ValidCustomException;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class ValidationApplication {

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {

			@Override
			public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
				Throwable error = getError(webRequest);
				if (error instanceof ValidCustomException) {
					errorAttributes.put("errors", ((ValidCustomException) error).getErrors());
				}
				return errorAttributes;
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);
	}

}
