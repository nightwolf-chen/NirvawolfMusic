/*
 *  Copyright 2013 Jidong Chen, Inc. All rights reserved.
 * 
 */
package db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nightwolf 陈纪栋
 */
public class EasyDatabaseAccessor {

    private static ConnectionManager connnectionMgr = new ConnectionManager();

    public static Statement createStatement(Connection con) {
        Statement stmt = null;
        try {
            stmt = (Statement) con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EasyDatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }

    public static ResultSet select(Statement stmt,String sql) {
        
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EasyDatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static boolean insert(Statement stmt,String sql) {
        try {
            return stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EasyDatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static int update(Statement stmt,String sql){

        try {
            return stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EasyDatabaseAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static int delete(Statement stmt,String sql){
         return update(stmt,sql) ;
    }
}
