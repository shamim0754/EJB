package com.javaaround.ejb;
import javax.ejb.Stateless;
import javax.annotation.PostConstruct;
@Stateless
public class StateLessBean implements StateLessRemote{
	
	@Override
	public boolean isNumber(String phone){
		return phone.matches("[0-9]+");
	}
}