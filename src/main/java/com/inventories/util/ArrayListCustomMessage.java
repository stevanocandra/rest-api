package com.inventories.util;

import com.inventories.model.CustomMessage;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCustomMessage {
    public static List<CustomMessage> setMessage(String message, HttpStatus status){
        CustomMessage cm = new CustomMessage();
        cm.setStatusCode(status.value());
        cm.setMessage(message);
        List<CustomMessage> customMessageList = new ArrayList<CustomMessage>();
        customMessageList.add(cm);
        return customMessageList;
    }
}
