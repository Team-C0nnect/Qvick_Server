package com.project.qvick.domain.user.application.service;

import com.project.qvick.domain.check.client.dto.request.CodeRequest;
import com.project.qvick.domain.check.domain.repository.jpa.CheckCodeRepository;
import com.project.qvick.domain.check.exception.CheckAlreadyExistsException;
import com.project.qvick.domain.check.exception.CheckCodeError;
import com.project.qvick.domain.user.application.util.UserUtil;
import com.project.qvick.domain.user.client.dto.User;
import com.project.qvick.domain.user.client.dto.request.AdminPasswordEditRequest;
import com.project.qvick.domain.user.client.dto.request.AdminSetStatusRequest;
import com.project.qvick.domain.user.client.dto.request.PasswordEditRequest;
import com.project.qvick.domain.user.client.dto.request.RoomEditRequest;
import com.project.qvick.domain.user.client.dto.request.StdIdEditRequest;
import com.project.qvick.domain.user.domain.mapper.UserMapper;
import com.project.qvick.domain.user.domain.repository.jpa.UserRepository;
import com.project.qvick.domain.user.exception.PasswordWrongException;
import com.project.qvick.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtil userUtil;
    private final CheckCodeRepository checkCodeRepository;
    private final PasswordEncoder encoder;

    @Override
    public void editUserStdId(StdIdEditRequest request) {
        User user = userUtil.getUser();
        user.setStdId(request.getStdId());
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public void deleteUser() {
        userRepository.deleteById(userUtil.getUser().getId());
    }

    @Override
    public void editRoom(RoomEditRequest request) {
        User user = userUtil.findUserByStdId(request.getStdId());
        user.setRoom(request.getRoom());
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public User findUser() {
        return userUtil.getUser();
    }

    @Override
    public void check(CodeRequest request) {
        User user = userUtil.getUser();
        if (user.isChecked())
            throw CheckAlreadyExistsException.EXCEPTION;
        if (!checkCodeRepository.existsByCodeAndValid(request.code(), true))
            throw CheckCodeError.EXCEPTION;
        user.setChecked(true);
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public void editPassword(PasswordEditRequest request) {
        User user = userUtil.getUser();
        if (!encoder.matches(request.getOldPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;
        user.setPassword(encoder.encode(request.getNewPassword()));
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public void adminEditPassword(AdminPasswordEditRequest request) {
        User user = userUtil.findUserByEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getNewPassword()));
        userRepository.save(userMapper.toEdit(user));
    }

    @Override
    public void adminDeleteUser(String email) {
        if (userRepository.findByEmail(email).isEmpty())
            throw UserNotFoundException.EXCEPTION;
        userRepository.deleteByEmail(email);
    }

    @Override
    public boolean isChecked() {
        User user = userUtil.getUser();
        return user.isChecked();
    }

    @Override
    public void fixStatus(AdminSetStatusRequest setStatusRequest) {
        User user = userUtil.findUserByEmail(setStatusRequest.getEmail());
        user.setChecked(setStatusRequest.getStatus() != 1);
        userRepository.save(userMapper.toEdit(user));
    }

}
