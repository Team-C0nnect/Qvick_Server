package com.project.qvick.domain.user.service;

import com.project.qvick.domain.user.presentation.dto.User;
import com.project.qvick.domain.user.presentation.dto.request.UserAcceptRequest;
import com.project.qvick.domain.user.presentation.dto.request.UserRequest;
import com.project.qvick.domain.user.presentation.dto.request.UserSignUpRequest;

import java.util.List;

public interface UserService {

    void acceptSignUp(UserAcceptRequest request);

    void rejectSignUp(UserAcceptRequest request);

}
