package com.data.social.Web;

import com.data.social.data.Member;
import com.data.social.data.Role;
import com.data.social.data.RoleRepository;
import com.data.social.service.MemberService;
import com.data.social.service.SecurityService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

@Controller
@Log4j2
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    SecurityService securityService;

    @Autowired
    MemberService memberService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping(value = "")
    public String home(Principal principal, Model model){

        if (principal!=null) {
            Member member = memberService.findByEmail(principal.getName());
            List<Member> data=memberService.findAll();
            model.addAttribute("member",member);
        }

        return "index";
    }


    @GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/facebook/complete")
    public String login(){

        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        String email=map.get("id")+"@facebook.com";
        Member member =memberService.findByEmail(email);
        if (member ==null){

        member=saveFromFB(map,email);
        }else{
            member.setPassword(bCryptPasswordEncoder.encode("memberdata"));
            member=memberService.save(member);
        }
        securityService.autologin(member.getEmail(),member.getPassword());

        return "redirect:/auth";
    }

public Member saveFromFB(Map<String,String> map,String email){
        Member member =new Member();
        String imageMember="https://graph.facebook.com/"+map.get("id")+"/picture?type=large";
        // gán quyền USER cho member mới
        Role r=roleRepository.findByName("USER");
        member.setName(map.get("name").toString());
        member.setEmail(email);
        member.setImage(imageMember);
        member.setRole(new HashSet<Role>(Arrays.asList(r)));
        member.setPassword(bCryptPasswordEncoder.encode("memberdata"));
        member =memberService.save(member);
        return member;
}



}
