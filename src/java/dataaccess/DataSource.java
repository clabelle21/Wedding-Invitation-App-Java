package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class DataSource {

    public Connection createConnection() {
        Connection connection = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            javax.sql.DataSource ds = (javax.sql.DataSource) envCtx.lookup("jdbc/reception");
            connection = ds.getConnection();
            System.out.println("Connection is successful.");
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection unsuccesful.");
        }
        return connection;
    }
}
