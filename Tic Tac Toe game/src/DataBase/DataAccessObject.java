package DataBase;

import DTO.UserData;
import org.apache.derby.jdbc.ClientDriver;
import java.sql.*;
import java.util.ArrayList;

public class DataAccessObject {
    private static String dbURL;
    private static String dbName;
    private static String dbPassword;
    private static Connection con;
    
    public static void start() throws SQLException {
        dbURL = "jdbc:derby://localhost:1527/Tic Tac Toe Game";
        dbName = "java";
        dbPassword = "j1a2v3a4"; 
        DriverManager.registerDriver(new ClientDriver());
        con = DriverManager.getConnection(dbURL, dbName, dbPassword);
    }
    
    public static ArrayList<UserData> getAvailableUser() throws SQLException {
        ArrayList<UserData> availableUsers = new ArrayList<UserData>();
        
        String sqlStat = "select * from UserData WHERE is_Available = true and is_OnGame = false";
        PreparedStatement pst = con.prepareStatement(sqlStat);
        ResultSet rs = pst.executeQuery(sqlStat);
        while (rs.next()) {
            UserData user = new UserData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                        rs.getLong(5), rs.getBoolean(6), rs.getBoolean(7));
            availableUsers.add(user);
        }
        return availableUsers;
    }
    
    public static int addUser(UserData user) throws SQLException {
        
        return 0;
    }
    
    public static UserData getUser(String email, String password) throws SQLException {
        
        return null;
    }
    
    public static int getNextID() throws SQLException {
        int id;
        String sqlStat = "select max(id) from userdata";
        PreparedStatement pst = con.prepareStatement(sqlStat);
        ResultSet rs = pst.executeQuery();
        
        if(rs.next())
            id = rs.getInt(1) + 1;
        else 
            id = 1;
        
        pst.close();
        rs.close();
        return id;
    }
    
    public static int updateScore(UserData user) throws SQLException {
        
        return 0;
    }
    
    public static int updateStatus(UserData user) throws SQLException {
        
        return 0;
    }
    
    public static void stop() throws SQLException {
        con.close();
    }
    
}