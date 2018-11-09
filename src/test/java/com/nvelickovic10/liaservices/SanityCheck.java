package com.nvelickovic10.liaservices;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SanityCheck {
	public static void main(String[] args) {
		String str = "function a() {return 6;}; a();";
		try {
			Object res = new ScriptEngineManager().getEngineByName("js").eval(str);
			System.out.println(res);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
