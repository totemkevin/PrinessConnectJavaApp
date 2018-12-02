package thread;

import java.util.Observable;
import logic.InitLogic;
import util.HibernateUtil;

public class DBConnectThread extends Observable implements Runnable{
	private String status;

	public String getStatus() {
		return status;
	}
	 public void run(){
		 HibernateUtil.getSessionFactory().openSession();
		 InitLogic initLogic =new InitLogic();
		 status = initLogic.checkDataBaseStatus();
		 super.setChanged();
		 notifyObservers();
	 }
}
