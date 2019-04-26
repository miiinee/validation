package com.min.valid.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.min.valid.domain.Member;
import com.min.valid.domain.MemberRole;
import com.min.valid.dto.MemberReqDto;
import com.min.valid.dto.MemberResDto;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberRepoTest {

	@Autowired
	MemberRepo repo;
	
	@Test
	public void insertTest() {
		for(int i=0; i<100; i++) {
			MemberReqDto dto = new MemberReqDto();
			dto.setUid("user" + i);
			dto.setPasswd("pw" + i);
			dto.setName("test" + i);
			dto.setPhoneNumber("01012345678");
			dto.setEmail("hihi@" + i);
			
			MemberRole role = new MemberRole();
			if(i <= 80) {
				role.setRoleName("BASIC");
			}else if(i <= 90) {
				role.setRoleName("MANAGER");
			}else {
				role.setRoleName("ADMIN");
			}
			dto.setRoles(Arrays.asList(role));
			repo.save(dto.toEntity());
		}
	}
	
	@Test
	public void testMember() {
		Optional<Optional<Member>> result = Optional.ofNullable(repo.findById(85L));
		result.ifPresent(member -> log.info("member >>>>>" + member.map(MemberResDto::new)));
	}
	
	@Test
	public void findByUidTest() {
		//given
		
		//when
		Member member = repo.findByUid("admin");
		
		//then
		assertThat("admin", is(member.getUid()));
		assertThat("테스터", is(member.getName()));
		assertThat("01012345678", is(member.getPhone1()+member.getPhone2()+member.getPhone3()));
		assertThat("admin@domain.com", is(member.getEmail()));
		
	}

	@After
    public void cleanup() {
    	/** 
		        이후 테스트 코드에 영향을 끼치지 않기 위해 
		        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
    	 **/
//		repo.deleteAll();
    }
}
