package example.com.cv4j.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhigang on 2018/1/2.
 */

public class EventData {
    private Map<String, Object> data;

    public EventData() {
        data = new HashMap<>();
    }

    public void putData(String name, Object obj) {
        data.put(name, obj);
    }

    public Object getData(String name) {
        return data.get(name);
    }

    public String getString(String name) {
        return (String)data.get(name);
    }

    public boolean getFileOpen(String name) {
        String cmd = (String)data.get(name);
        return cmd.equals(MenuConstants.FILE_OPEN);
    }

    public int getInt(String name) {
        Integer i = (Integer)data.get(name);
        return i.intValue();
    }

    public double getDouble(String name) {
        Double i = (Double)data.get(name);
        return i.doubleValue();
    }

    public float getFloat(String name) {
        Float i = (Float)data.get(name);
        return i.floatValue();
    }

}
