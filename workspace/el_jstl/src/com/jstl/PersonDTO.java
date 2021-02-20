package com.jstl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//1번 어노테이션 NonNull로 설정된 필드에 대해 파라미터가 있는 생성자를 만든다.
//2번 어노테이션 파라미터를 받지 않는 기본생성자
//3번 어노테이션 모든 필드에 대한 생성자
public class PersonDTO {
	@NonNull
	private String name;
	private int age;

}
