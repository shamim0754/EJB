package com.javaaround.ejb;
import javax.ejb.Stateless;
@Stateless
public class HelloWorldBean implements HelloWorldRemote{
	@Override
	public String hello(){
	     return "Hello World EJB";
	}
}