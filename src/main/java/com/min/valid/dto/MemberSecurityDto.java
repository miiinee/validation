package com.min.valid.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.min.valid.domain.Member;
import com.min.valid.domain.MemberRole;

/*
 * Spring Security의 Role <-> Custom Role 타입 맞추는 클래스
 * 
 * */
public class MemberSecurityDto extends User {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	public MemberSecurityDto(Member member) {
		super(member.getUid(), member.getPasswd(), makeGrantedAuthority(member.getRoles()));
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}

}
