package com.javaaround.ejb;
import javax.ejb.*;
import java.util.*;

public class HelloWorldBean implements SessionBean{
	public String hello(){
	     return "Hello World EJB";
	}
	public void ejbCreate() {
		System.out.println( "Ejb create" );
	} 
	public void ejbRemove() {
		System.out.println( "Ejb remove" );
	}
	public void ejbActivate() {
		System.out.println( "Ejb Activate" );
	}
	public void ejbPassivate() {
		System.out.println( "Ejb Passivate" );
	}
	public void setSessionContext(SessionContext sc){
		System.out.println( "SessionContext" );
	}
}