package com.data.social.data;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String image;
    private String name;
    private String password;


    // == Create table member_role(member_id int(11), role_id int(11))
    @ManyToMany
    @JoinTable(name = "member_role",joinColumns = @JoinColumn(name = "member_id"),inverseJoinColumns =@JoinColumn(name ="role_id" ))
    private Set<Role> role=new HashSet<>();

    public  void addRole(Role role){
        this.role.add(role);
    }
    public void removeRole(Role role){
        this.role.remove(role);
    }


    // thông tin đầy đủ của 1 member
    public Member(Member member){
        this.id=member.getId();
        this.image=member.getMemberImage();
        this.name=member.getName();
        this.email=member.getEmail();
        this.authorities=member.getRole().stream().map(Role::getName).collect(Collectors.toSet());
    }

    public String getMemberImage(){
        if (image !=null){
            if (this.image.startsWith("http")){
                return image;
            }else {
                return "/image/"+image;
            }
        }else {
            return "/image/default.png";
        }

    }

    @Transient
    private Set<String> authorities;

}
