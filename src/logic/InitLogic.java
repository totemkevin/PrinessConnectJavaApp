package logic;

import util.HibernateUtil;

public class InitLogic {
	public InitLogic() {
		System.out.println("InitLogic constructor");
	}
	public String checkDataBaseStatus() {
		System.out.println("checkDataBaseStatus");
		try {
			HibernateUtil.getSessionFactory().openSession();
			TeamLogic t = new TeamLogic();
			t.getTeams();
			return "active";
		}catch(ExceptionInInitializerError e) {
			return "closed";
		}
	}
}
