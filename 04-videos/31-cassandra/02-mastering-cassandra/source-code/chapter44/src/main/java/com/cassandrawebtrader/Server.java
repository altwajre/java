package com.cassandrawebtrader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cassandrawebtrader.dao.MemberRepository;
import com.cassandrawebtrader.domain.Member;

@Component
public class Server {
	
	private static Logger logger = LoggerFactory.getLogger(Server.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void printMembers() {
		Iterable<Member> members = memberRepository.findAll();
		
		for(Member m: members) {
			logger.info(m.toString());
		}
	}

}
