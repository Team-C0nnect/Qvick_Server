package com.project.qvick.domain.check.domain.mapper;

import com.project.qvick.domain.check.client.dto.Check;
import com.project.qvick.domain.check.domain.CheckEntity;
import com.project.qvick.domain.user.client.dto.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckMapper {

    public CheckEntity createCheckEntity(User user, LocalDateTime date) {
        return CheckEntity.builder()
                .userId(user.getId())
                .stdId(user.getStdId())
                .name(user.getName())
                .email(user.getEmail())
                .room(user.getRoom())
                .checkedDate(date)
                .build();
    }

    public Check toCheck(CheckEntity entity){
        return Check.builder()
                .checkedDate(entity.getCheckedDate())
                .build();
    }

}