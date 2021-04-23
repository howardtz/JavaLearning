import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    private static Connection dbConn = null;

    public static void main(String[] args) {
        String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sa";

        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            dbConn = DriverManager.getConnection(dbURL, "sa", "1234T");
            System.out.println("连接数据库成功！");
//            String sql="select * from people";
//            PreparedStatement statement=null;
//            statement=dbConn.prepareStatement(sql);
//            ResultSet res=null;
//            res=statement.executeQuery();
//            while(res.next()){
//                String title=res.getString("name");
//                System.out.println(title);
//            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }

    }
}
