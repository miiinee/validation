package com.min.valid.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.min.valid.domain.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{
	Optional<Member> findByEmail(String email);

	Member findByUid(String uid);
}
