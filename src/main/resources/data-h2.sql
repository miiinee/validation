insert into member (uid, passwd, name, phone1, phone2, phone3, email, reg_dt, mod_dt) values ('admin', '$2a$10$nBx8uC28vIUR5.bOjHC9.OqVYaEzWhcfmhRxh2gF6/vQL/XguuDn2', '테스터', '010', '1234', '5678', 'admin@domain.com', now(), now());
insert into member (uid, passwd, name, phone1, phone2, phone3, email, reg_dt, mod_dt) values ('test', '$2a$10$nBx8uC28vIUR5.bOjHC9.OqVYaEzWhcfmhRxh2gF6/vQL/XguuDn2', '테스터', '010', '1234', '5678', 'test@test.com', now(), now());
insert into member_role (role_name, uid) values ('ADMIN','admin');
insert into member_role (role_name, uid) values ('BASIC','admin');
insert into member_role (role_name, uid) values ('BASIC','test');
