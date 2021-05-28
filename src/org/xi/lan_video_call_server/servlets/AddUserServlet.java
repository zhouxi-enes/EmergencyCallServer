package org.xi.lan_video_call_server.servlets;

import com.google.gson.Gson;
import org.xi.lan_video_call_server.defines.ErrorCodeDefines;
import org.xi.lan_video_call_server.pojos.NumberOnlyReturnPOJO;
import org.xi.lan_video_call_server.pojos.User;
import org.xi.lan_video_call_server.userlist.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends MyServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        handle(req,resp);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String uuid = request.getParameter("uuid");
        String resp_str = "";
        Gson gson = new Gson();
        NumberOnlyReturnPOJO numberOnlyReturnPOJO = new NumberOnlyReturnPOJO();
        // check user is exists
        if(name != null && !name.isEmpty() && uuid != null && !uuid.isEmpty()) {
            String ip = request.getParameter("ip");
            if(ip == null) {
                ip = request.getRemoteAddr();
            }
            User user = new User();
            user.ip = request.getRemoteHost();
            user.name = name;
            user.uuid = uuid;
            boolean is_add_or_update_information_successful = UserList.getInstance().
                    addOrUpdateUserInformation(user);
            if(is_add_or_update_information_successful) {
                numberOnlyReturnPOJO.status = ErrorCodeDefines.STATUS_OK;
            }else {
                numberOnlyReturnPOJO.status = ErrorCodeDefines.STATUS_ADD_OR_UPDATE_USER_INFORMATION_FAILED_ERROR;
            }
        }else {
            numberOnlyReturnPOJO.status = ErrorCodeDefines.STATUS_PARAMETERS_ERROR;
        }
        try {
            resp_str = gson.toJson(numberOnlyReturnPOJO);
            response.getWriter().print(resp_str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
