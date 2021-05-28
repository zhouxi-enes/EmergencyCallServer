package org.xi.lan_video_call_server.servlets;

import com.google.gson.Gson;
import org.xi.lan_video_call_server.defines.ErrorCodeDefines;
import org.xi.lan_video_call_server.pojos.NumberOnlyReturnPOJO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetVersionServlet extends MyServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String package_name = req.getParameter("pkg_name");
        String version_name = req.getParameter("v_name");
        NumberOnlyReturnPOJO numberOnlyReturnPOJO = new NumberOnlyReturnPOJO();
        if(package_name != null && !package_name.isEmpty() && version_name != null && !version_name.isEmpty()) {
            numberOnlyReturnPOJO.status = ErrorCodeDefines.STATUS_OK;
        }else {
            numberOnlyReturnPOJO.status = ErrorCodeDefines.STATUS_PARAMETERS_ERROR;
        }
        Gson gson = new Gson();
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(gson.toJson(numberOnlyReturnPOJO));
    }

}
