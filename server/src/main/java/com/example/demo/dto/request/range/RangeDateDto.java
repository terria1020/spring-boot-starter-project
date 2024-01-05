package com.example.demo.dto.request.range;

import com.example.demo.constants.DateTimeConst;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RangeDateDto {
    @DateTimeFormat(pattern = DateTimeConst.DATE)
    private LocalDate startAt;
    @DateTimeFormat(pattern = DateTimeConst.DATE)
    private LocalDate endAt;

    public RangeDateDto() {
        // (오늘 - 1달) ~ 오늘
        this.startAt = LocalDate.now().minusMonths(1L);
        this.endAt = LocalDate.now();
    }

    public RangeDateDto(LocalDate startAt, LocalDate endAt) {
        if (startAt == null) this.startAt = LocalDate.now().minusMonths(1L);
        else this.startAt = startAt;
        if (endAt == null) this.endAt = LocalDate.now();
        else this.endAt = endAt;
    }
}
