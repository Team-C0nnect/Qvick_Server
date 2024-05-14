package com.project.qvick.domain.user.service;

import com.project.qvick.domain.user.domain.enums.UserRole;
import com.project.qvick.domain.user.domain.repository.jpa.UserRepository;
import com.project.qvick.domain.user.exception.UserForbiddenException;
import com.project.qvick.domain.user.mapper.UserMapper;
import com.project.qvick.domain.user.presentation.dto.User;
import com.project.qvick.domain.user.presentation.dto.request.RoomRequest;
import com.project.qvick.domain.user.presentation.dto.request.StdIdEditRequest;
import com.project.qvick.domain.user.presentation.dto.request.UserSignUpRequest;
import com.project.qvick.global.common.util.user.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtil userUtil;

    @Override
    public void acceptSignUp(UserSignUpRequest request) {
        User user = userUtil.findUser();
        if(user.getUserRole().equals(UserRole.ADMIN) || user.getUserRole().equals(UserRole.TEACHER)){
            user.setUserRole(UserRole.USER);
        }
        throw UserForbiddenException.EXCEPTION;
    }

    @Override
    public void rejectSignUp(UserSignUpRequest request) {
        User user  = userUtil.findUser();
        if(user.getUserRole().equals(UserRole.GUEST)){
            userRepository.deleteById(request.getId());
        }
        throw UserForbiddenException.EXCEPTION;
    }

    @Override
    public void editUserStdId(StdIdEditRequest request) {
        User user = userUtil.findUser();
        user.setStdId(request.getStdId());
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public void deleteUser() {
        User user = userUtil.findUser();
        userUtil.userCheckById(user.getId());
        userRepository.deleteById(user.getId());
    }

    @Override
    public void editRoom(RoomRequest request){
        User user = userUtil.findUser();
        user.setRoom(request.getRoom());
        userRepository.save(userMapper.toEdit(user));
    }

}
