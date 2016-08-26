package com.pb.server.MessageFactory;

import com.server.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by DW on 2016/8/8.
 */
public class MessageHolder {
    public static Map<String,Message> send_messages = new HashMap<String,Message>();
    public static LinkedBlockingDeque<String> rec_messages = new LinkedBlockingDeque<String>();
}
