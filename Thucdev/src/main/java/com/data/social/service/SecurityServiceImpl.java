package com.data.social.service;

        import lombok.extern.log4j.Log4j2;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserDetailsService userdetailsService;
    @Autowired
    AuthenticationManager authenticationmanage;

    @Override
    public void autologin(String u, String p) {
        UserDetails userDetails =userdetailsService.loadUserByUsername(u);
        UsernamePasswordAuthenticationToken passwordAuthenticationToken
                =new UsernamePasswordAuthenticationToken(userDetails, p,userDetails.getAuthorities());
        if(passwordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
            log.debug("auto-login ok");
        }
    }

    @Override
    public String findloggedUsername() {
        Object userdatels= SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userdatels instanceof UserDetails) {
            return ((UserDetails) userdatels).getUsername();
        }
        return null;
    }
}
