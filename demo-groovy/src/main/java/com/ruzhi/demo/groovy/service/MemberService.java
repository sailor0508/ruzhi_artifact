package com.ruzhi.demo.groovy.service;

import com.ruzhi.demo.groovy.model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

	private Map<String, Member> memberMap;

	@PostConstruct
	public void init() {
		memberMap = new HashMap<String, Member>();
		Member member = new Member("joshua", "daonan.zhan@gmail.com");
		memberMap.put(member.getEmail(), member);
		member = new Member("daonan", "zdn880729@gmail.com");
		memberMap.put(member.getEmail(), member);
	}

	public Member findMember(String email) {
		return memberMap.get(email);
	}
}
