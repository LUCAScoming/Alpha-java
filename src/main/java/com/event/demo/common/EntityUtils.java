package com.event.demo.common;

import java.util.UUID;

public class EntityUtils {
    public static String genUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
