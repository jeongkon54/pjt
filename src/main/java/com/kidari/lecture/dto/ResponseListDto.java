package com.kidari.lecture.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseListDto<T> extends BaseDto {

    private List<T> data;

    private int count;

}
