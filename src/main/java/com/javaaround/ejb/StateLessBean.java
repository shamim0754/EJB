package com.javaaround.ejb;
import javax.ejb.Stateful;
import javax.annotation.PostConstruct;
@Stateful
public class StateLessBean implements StateLessRemote{
	public int count=1;
	@Override
	public int accessCount(){
		this.count++;
		return this.count;
	}
	
}