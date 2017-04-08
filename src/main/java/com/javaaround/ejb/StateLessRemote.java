package com.javaaround.ejb;
import java.rmi.*;
import java.util.*;
import javax.ejb.Remote;
@Remote
public interface StateLessRemote{
   public int accessCount();
}