package Part1;

import java.util.*;

public class SExp {
	int type=0;
	int val=0;
	boolean isNIL=false;
	String name="";
	SExp left,right;
	static HashMap<String,SExp> symbolicAtomList=new HashMap<String,SExp>();
	public SExp() {
		isNIL=true;
		type=0;
	}
	public SExp(SExp a, SExp b) {
		left=a;
		right=b;
		type=3;
	}
	public SExp(int value) {
		type=1;
		val=value;
		left=null;
		right=null;
	}
	public SExp(String nameval) {
		if(nameval.equalsIgnoreCase("nil")) {
			isNIL=true;
			type=0;
		}
		else {
			name=nameval;
			type=2;
			left=null;
			right=null;
		}
	}
	public boolean getIsNil() {
		return isNIL;
	}
}
