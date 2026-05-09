package function;

import java.util.ArrayList;

import entity.State;

public interface IStateUtil {
	public State findByAb(String abb);
	public ArrayList<State> findByYear(int year);
	public void close();
}
