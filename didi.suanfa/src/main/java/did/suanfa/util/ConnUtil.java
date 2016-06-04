package did.suanfa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class ConnUtil {

	private static String url = "jdbc:mysql://192.168.241.130:3306/test";
	private static String driveName = "com.mysql.jdbc.Driver";
	private static String username = "test";
	private static String password = "111111";
	//private static Connection conn = getCon();
	//private static Statement st  = null;
	
	public static void batchUpdate(List<String > lst){
		try {
			Connection conn = getCon();
			Statement st = conn.createStatement();
			conn.setAutoCommit(true);
			for(String sql : lst){
				//System.out.println();
				st.addBatch(sql);
			}
			st.executeBatch();
			//conn.commit();
            st.clearBatch();     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConn(){
/*		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}
	
	public synchronized static Connection getCon(){
		try{    
		    //加载MySql的驱动类    
		    Class.forName(driveName) ;    
		    Connection con = DriverManager.getConnection(url,username, password);
            return con;
		    
	    }catch(ClassNotFoundException e){    
		    System.out.println("找不到驱动程序类 ，加载驱动失败！");    
		    e.printStackTrace() ;    
		    throw new RuntimeException("找不到驱动程序类 ，加载驱动失败！");	
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
	    	System.out.println("获得数据库连接失败！");  
	    	e.printStackTrace();
	    	throw new RuntimeException("获得数据库连接失败！");		
		}  
	}
	
	
	/**
	 * @param args
	 * @return
	 */
	public static int[] getSqlTypeFromArgs(Object[] args) {
		int types[] = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				types[i] = Types.VARCHAR;
			} else if (args[i] instanceof java.sql.Timestamp) {
				types[i] = Types.TIMESTAMP;
			} else if (args[i] instanceof java.sql.Date) {
				types[i] = Types.DATE;
			} else if (args[i] instanceof java.sql.Time) {
				types[i] = Types.TIME;
			} else if (args[i] instanceof java.math.BigDecimal) {
				types[i] = Types.DECIMAL;
			} else if (args[i] instanceof Integer) {
				types[i] = Types.INTEGER;
			} else if (args[i] instanceof Long) {
				types[i] = Types.BIGINT;
			} else if (args[i] instanceof Short) {
				types[i] = Types.SMALLINT;
			} else if (args[i] instanceof Float) {
				types[i] = Types.FLOAT;
			} else if (args[i] instanceof Double) {
				types[i] = Types.DOUBLE;
			} else if (args[i] instanceof byte[]) {
				types[i] = Types.BLOB;
			} else {
				types[i] = Types.VARCHAR;
			}
		}
		return types;
	}
	
	public static void closeConn(PreparedStatement st,Connection conn){
		/*if(st!=null){
			try {
				//st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void closeConn(Statement st,Connection conn){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         double d1 =1.01;
         double d2 = 1.03;
         System.out.println((d1+d2));
	}

}
