
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
import java.util.Scanner;

public class DBHelper {

    private static Connection conn = null;
    public static MyModelInteliJ model = null;
    public static PreparedStatement state = null;
    public static ResultSet result = null;

    public static Connection getConnection() {

        try {
            String driver = "";
            String url = "";
            String user = "";
            String password = "";


            File config = new File("C:\\Users\\SUPER-PC\\Desktop\\config.txt");

            Scanner scanner = new Scanner(config);

            while (scanner.hasNextLine()){
                driver = scanner.nextLine().trim();
                url = scanner.nextLine().trim();
                user = scanner.nextLine().trim();
                password = scanner.nextLine().trim();
                break;

            }


            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return conn;

    }//end method getConnection

    public static void refreshTable(String name, JTable table) {
        conn= getConnection();
        String str="select * from "+name;

        try {
            state=conn.prepareStatement(str);
            result=state.executeQuery();
            table.setModel(new MyModelInteliJ(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}//end class DBHelper
