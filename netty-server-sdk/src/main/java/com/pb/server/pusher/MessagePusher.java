package com.pb.server.pusher;

import com.server.model.Message;

/**
 * Created by piecebook on 2016/8/8.
 */
public interface MessagePusher {
    public void push(Message msg);
}
