package com.example.loanApp.service;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.model.UserDto;
import com.example.loanApp.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    LoanService loanService;

    public UserInfo findByUser(Long id){
        return userRepository.findById(id).get();
    }

    public UserInfo findByName(String name){
        return userRepository.findByName(name).get();
    }
    public UserInfo createUser(UserDto user){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(encoder.encode(user.getPassword()));
        userInfo.setRoles(user.getRoles());
        userInfo.setCreatedBy(user.getName());
        userInfo.setModifiedBy(user.getName());
        userInfo.setMobileNumber(user.getMobileNumber());
        return userRepository.save(userInfo);
    }
    public SuccessResponse viewLoan(String username){
        UserInfo user = userRepository.findByName(username).get();
        return loanService.viewLoan(user.getId());

    }
}
