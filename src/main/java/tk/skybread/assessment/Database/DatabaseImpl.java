package tk.skybread.assessment.Database;

import org.sqlite.SQLiteConfig;
import tk.skybread.assessment.Main;
import tk.skybread.assessment.view.LoggerTab;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;

public class DatabaseImpl {
    private static DatabaseImpl instance;

    // connect
    public static Connection getconnection() {
		Connection connection = null;
		SQLiteConfig sc = new SQLiteConfig();
		sc.setEncoding(SQLiteConfig.Encoding.UTF_8);
	    Path currentRelativePath = Paths.get("");
	    String s = currentRelativePath.toAbsolutePath().toString();
	    try {
		    connection = DriverManager.getConnection("jdbc:sqlite:citys.db", sc.toProperties());
		    Statement maintable = connection.createStatement();
		    maintable.execute("CREATE TABLE IF NOT EXISTS citys (id integer AUTO_INCREMENT PRIMARY KEY, name string, desc string, temp integer)");
		    LoggerTab.Logger(Level.INFO,": Opened database file: " + s + "/citys.db");
	    } catch (SQLException ex) {
	        LoggerTab.Logger(Level.SEVERE, "Failed to create the database connection.");
	        LoggerTab.Logger(Level.SEVERE, ex.toString());
	    }
	    return connection;
    }

    public static DatabaseImpl getInstance() {
        return instance;
    }

    public static void addToDb(int id, String city, String desc, int temp) {
        try {
            Statement ps = getconnection().createStatement();
	        ps.setQueryTimeout(30);
            ps.executeUpdate("insert into citys values(" + id + ",'" + city + "', '" + desc + "', '" + temp + "')");
        }catch(SQLException e){
            LoggerTab.Logger(Level.SEVERE, e.toString());
        }
    }

    public static void UpdateTempToDb(String city, int temp) {
        try {
            Statement ps = getconnection().createStatement();
            ps.setQueryTimeout(30);
            ps.executeUpdate("UPDATE citys SET temp= " + temp +" WHERE name = " + city);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int numberOfRows() {
        int count = 0;
        try {
            PreparedStatement ps = getconnection().prepareStatement("SELECT COUNT(*) FROM cities");
            ps.setQueryTimeout(30);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ResultSet CitysFromId(int ID) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = getconnection().prepareStatement("SELECT * FROM citys WHERE id = " + ID);
	        ps.setQueryTimeout(30);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet descFromId(int ID) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = getconnection().prepareStatement("SELECT desc FROM citys WHERE id = " + ID);
            ps.setQueryTimeout(30);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
