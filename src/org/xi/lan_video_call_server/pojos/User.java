package org.xi.lan_video_call_server.pojos;

import java.io.Serializable;

public class User implements Serializable, Cloneable {

    public String uuid;

    public String name;

    public String ip;

    public long last_online_time;

    public boolean is_online;


    @Override
    public Object clone() {
        try {
            User user = (User) super.clone();
            user.uuid = uuid;
            user.last_online_time = last_online_time;
            user.ip = ip;
            user.name = name;
            user.is_online = is_online;
            return user;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
