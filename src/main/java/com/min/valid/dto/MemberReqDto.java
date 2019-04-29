package com.min.valid.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.min.valid.domain.Member;
import com.min.valid.domain.MemberRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberReqDto {

	private Long id;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String uid;
    
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String passwd;
    
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
    private String phoneNumber;

    @NotBlank(message = "메일을 입력해주세요.")
    @Email(message = "메일의 양식을 지켜주세요.")
    private String email;
    
    private List<MemberRole> roles;
    
    public Member toEntity() {
    	String[] phones = parsePhone();
    	
//    	return new Member(name, phones[0], phones[1], phones[2], email);
    	return Member.builder()
    			.uid(uid)
    			.passwd(passwd)
    			.name(name)
    			.phone1(phones[0])
    			.phone2(phones[1])
    			.phone3(phones[2])
    			.email(email)
    			.roles(roles)
    			.build();
    }
    
    private String[] parsePhone(){
        String[] phones = new String[3];
        int mid = phoneNumber.length() == 10? 6:7;
        phones[0] = phoneNumber.substring(0,3);
        phones[1] = phoneNumber.substring(3,mid);
        phones[2] = phoneNumber.substring(mid,phoneNumber.length());
        return phones;
    }
    
}
