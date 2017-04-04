package com.javaaround.ejb;
import javax.ejb.Stateful;
import javax.annotation.PostConstruct;
@Stateful
public class StateFulBean implements StateFulRemote{
	public int count=1;
	@Override
	public int accessCount(){
		return count;
	}
	@Override
	public void increment() {
        this.count++;
    }
}