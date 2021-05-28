package org.xi.lan_video_call_server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceStartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("contextDestroyed");
    }

}
