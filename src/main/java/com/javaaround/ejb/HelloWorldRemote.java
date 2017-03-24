package com.javaaround.ejb;
import javax.ejb.*;
import java.rmi.*;
import java.util.*;
public interface HelloWorldRemote extends EJBObject{
   public String hello() throws RemoteException;
}