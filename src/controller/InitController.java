package controller;

import logic.InitLogic;

public class InitController {
	Integer count = 0;

	public Integer count(int i) {
		count = count + i;
		return count;
	}
	
	public String checkDataBaseStatus() {
		InitLogic logic = new InitLogic();
		return logic.checkDataBaseStatus();
	}
}
