package logic;

import util.HibernateUtil;

public class InitLogic {
	public String checkDataBaseStatus() {
		try {
			HibernateUtil.getSessionFactory().openSession();
			return "active";
		}catch(ExceptionInInitializerError e) {
			return "closed";
		}
	}
}
