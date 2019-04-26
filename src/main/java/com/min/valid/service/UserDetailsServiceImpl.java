package com.min.valid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.min.valid.domain.Member;
import com.min.valid.repo.MemberRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MemberRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		Member member = repo.findByUid(uid);
		
		if(member == null) {
			throw new UsernameNotFoundException(uid);
		}
		
		return new User(member.getUid(), member.getPasswd(), AuthorityUtils.createAuthorityList("USER"));
	}

}
