<%-- 
    Document   : index
    Created on : Feb 18, 2014, 7:33:52 PM
    Author     : bruce
--%>

<%@page import="java.sql.DriverManager"%>
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
            ConnectionManager conMgr = new ConnectionManager();
            Connection con = conMgr.getConnection();
            
            Statement stmt = EasyDatabaseAccessor.createStatement(con);
            ResultSet rs = EasyDatabaseAccessor.select(stmt, "select count(songid) from song");
            
            while(rs.next()){
                out.println("Database connected!");
                break;
            }
            
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
            con.close();
            con = null;

        %>
    </body>
</html>
