package com.javaaround.ejb;
import javax.ejb.Stateless;
import javax.annotation.PostConstruct;
@Stateless
public class StateLessBean implements StateLessRemote{
	int data = 5;
	@PostConstruct
	public void increment(){
	     data++;
	}
	@Override
	public int getData(){
		return data;
	}
}