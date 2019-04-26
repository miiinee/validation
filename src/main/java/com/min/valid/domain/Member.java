package com.min.valid.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.min.common.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length=50)
    private String uid;
    
    @Column(nullable = false, length=200)
    private String passwd;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone1;

    @Column(nullable = false)
    private String phone2;

    @Column(nullable = false)
    private String phone3;

    @Column(nullable = false, unique = true, length=50)
    private String email;
    
//    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="uid", referencedColumnName="uid") // referencedColumnName 없는 경우 이름만 같고 전혀 다른 컬럼 생성됨(h2: uid(varchar) 아닌 uid(bigint) 생성)
//	private List<MemberRole> roles;
    
    @Builder
    public Member(String uid, String passwd, String name, String phone1, String phone2, String phone3, String email) {
        this.uid = uid;
        this.passwd = passwd;
    	this.name = name;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email = email;
    }
}
