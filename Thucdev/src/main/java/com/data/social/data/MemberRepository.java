package com.data.social.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findByEmail(String email);
    @Query(value = "SELECT new com.data.social.data.Member(m) FROM Member m ")
    List<Member> findAllMember();
}
