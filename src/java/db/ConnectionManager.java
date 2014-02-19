/*
 *  Copyright 2013 Jidong Chen, Inc. All rights reserved.
 * 
 */
package db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author nightwolf 陈纪栋
 */
public class ConnectionManager {

    private final String driver = "com.mysql.jdbc.Driver";
    private String DatabaseName = "nightwolf_music";
    private final String Encode = "utf8";
    private String ServerHost = "localhost";
    private String port = "3306";
    private String conStr = "jdbc:mysql://" + ServerHost + ":" + port + "/" + DatabaseName + "?characterEncoding=" + Encode;
    private String userName = "root";
    private String passWord = "123";
    
/*
    {"mysql-5.1":[
    {
        "name":"mysql-4f700",
        "label":"mysql-5.1",
        "plan":"free",
        "tags":["mysql","mysql-5.1","relational"],
        "credentials":{
            "name":"d6d665aa69817406d8901cd145e05e3c6",
            "hostname":"mysql-node01.us-east-1.aws.af.cm",
            "host":"mysql-node01.us-east-1.aws.af.cm",
            "port":3306,
            "user":"uB7CoL4Hxv9Ny",
            "username":"uB7CoL4Hxv9Ny",
            "password":"pzAx0iaOp2yKB"
        }
    },
    {
        "name":"mysql-f1a13",
        "label":"mysql-5.1",
        "plan":"free",
        "tags":["mysql","mysql-5.1","relational"],
        "credentials":{
            "name":"db777ab9da32047d99dd6cdae3aafebda",
            "hostname":"mysql-node01.us-east-1.aws.af.cm",
            "host":"mysql-node01.us-east-1.aws.af.cm",
            "port":3306,
            "user":"uJHApvZF6JBqT",
            "username":"uJHApvZF6JBqT",
            "password":"p146KmfkqGYmi"
        }
    }
]}
*/
    
    public ConnectionManager() {
        //VCAP_SERVICES
        String databaseInfo = java.lang.System.getenv("VCAP_SERVICES");
        
        if (databaseInfo != null) {
            try {
                JSONObject jsonObject = new JSONObject(databaseInfo);
                JSONObject mysqlObj = jsonObject.getJSONArray("mysql-5.1").getJSONObject(0);
                JSONObject credentials = mysqlObj.getJSONObject("credentials");
                
                this.ServerHost = credentials.getString("hostname");
                this.port = String.valueOf(credentials.getInt("port"));
                this.DatabaseName = credentials.getString("name");
                this.userName = credentials.getString("username");
                this.passWord = credentials.getString("password");
                
            } catch (JSONException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection(conStr, userName, passWord);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public String getConnectionStr() {
        return "jdbc:mysql://" + ServerHost + ":3306/" + DatabaseName + "?CharacterEncoding=" + Encode;
    }

}
