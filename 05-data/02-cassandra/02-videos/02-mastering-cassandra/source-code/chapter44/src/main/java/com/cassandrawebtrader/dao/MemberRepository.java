package com.cassandrawebtrader.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrawebtrader.domain.Member;

public interface MemberRepository extends CassandraRepository<Member> {
	
	@Query("select * from members where username = ?0")
	Iterable<Member> findByUsername(String username);

}
