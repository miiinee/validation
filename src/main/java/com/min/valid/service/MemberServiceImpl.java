package com.min.valid.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.valid.dto.MemberReqDto;
import com.min.valid.dto.MemberResDto;
import com.min.valid.exception.ValidCustomException;
import com.min.valid.repo.MemberRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private MemberRepo repo;
	
	@Override
	@Transactional
	public Long save(MemberReqDto reqDto) {
		verifyDuplicateEmail(reqDto.getEmail());
		return repo.save(reqDto.toEntity()).getId();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MemberResDto> findAll() {
		return repo.findAll().stream().map(MemberResDto::new).collect(Collectors.toList());
	}
	
	
	
	private void verifyDuplicateEmail(String email) {
		if(repo.findByEmail(email).isPresent()) {
			throw new ValidCustomException("이미 사용중인 이메일주소입니다", "email");
		}
	}

}
