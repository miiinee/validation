package com.min.valid.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min.valid.dto.MemberReqDto;
import com.min.valid.dto.MemberResDto;
import com.min.valid.service.MemberService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

	private MemberService service;
	
	@PostMapping("/member")
	public Long saveMember(@RequestBody @Valid MemberReqDto reqDto) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		reqDto.setPasswd(passwordEncoder.encode(reqDto.getPasswd()));
		
		return service.save(reqDto);
	}
	
	@GetMapping("/members")
	public List<MemberResDto> findAll() {
		return service.findAll();
	}
}
