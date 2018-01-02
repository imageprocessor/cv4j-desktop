package example.com.cv4j.example.controller;

import example.com.cv4j.example.model.EventData;

/**
 * Created by zhigang on 2018/1/2.
 */

public interface EventHandler {

    public void handleEvent(EventData event);
}
