package com.kidari.lecture.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResponseDto<T> extends BaseDto {

    private T data;

    public ResponseDto(T object) {
        this.setSuccess(true);
        this.setData(object);
    }
}
