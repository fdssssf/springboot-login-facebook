package com.data.social.service;


import com.data.social.data.Member;

import java.util.List;

public interface MemberService {
    Member save(Member m);
    Member findByEmail(String email);
    List<Member> findAll();
}
