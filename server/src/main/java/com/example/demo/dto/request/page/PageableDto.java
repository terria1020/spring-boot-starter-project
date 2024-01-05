package com.example.demo.dto.request.page;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableDto {
    @JsonProperty(value = "pageSize")
    @JsonAlias(value = "pageSize")
    private Integer size;
    @JsonProperty(value = "pageOffset")
    private Integer offset;

    public PageableDto() {
        this.size = 30;
        this.offset = 1;
    }

    public PageableDto(Integer size, Integer offset) {
        this.size = size;
        this.offset = offset;
    }

    // JPA 의존성 추가, 주석 해제 후 사용:
    // public Pageable pageable() {
    //     return PageRequest.of(offset - 1, size);
    // }

    public int firstResult() {
        return (this.offset - 1) * this.size;
    }
}
