package com.augus.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisStringExRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String key;
    String value;
    Long expireTime;
}
