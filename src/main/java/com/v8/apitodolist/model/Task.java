package com.v8.apitodolist.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash
public class Task implements Serializable {

    @Id
    @Indexed
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

}
