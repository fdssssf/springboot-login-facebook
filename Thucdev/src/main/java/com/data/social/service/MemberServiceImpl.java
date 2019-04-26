package com.data.social.service;

import com.data.social.data.Member;
import com.data.social.data.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {



    @Autowired
    MemberRepository memberRepository;


    @Override
    public Member save(Member m) {
        return memberRepository.save(m);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAllMember();
    }
}
