package com.javaaround.ejb;
import javax.ejb.*;
import java.util.*;

public class HelloWorldBean implements SessionBean{
	public String hello(){
	     return "Hello World EJB";
	}
	public void ejbCreate() {

	} 
	public void ejbRemove() {

	}
	public void ejbActivate() {

	}
	public void ejbPassivate() {

	}
	public void setSessionContext(SessionContext sc){

	}
}