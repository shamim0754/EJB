package com.javaaround.ejb;

import javax.ejb.*;
import java.rmi.*;
public interface HelloWorldHome extends EJBHome{
   public HelloWorldRemote create() throws RemoteException,CreateException;
}