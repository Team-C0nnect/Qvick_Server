package com.project.qvick.domain.school.service.querydsl;

import com.project.qvick.domain.school.presentation.dto.School;
import com.project.qvick.domain.school.domain.repository.querydsl.SchoolQueryRespository;
import com.project.qvick.global.common.dto.request.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SchoolQueryServiceImpl implements SchoolQueryService{

    private final SchoolQueryRespository schoolQueryRespository;

    @Override
    public List<School> schoolList(PageRequest request) {
        return schoolQueryRespository.schoolList(request);
    }

}
