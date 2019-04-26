package com.min.common;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/***************
 * 모든 Entity들의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할
 ***************/

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity클래스에 Auditing 기능을 포함시킵니다.
public class BaseTimeEntity {

	@CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장됨
	private LocalDateTime regDt;
	
	@LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨
	private LocalDateTime modDt;
	
}
