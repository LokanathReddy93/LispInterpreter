package Part1;

import java.util.*;

public class MyParser {
	String inputLine;
	static String errorMessage="";
	String[] parsedLine;
	ArrayList<String> parsedList;
	//static SExp aList=null;
	int index=0;
	int size=0;
	public int chkToken(String s) {
		if(s.equals("(")) return 1;
		if(s.equals(")")) return 2;
		if(s.equals(".")) return 3;
		if(s.matches("^[+-]?\\d+$")) {
			return 4;
		}
		if(s.equals("")) return 6;
		else return 5;
	}
	public MyParser(String inp) {
		index=0;
		inputLine=inp.trim().replaceAll("(\\.\\s+)|(\\s+\\.)","\\.").replaceAll("(\\(\\s+)","\\(").replaceAll("(\\s+\\))","\\)");
		//inputLine;//.replaceAll("(\\s+\\))","\\)");
		parsedLine=inputLine.split("(\\s+)|(\\t+)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\.)|(?=\\.)");
		parsedList = new ArrayList<String>(Arrays.asList(parsedLine));
		/*Iterator<String> itr=parsedList.iterator();  
		while(itr.hasNext()){  
			String st=(String)itr.next();  
		    System.out.println(st);  
		 }  */
		size=parsedList.size();
	}
	public SExp input() throws MyLispException {
		SExp s=null;
		while(chkToken(parsedList.get(index))==6) {
			index++;
		}
		int temp=chkToken(parsedList.get(index));
		if(temp==2 || temp==3) {
			System.out.println("Error");
			return s;
		}
		if(temp==1) {
			index++;
			if(chkToken(parsedList.get(index))==2) {
				index++;
				return new SExp();
			}
			SExp car=input();	
			if(car==null) {
				return null;
			}
			SExp cdr=null;
			if(parsedList.get(index).equals(".")) {
				index++;
				cdr=input();
				if(!parsedList.get(index).equals(")")) {
					System.out.println("Error! Bracket Expected");
					return null;
				}
				index++;
			}
			else {
				while(chkToken(parsedList.get(index))==6) index++;
				cdr=input2();
			}
			if(cdr ==null) return null;
			s=new SExp(car,cdr);
			return s;
		}
		if(temp==4) {
			if(index==0 && size!=1) {
				System.out.println("Error! Invalid format");
				throw new MyLispException();
			}
			s=new SExp(Integer.parseInt(parsedList.get(index)));
			index++;
			return s;
		}
		if(temp==5) {
			if(SExp.symbolicAtomList.containsKey(parsedList.get(index))) {
				s=SExp.symbolicAtomList.get(parsedList.get(index));
			}
			else {
				s=new SExp(parsedList.get(index));
				SExp.symbolicAtomList.put(parsedList.get(index), s);
			}
			index++;
			return s;
		}
		return s;
	}
	public SExp input2() throws MyLispException {
		SExp s=null;
		int temp=chkToken(parsedList.get(index));
		if(temp==2) {
			index++;
			s=new SExp();
			return s;
		}
		else {
			SExp car=input();	
			if(car==null) return null;
			SExp cdr=null;
			if(parsedList.get(index).equals(".")) {
				//index++;
				//cdr=input();
				System.out.println("Error! List form should not include dot");
				throw new MyLispException();
				//return null;
			}
			else {
				while(chkToken(parsedList.get(index))==6) index++;
				cdr=input2();
			}
			if(cdr==null) return null;
			//index++;
			s=new SExp(car,cdr);
			return s;
		}
	}
}
