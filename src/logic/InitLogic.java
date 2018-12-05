package logic;

import service.TeamMemberService;
import util.HibernateUtil;

public class InitLogic {
	public InitLogic() {
	}
	public String checkDataBaseStatus() {
		try {
			HibernateUtil.getSessionFactory().openSession();
			System.out.println("asdads");
			TeamMemberService TeamLogic = new TeamMemberService();
			TeamLogic.findAll();
			return "active";
		}catch(ExceptionInInitializerError e) {
			return "closed";
		}
	}
}
