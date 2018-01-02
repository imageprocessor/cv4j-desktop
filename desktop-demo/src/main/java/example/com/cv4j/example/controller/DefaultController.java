package example.com.cv4j.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import example.com.cv4j.example.model.EventData;

/**
 * Created by zhigang on 2018/1/2.
 */

public class DefaultController implements ActionListener {

    private EventHandler eventHandler;

    public DefaultController(EventHandler handler) {
        this.eventHandler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent cmd) {
        EventData ed = new EventData();
        ed.putData(cmd.getActionCommand(), cmd.getActionCommand());
        eventHandler.handleEvent(ed);
    }
}
