package did.suanfa.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String id = RmIdFactory.getInstanse().requestId("");
		//Thread t1 = new 
		//Runable2 run1 = new Runable2(100,"1","2013-09-10","zhangjsh1","订单20130910-");
		CreateData cd = new CreateData();
		cd.crateThread();
	}
	
	public void crateThread(){
		Runable2 run1 = new Runable2(5000000,"1","2012-09-10","zhangjsh1","订单20120910-");		
		run1.start();
		Runable2 run2 = new Runable2(5000000,"2","2011-08-10","wanghuang","订单20110810-");		
		run2.start();
		Runable2 run3 = new Runable2(5000000,"3","2012-10-12","huanghai","订单20121012-");		
		run3.start();
		Runable2 run4 = new Runable2(5000000,"4","2013-01-10","hushaolin","订单20130110-");		
		run4.start();
		Runable2 run5 = new Runable2(5000000,"5","2014-01-10","zhangcj","订单20140110-");		
		run5.start();
		Runable2 run6 = new Runable2(5000000,"6","2014-02-10","xyixi","订单20140210-");		
		run6.start();
		Runable2 run7 = new Runable2(5000000,"7","2014-04-10","liuxi","订单20140410-");		
		run7.start();
		Runable2 run8 = new Runable2(5000000,"8","2011-06-10","lucl","订单20110610-");		
		run8.start();
		//System.out.println("总时间："+(System.currentTimeMillis()-start));
	}

	//可以进行资源共享
	//class Runable2 implements Runnable{
	class Runable2 extends Thread{
		private int m = 0;
		private String id ;
		private String dateStr;
		private String user;
		private String orderStr;
		
        public Runable2(int num,String id,String dateStr,String user,String orderStr){
            this.m = num;
            this.id = id;
            this.dateStr = dateStr;
            this.user = user;
            this.orderStr = orderStr;
        }
        
		public void run() {
			long start = System.currentTimeMillis();
			System.out.println("start:"+start);
			Connection conn  = null;
			Statement st = null;
			//RmIdFactorys idf = new RmIdFactorys();
			StringBuilder sb= new StringBuilder("INSERT INTO user_order (id, order_name, create_date, update_time, order_state, create_user) VALUES ");
			try{
				conn = ConnUtil.getCon();
				st = conn.createStatement();
				conn.setAutoCommit(true);

			}
			catch(Exception e){
				System.out.println(sb.toString());
				e.printStackTrace();
			}
			finally{
				if(st!=null){
					try {
						st.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("End:"+System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName()+"--"+(System.currentTimeMillis()-start));
		    
		}
		
	}
	
	public class RmIdFactorys {		
		private  long ONE_STEP = 1000;
		private  long lastTime = System.currentTimeMillis();
		private  short count = 0;

	    public  String requestIdNotSyn(String tableName){
	    	try{
	    		if(count == ONE_STEP){
	    			boolean done = false;
	    			while(!done){
	    				long now = System.currentTimeMillis();
	    				if(now == lastTime ){
	    					try{
	    						Thread.sleep(1);
	    					}catch (java.lang.InterruptedException e){
	    						
	    					}
	    				}else{
	    					lastTime = now;
	    					count = 0;
	    					done = true;
	    				}
	    			}
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	String temp = "";
	    	if(count<10){
	    		temp += "000"+count;
	    	}
	    	else if(count<100 && count >=10){
	    		temp += "00"+count;
	    	}else{
	    		temp += "0"+count;
	    	}
	    	count ++ ;
	    	String result =  lastTime + temp;
	    	return result;
	    }		    
	}	
	
	
}
