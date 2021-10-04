package com.paystock.messaging.api.utils;

public class MessageValidator {
    public static void valid(String val){
        if(val.trim().isEmpty()){
            throw new IllegalArgumentException("공백을 입력할 수 없습니다.");
        }
    }
}
