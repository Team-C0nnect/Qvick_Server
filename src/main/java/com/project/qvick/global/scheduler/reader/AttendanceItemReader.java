package com.project.qvick.global.scheduler.reader;

import com.project.qvick.domain.check.application.query.CheckQueryService;
import com.project.qvick.domain.check.client.dto.Check;
import com.project.qvick.global.common.dto.request.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AttendanceItemReader implements ItemReader<List<String>> {

    private static final int SIZE = 50;
    private final CheckQueryService checkQueryService;
    private int page = 1;

    @Override
    public List<String> read() {
        log.info("....................... attendaceItemReader start");
        final List<String> nonCheckUserList = checkQueryService.findAllNonCheckUser(pageRequest(page));
        log.info("....................... nonAttendanceList : {} ", nonCheckUserList.size());
        if (nonCheckUserList.isEmpty()) {
            page = 1;
            return null;
        }
        page++;
        log.info("....................... attendaceItemReader end");
        return nonCheckUserList;
    }

    private PageRequest pageRequest(int page) {
        return new PageRequest(page, SIZE);
    }

}
