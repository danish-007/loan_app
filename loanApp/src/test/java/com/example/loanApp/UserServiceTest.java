package com.example.loanApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.model.UserDto;
import com.example.loanApp.repository.UserRepository;
import com.example.loanApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        when(encoder.encode(anyString())).thenReturn("encodedPassword");
    }

    @Test
    public void testCreateUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@gmail.com");
        userDto.setPassword("password");
        userDto.setRoles("USER");

        UserInfo userInfo = new UserInfo();
//        userInfo.setId(1L);
        userInfo.setName(userDto.getName());
        userInfo.setEmail(userDto.getEmail());
        userInfo.setPassword("encodedPassword");
        userInfo.setRoles("USER");
        userInfo.setCreatedBy(userDto.getName());
        userInfo.setModifiedBy(userDto.getName());

        when(userRepository.save(any(UserInfo.class))).thenReturn(userInfo);

        UserInfo createdUser = userService.createUser(userDto);

        // Assert
        assertEquals(userDto.getName(), createdUser.getName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals("encodedPassword", createdUser.getPassword());
        assertEquals(userDto.getRoles(), createdUser.getRoles());
        assertEquals(userDto.getName(), createdUser.getCreatedBy());
        assertEquals(userDto.getName(), createdUser.getModifiedBy());
    }
}
