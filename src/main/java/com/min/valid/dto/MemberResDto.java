package com.min.valid.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.min.valid.domain.Member;
import com.min.valid.domain.MemberRole;

import lombok.Getter;

@NoArgsConstructor
@Getter
public class MemberResDto {

	private static final String ROLE_PREFIX = "ROLE_";
	
	private Long id;
	private String uid;
	private String passwd;
    private String name;
    private String phoneNumber;
    private String email;
    private String regDt;
    private String modDt;
    private List<GrantedAuthority> roles;
    
    public MemberResDto(Member member) {
        id = member.getId();
        uid = member.getUid();
        passwd = member.getPasswd();
        name = member.getName();
        phoneNumber = toStringPhone(member.getPhone1(), member.getPhone2(), member.getPhone3());
        email = member.getEmail();
        regDt = toStringDateTime(member.getRegDt());
        modDt = toStringDateTime(member.getModDt());
        roles = makeGrantedAuthority(member.getRoles());
    }

    private String toStringPhone(String phone1, String phone2, String phone3){
        return phone1+"-"+phone2+"-"+phone3;
    }
    
    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
    
    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}
}
