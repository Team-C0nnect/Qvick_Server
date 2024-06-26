package com.project.qvick.domain.post.domain.repository.query;

import com.project.qvick.domain.post.client.dto.Post;
import com.project.qvick.domain.post.client.dto.request.PostSearchRequest;
import com.project.qvick.global.common.client.dto.request.PageRequest;

import java.util.List;

public interface PostQueryRepository {
    List<Post> postList(PageRequest request);

    List<Post>postSearch(PostSearchRequest request);
}
