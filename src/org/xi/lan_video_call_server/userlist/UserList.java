package org.xi.lan_video_call_server.userlist;

import org.xi.lan_video_call_server.pojos.User;

import java.util.*;

public class UserList {

    private static UserList instance;

    public static UserList getInstance() {
        if(instance == null) {
            instance = new UserList();
        }
        return instance;
    }

//    private final List<User> userList;

    private final HashMap<String,User> userHashMap;

    private UserList() {
//        userList = new ArrayList<>();
        userHashMap = new HashMap<>();
    }

    public List<User> getUserList() {
        long one_day_millis = 1000 * 60 * 60 * 24;
        List<User> new_list_user = new ArrayList<>();
        long now_time_millis = System.currentTimeMillis();

        List<String> need_delete_datas = new ArrayList<>();

        Iterator entries = userHashMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            User value = (User)entry.getValue();

            long user_last_online_time = value.last_online_time;
            if(now_time_millis - user_last_online_time <= 2 * 60 * 1000) {
                value.is_online = true;
            }else {
                value.is_online = false;
            }

            if(now_time_millis - value.last_online_time <= one_day_millis) {
                new_list_user.add((User) value.clone());
            }else {
                System.out.println("need delete:"+key);
                need_delete_datas.add(key);
            }

        }

        System.out.println("all_user_size"+new_list_user.size());

        for (int i = 0 ; i < need_delete_datas.size() ; i ++) {
            String str = need_delete_datas.get(i);
            userHashMap.remove(str);
        }

        return new_list_user;
    }

    public boolean addOrUpdateUserInformation(User user) {
        boolean isAddSuccess = true;
        if(user != null) {
            synchronized (userHashMap) {
                String key = user.ip;
                User oldUser = userHashMap.get(key);
                if(oldUser != null) {
                    // is oldUser
                    oldUser.uuid = user.uuid;
                    oldUser.name = user.name;
                    oldUser.ip = user.ip;
                    oldUser.last_online_time = System.currentTimeMillis();
                }else {
                    // is new user
                    user.last_online_time = System.currentTimeMillis();
                    userHashMap.put(key,user);
                }
            }
        }
        return isAddSuccess;
    }

}
