package com.smitsworks.easytour.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redlongcity
 * 04/12/2017
 * class for getting connection data for openshift red head mysql cartridge
 */
public class ConnectionManager {

    private static final Logger LOG = Logger.getLogger(ConnectionManager.class.getName());

    public String getUser() {
        String user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        LOG.log(Level.INFO, "User Name: "+user);
        return user;
    }

    public String getHost() {
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        LOG.log(Level.INFO, "Host: " + host);
        return host;
    }

    public String getPort() {
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        LOG.log(Level.INFO, "Port: " + port);
        return port;
    }

    public String getPassword() {
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        LOG.log(Level.INFO, "Password: " + password);
        return password;
    }

    public String getApplicationName() {
        String name = System.getenv("OPENSHIFT_APP_NAME");
        LOG.log(Level.INFO, "Application name: " + name);
        return name;
    }

    public String getUrl() {
        if (getHost() == null) {
            return null;
        }
        String url = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getApplicationName();
        LOG.log(Level.INFO, url);
        return url;
    }

}
