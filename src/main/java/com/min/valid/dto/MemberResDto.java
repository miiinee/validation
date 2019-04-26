package com.min.valid.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.min.valid.domain.Member;

import lombok.Getter;

@NoArgsConstructor
@Getter
public class MemberResDto {

	private Long id;
	private String uid;
	private String passwd;
    private String name;
    private String phoneNumber;
    private String email;
    private String regDt;
    private String modDt;
    
    public MemberResDto(Member member) {
        id = member.getId();
        uid = member.getUid();
        passwd = member.getPasswd();
        name = member.getName();
        phoneNumber = toStringPhone(member.getPhone1(), member.getPhone2(), member.getPhone3());
        email = member.getEmail();
        regDt = toStringDateTime(member.getRegDt());
        modDt = toStringDateTime(member.getModDt());
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
}
