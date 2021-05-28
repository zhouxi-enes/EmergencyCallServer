package org.xi.lan_video_call_server.servlets;

import com.google.gson.Gson;
import org.xi.lan_video_call_server.defines.ErrorCodeDefines;
import org.xi.lan_video_call_server.pojos.User;
import org.xi.lan_video_call_server.pojos.UserListReturnPOJO;
import org.xi.lan_video_call_server.userlist.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserListServlet extends MyServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        List<User> userList = UserList.getInstance().getUserList();
        UserListReturnPOJO userListReturnPOJO = new UserListReturnPOJO();
        userListReturnPOJO.status = ErrorCodeDefines.STATUS_OK;
        userListReturnPOJO.users = userList;
        Gson gson = new Gson();
        String return_str = gson.toJson(userListReturnPOJO);
        resp.getWriter().print(return_str);
    }

}
