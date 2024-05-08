package com.example.loanApp.service;

import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userRepository.findByName(s);
//        UserDetails userDetails = new UserInfoDetail(userInfo);
        return userInfo.map(UserInfoDetail::new).orElseThrow(()->new UsernameNotFoundException("user not found"+s));

    }
}
