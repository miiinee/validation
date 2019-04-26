package com.min.valid.service;

import java.util.List;

import com.min.valid.dto.MemberReqDto;
import com.min.valid.dto.MemberResDto;

public interface MemberService {

	public Long save(MemberReqDto reqDto);
	
	public List<MemberResDto> findAll();
}
