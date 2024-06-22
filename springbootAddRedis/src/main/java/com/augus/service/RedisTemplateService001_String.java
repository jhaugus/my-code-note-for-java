package com.augus.service;

public interface RedisTemplateService001_String {


    void setString(String key, String value);

    boolean setnxString(String key, String value);

    void setexString(String key, String value, long expireTime);
}
