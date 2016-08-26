package com.pb.server;

import com.pb.server.model.Message;
import com.pb.server.redisUtil.RedisUtil;

/**
 * Created by piecebook on 2016/8/26.
 */
public class MainTest {

    public static void main(String[] args){
        RedisUtil redisUtil = (RedisUtil)ContexHolder.getBean("redisUtil");
        Message message = new Message();
        message.setType((byte)1);
        message.setMsg_id(13);
        message.setLength(3);
        message.setParam("s_uid","lili");
        message.setParam("r_uid","lala");
        redisUtil.setForAHash("message",message.get("s_uid")+message.getMsg_id(),message);//TODO: 序列化出现问题

        Message msg = (Message)redisUtil.getForAHash("message",message.get("s_uid")+message.getMsg_id());
        System.out.println(message.toString());
        System.out.println(msg.toString());

    }
}
