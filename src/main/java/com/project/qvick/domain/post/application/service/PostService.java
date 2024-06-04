package com.project.qvick.domain.post.application.service;

import com.project.qvick.domain.post.client.dto.request.PostDeleteRequest;
import com.project.qvick.domain.post.client.dto.request.PostRegisterRequest;

public interface PostService {

    void postRegister(PostRegisterRequest request);

    void postDelete(PostDeleteRequest request);
}
