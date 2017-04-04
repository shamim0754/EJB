package com.javaaround.ejb;
import java.rmi.*;
import java.util.*;
import javax.ejb.Remote;
@Remote
public interface StateFulRemote{
   public int accessCount();
   public void increment();
}