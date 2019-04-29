package com.min.valid.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.valid.dto.MemberReqDto;
import com.min.valid.dto.MemberResDto;
import com.min.valid.dto.MemberSecurityDto;
import com.min.valid.exception.ValidCustomException;
import com.min.valid.repo.MemberRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

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
	
	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
//		Member member = repo.findByUid(uid);
//		
//		if(member == null) {
//			throw new UsernameNotFoundException(uid);
//		}
//		
//		return new User(member.getUid(), member.getPasswd(), AuthorityUtils.createAuthorityList("USER"));
		return Optional.ofNullable(repo.findByUid(uid))
				.filter(m -> m!= null)
				.map(m -> new MemberSecurityDto(m)).get();
	}
	
	private void verifyDuplicateEmail(String email) {
		if(repo.findByEmail(email).isPresent()) {
			throw new ValidCustomException("이미 사용중인 이메일주소입니다", "email");
		}
	}

}
