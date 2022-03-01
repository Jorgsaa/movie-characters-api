package com.example.moviecharactersapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private Boolean success = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    public Response(T payload) {
        this.payload = payload;
    }

    public Response(String error) {
        this.success = false;
        this.error = error;
    }

}
