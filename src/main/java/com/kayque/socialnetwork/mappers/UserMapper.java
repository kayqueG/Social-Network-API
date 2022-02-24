package com.kayque.socialnetwork.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.kayque.socialnetwork.dto.ImageDto;
import com.kayque.socialnetwork.dto.ProfileDto;
import com.kayque.socialnetwork.dto.SignUpDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.dto.UserSummaryDto;
import com.kayque.socialnetwork.entities.Image;
import com.kayque.socialnetwork.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toUserDto(User user);

	@Mapping(target = "password", ignore = true)
	User signUpToUser(SignUpDto signUpDto);

	UserSummaryDto toUserSummary(User user);

	List<UserSummaryDto> toUserSummaryDtos(List<User> users);

	@Mapping(target = "userDto.id", source = "id")
	@Mapping(target = "userDto.firstName", source = "firstName")
	@Mapping(target = "userDto.lastName", source = "lastName")
	ProfileDto userToProfileDto(User user);

	
	@Mapping(target="userDto",source="user")
	ImageDto imageToImageDto(Image image);


}
