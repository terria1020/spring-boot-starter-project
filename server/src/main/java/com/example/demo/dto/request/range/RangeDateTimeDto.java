package com.example.demo.dto.request.range;

import com.example.demo.constants.DateTimeConst;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RangeDateTimeDto {
    @DateTimeFormat(pattern = DateTimeConst.DATETIME)
    private LocalDateTime startAt;
    @DateTimeFormat(pattern = DateTimeConst.DATETIME)
    private LocalDateTime endAt;

    public RangeDateTimeDto() {
        // (오늘 - 1달) ~ 오늘
        this.startAt = LocalDateTime.now().minusMonths(1L);
        this.endAt = LocalDateTime.now();
    }

    public RangeDateTimeDto(@DateTimeFormat(pattern = DateTimeConst.DATETIME) LocalDateTime startAt, @DateTimeFormat(pattern = DateTimeConst.DATETIME) LocalDateTime endAt) {
        if (startAt == null) this.startAt = LocalDateTime.now().minusMonths(1L);
        else this.startAt = startAt;

        if (endAt == null) this.endAt = LocalDateTime.now();
        else this.endAt = endAt;
    }

    @Getter
    @Setter
    @ToString
    public static class CreatedRangeDateTimeDto {
        @DateTimeFormat(pattern = DateTimeConst.DATE)
        private LocalDate startAt;
        @DateTimeFormat(pattern = DateTimeConst.DATE)
        private LocalDate endAt;

        public CreatedRangeDateTimeDto() {
            this.startAt = LocalDate.now().minusMonths(1L);
            this.endAt = LocalDate.now();
        }

        public CreatedRangeDateTimeDto(LocalDate startAt, LocalDate endAt) {
            if (startAt == null) this.startAt = LocalDate.now().minusMonths(1L);
            this.startAt = startAt;
            if (endAt == null) this.endAt = LocalDate.now();
            this.endAt = endAt;
        }
    }
}
