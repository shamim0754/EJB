package com.javaaround.ejb;
import javax.ejb.*;
import java.rmi.*;
import java.util.*;
import javax.ejb.Remote;
@Remote
public interface HelloWorldRemote{
   public String hello();
}