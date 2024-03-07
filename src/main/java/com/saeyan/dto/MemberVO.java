package com.saeyan.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * DROP TABLE MEMBER;
CREATE TABLE MEMBER(
NAME VARCHAR2(30),
USERID VARCHAR2(30) PRIMARY KEY,
PWD VARCHAR2(20),
EMAIL VARCHAR2(30),
PHONE CHAR(13),
ADMIN NUMBER(1)
);

 */
@Setter
@Getter
@ToString
public class MemberVO {
	String name;
	String userid;
	String pwd;
	String email;
	String phone;
	int admin;
}
