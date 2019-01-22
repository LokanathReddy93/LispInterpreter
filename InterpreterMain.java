package Part1;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InterpreterMain {
	public static boolean checkifMultipleDots() {
		boolean ans=false;
		return ans;
	}
	static SExp dList;
	public static String convertToString(SExp root) {
		String res="";
		if(root.getIsNil()) return "NIL";
		else if(root.type==1) return res+root.val;
		else if(root.type==2) return res+root.name;
		else {
			res+="(";
			res+=convertToString(root.left);
			res+=".";
			res+=convertToString(root.right);
			res+=")";
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		System.out.println("Enter the input");
		SExp aList=new SExp();
		dList=new SExp();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//Scanner scanner = new Scanner(System.in);
		String inputdata = "";
		String temp="";
		while(true) {
			try {
				temp=reader.readLine();
				inputdata="";
				while(!temp.endsWith("$")&&(!temp.endsWith("$$"))) {
					inputdata+=" "+temp;
					temp=reader.readLine();
				}
				inputdata.trim();
				if(!inputdata.equals("") && (!inputdata.equals("\n"))) {
					SExp bt=null,ebt=null;
					MyParser mp=new MyParser(inputdata);
					ArrayList<String> parsedList=mp.parsedList;
					String element="";
					int leftBrac=0,rightBrac=0;//,numDots=0;
					boolean dotAllowed=false,validSExp=true,expExpected=false;
					for(int ind=0;ind<parsedList.size();ind++) {
						element=parsedList.get(ind);
						if(element.equals("")) continue;
						if(element.equals("(")) {
							if(leftBrac>0 && leftBrac<=rightBrac) {
								System.out.println("Error! UnExpected Braces.");
								validSExp=false;
								break;
							}
							leftBrac++;
							expExpected=false;
						}
						else if(element.equals(")")) {
							dotAllowed=true;
							rightBrac++;
							if(leftBrac<rightBrac) {
								System.out.println("Error! UnExpected Braces.");
								validSExp=false;
								break;
							}
						}
						else if(element.matches("^[+-]?\\d+$")) {
							expExpected=false;
							dotAllowed=true;
							continue;
						}
						else if(element.equals(".")) {
							if(dotAllowed) {
								dotAllowed=false;
								expExpected=true;
							}
							else {
								System.out.println("Error! UnExpected dot");
								validSExp=false;
								break;
							}
							if(leftBrac<=rightBrac) {
								System.out.println("Error! UnExpected dot");
								validSExp=false;
								break;
							}
						}
						else {
							if(!element.matches("[A-Za-z][A-Za-z0-9]*")) {
								System.out.println("Error! Symbolic atom naming doesnot match the requirement");
								validSExp=false;
								break;
							}
							else {
								dotAllowed=true;
								expExpected=false;
							}
						}
					}
					if(validSExp &&(leftBrac-rightBrac!=0)) {
						System.out.println("Error! UnExpected Braces.");
						validSExp=false;
					}
					if(expExpected&&validSExp) {
						System.out.println("Error! Expression expected after dot");
						validSExp=false;
					}
					if(validSExp) {
						bt=mp.input();
						if(bt==null) {
							//System.out.println(mp.errorMessage);
						}
						else {
							System.out.println("DOT Notation: "+ convertToString(bt));
							ebt=evalSExpression.eval(bt,aList);
							if(ebt!=null) {
								System.out.println("Output: "+ convertToString(ebt));
							}
						}
					}
				}
				if(temp.equals("$$")) {
					break;
				}
			}
			catch(MyLispException e)
			{
				if(temp.equals("$$")) {
					break;
				}
			}
		}
		return;
	}
}
