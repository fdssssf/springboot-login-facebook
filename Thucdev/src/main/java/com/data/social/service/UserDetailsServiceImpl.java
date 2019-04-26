package com.data.social.service;

import com.data.social.data.Member;
import com.data.social.data.MemberRepository;
import com.data.social.data.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member=memberRepository.findByEmail(s);
        if (member!=null){
            Set<GrantedAuthority> grantedAuthority = new HashSet<>();
            for (Role roles : member.getRole())
            {
                grantedAuthority.add(new SimpleGrantedAuthority(roles.getName()));
            }
            return new org.springframework.security.core.userdetails.User(member.getEmail(), member.getPassword(),
                    grantedAuthority);
        }
        return  null;


    }
}
