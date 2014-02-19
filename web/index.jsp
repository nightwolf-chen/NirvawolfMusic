<%-- 
    Document   : index
    Created on : Feb 18, 2014, 7:33:52 PM
    Author     : bruce
--%>

<%@page import="javax.sql.RowSet"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="db.EasyDatabaseAccessor"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="db.ConnectionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Install Test Page</title>
    </head>
    <body>
        <%
            String databaseInfo = java.lang.System.getenv("VCAP_SERVICES");
            out.print(databaseInfo + "<br>");
            ConnectionManager conMgr = new ConnectionManager();
            out.println(conMgr.getConnectionStr());
            Connection con = conMgr.getConnection();
            if (con == null) {
                out.println("Connection failed!");
            } else {
                out.println("Connected!");
            }

            if (databaseInfo != null) {
                JSONObject jsonObject = new JSONObject(databaseInfo);
                JSONArray mysqlServices = jsonObject.getJSONArray("mysql-5.1");
                JSONObject mysqlObj = mysqlServices.getJSONObject(0);
                JSONObject credentials = mysqlObj.getJSONObject("credentials");

                String ServerHost = credentials.getString("host");
                String port = String.valueOf(credentials.getInt("port"));
                String DatabaseName = credentials.getString("name");
                String userName = credentials.getString("username");
                String passWord = credentials.getString("password");

                out.print("<br>" + userName + "<br>");
                out.print("<br>" + passWord + "<br>");
            }
//            Statement stmt = EasyDatabaseAccessor.createStatement(con);
//            ResultSet rs = EasyDatabaseAccessor.select(stmt, "select count(singerid) from singer");
//            
//            while(rs.next()){
//                out.println("Database connected!");
//                break;
//            }
//            
//            rs.close();
//            rs = null;
//            stmt.close();
//            stmt = null;
//            con.close();
//            con = null;


        %>
    </body>
</html>
