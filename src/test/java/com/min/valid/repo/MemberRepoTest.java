package com.min.valid.repo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.min.valid.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepoTest {

	@Autowired
	MemberRepo repo;
	
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
