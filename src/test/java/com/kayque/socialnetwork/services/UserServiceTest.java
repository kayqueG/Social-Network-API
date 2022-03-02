package com.kayque.socialnetwork.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kayque.socialnetwork.dto.ProfileDto;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.mappers.UserMapperImpl;
import com.kayque.socialnetwork.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Spy
	private UserMapper userMapper= new UserMapperImpl();
	
	@Test
    void testProfileData() {
        // given
        Optional<User> user = Optional.of(User.builder()
				.id(10L)
				.firstName("first")
				.lastName("login")
				.password("pass")
				.createdDate(LocalDateTime.now())
				.build()
				);
        Mockito.when(userRepository.findById(10L)).thenReturn(user);

        // when
        ProfileDto profile = userService.getProfile(10L);

        // then
        verify(userRepository).findById(10L);
        assertAll(() -> {
            assertEquals(user.get().getFirstName(), profile.getUserDto().getFirstName());
            assertEquals(user.get().getLastName(), profile.getUserDto().getLastName());
        });
    }
	
}


