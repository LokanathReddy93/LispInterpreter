package Part1;

import java.util.*;

public class evalSExpression {
	public static HashMap<String,SExp> dList;
	//static String errorMessage="";
	public evalSExpression() {
		dList=new HashMap<String,SExp>();
	}
	//Method to find the length of the parameter list
	public static int paralength(SExp exp) {
		return 0;
	}
	//Method for CAR which returns the CAR of the SEXp
	public static SExp CAR(SExp exp) throws MyLispException {
		if(exp.type!=3) {
			System.out.println("Error! CAR cannot applied to Atomic SExpression");
			throw new MyLispException();
		}
		else return exp.left;
	}
	//Method for CDR which returns the CDR of the SEXp
	public static SExp CDR(SExp exp) throws MyLispException {
		if(exp.type!=3) {
			System.out.println("Error! CDR cannot applied to Atomic SExpression");
			throw new MyLispException();
		}
		else return exp.right;
	}
	//Method for CONS which returns an SExp which contains the two inputs as CAR and CDR
	public static SExp CONS(SExp left, SExp right) {
		return new SExp(left,right);
	}
	//Method for eq which checks if both inputs point to same object.
	public static SExp EQ(SExp p1, SExp p2) throws MyLispException {
		if(p1.type==1 && p2.type==1) {
			if((p1.val==p2.val)) return new SExp("T");
			else return new SExp();
		}
		else if(p1.type==2 && p2.type==2) {
			if((p1.name==p2.name)) return new SExp("T");
			else return new SExp();
		}
		else {
			if((p1==p2) || (p1.isNIL && p2.isNIL)) return new SExp("T");
			else return new SExp();
		}
	}
	// Method for checkin if the given SExp is atom or not.
	public static SExp ATOM(SExp p) throws MyLispException {
		if(p.type==3) {
			//if(p.right.isNIL) return ATOM(p.left);
		    return new SExp();
		}
		else return new SExp("T");
	}
	// Method for checking if the given SExp is atom or not.
	public static SExp NULL(SExp p) {
		if(p.type==3) {
			if(p.right.isNIL) return NULL(p.left);
			else return new SExp();
		}
		else {
			if(p.isNIL) return new SExp("T");
			else return new SExp();
		}
	}
	// Method for checking if the given SExp is an Integer atom or not.
	public static SExp INT(SExp p) {
		if(p.type==3) {
			if(p.right.isNIL) return INT(p.left);
			else return new SExp();
		}
		else {
			if(p.type==1) return new SExp("T");
			else return new SExp();
		}
	}
	// Method for multiplying two integers
	public static SExp TIMES(SExp p1, SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for TIMES is not an Integer");
			throw new MyLispException();
		}
		else return new SExp(p1.val*p2.val);
	}
	//Method for adding two integers
	public static SExp PLUS(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for PLUS is not an Integer");
			throw new MyLispException();
		}
		else return new SExp(p1.val+p2.val);
	}
	//Method for subtracting two integers
	public static SExp MINUS(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for MINUS is not an Integer");
			throw new MyLispException();
		}
		else return new SExp(p1.val-p2.val);
	}
	//Method for finding the Quotient
	public static SExp QUOTIENT(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for QUOTIENT is not an Integer");
			throw new MyLispException();
		}
		else {
			if(p2.val==0) {
				System.out.println("Error! You can't divide an integer with zero");
				throw new MyLispException();
			}
			else return new SExp(p1.val/p2.val);
		}
	}
	//Method to find remainder
	public static SExp REMAINDER(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for REMAINDER is not an Integer");
			throw new MyLispException();
		}
		else {
			if(p2.val==0) {
				System.out.println("Error! You can't divide an integer with zero");
				throw new MyLispException();
			}
			else return new SExp(p1.val%p2.val);
		}
	}
	// Method to check if first integer is less than second integer
	public static SExp LESS(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for Less is not an Integer");
			throw new MyLispException();
		}
		else {
			if(p1.val<p2.val) return new SExp("T");
			else return new SExp();
		}
	}
	// Method to check if first integer is greater than second integer
	public static SExp GREATER(SExp p1,SExp p2) throws MyLispException {
		if(p1.type!=1 || p2.type!=1) {
			System.out.println("Error! One of the inputs for Greater is not an Integer");
			throw new MyLispException();
		}
		else {
			if(p1.val>p2.val) return new SExp("T");
			else return new SExp();
		}
	}
	// Method that returns the parameter without evaluating
	public static SExp QUOTE(SExp p) {
		return p;
	}
	public static boolean isList(SExp exp) throws MyLispException {
		if(exp.isNIL) return true;
		else if(exp.type==3) return isList(exp.right);
		else return false;
	}
	
	//Method for conditional
	public static SExp COND(SExp exp, SExp aList) throws MyLispException {
		if(exp==null ) {
			System.out.println("Error! Unexprected input parameter for COND");
			throw new MyLispException();
		}
		else if(!eval(CAR(CAR(exp)),aList).isNIL) {
			if(!CDR(CDR(CAR(exp))).isNIL) {
				System.out.println("Error! Unexpected number of parameters for COND");
				throw new MyLispException();
			}
			return eval(CAR(CDR(CAR(exp))),aList);
		}
		else {
			return COND(CDR(exp),aList);
		}
	}
	//Method to check if an atom is present in the aList
	public static boolean containsKey(SExp s, SExp aList) throws MyLispException {
		if(s==null || aList==null || aList.isNIL) {
			return false;
		}
		else {
			if(CAR(CAR(aList)).name.equalsIgnoreCase(s.name)){
				return true;
			}
			else return containsKey(s,CDR(aList));
		}
	}
	//Method to get the value of an symbolic atom if it is present in the aList
	public static SExp getValue(SExp s, SExp aList) throws MyLispException {
		if(s.type==1) return s;
		if(s==null || aList==null || aList.isNIL) {
			System.out.println("Error! Unbound atom.");
			throw new MyLispException();
		}
		else {
			if(CAR(CAR(aList)).name.equalsIgnoreCase(s.name)){
				return CDR(CAR(aList));
			}
			else return getValue(s,CDR(aList));
		}
	}
	public static SExp evaluateParams(SExp exp, SExp aList) throws MyLispException{
		if(exp.isNIL || exp.type==1) return exp;
		else {
			return CONS(eval(exp.left,aList),evaluateParams(exp.right,aList));
		}
	}
	public static boolean numofparams(SExp exp, SExp params) throws MyLispException{
		if((exp.isNIL && !params.isNIL)||(!exp.isNIL && params.isNIL)) {
			System.out.println("Error! Number of parameters do not match with the definition");
			throw new MyLispException();
		}
		else if(exp.isNIL && params.isNIL) return true;
		else return numofparams(exp.right, params.right);
	}
	public static SExp bindParams(SExp exp, SExp params, SExp aList) throws MyLispException{
		if(exp.isNIL && params.isNIL) return aList;
		SExp newList=bindParams(exp.right,params.right,aList);
		if(params.left.type!=2) {
			System.out.println("Error! Binding atom should be symbolic");
			throw new MyLispException();
		}
		return CONS(CONS(params.left,exp.left),newList);
	}
	public static SExp checkdList(SExp exp, SExp aList) throws MyLispException {
		SExp temp=InterpreterMain.dList;
		while(!temp.isNIL) {
			if(temp.left.left.name.equals(exp.left.name)) {
				if(numofparams(exp.right,temp.left.right.left)) {
					SExp tempaList=bindParams(evaluateParams(exp.right, aList),temp.left.right.left,aList);
					return eval(temp.left.right.right.left,tempaList);
				}
			}
			else temp=temp.right;
		}
		System.out.println("Error! The function is not defined");
		throw new MyLispException();
	}
	public static SExp eval(SExp exp, SExp aList) throws MyLispException {
		if(exp.type!=3) {
			if(exp.type==1) {
				return exp;
			}
			else {
				if(exp.name.equals("T") || exp.isNIL) return exp;
				else if(containsKey(exp, aList)) return getValue(exp, aList);
				else {
					System.out.println("Error! Unbound Atom.");
					throw new MyLispException();
				}
			}
			//return exp;//Modify this as u need to send value of the variable by checking the aList.
		}
		else if(exp.right.isNIL && exp.left.type==1) return exp.left;
		else if(exp.left.type==3) {
			System.out.println("Error! Unexpected Format");
			throw new MyLispException();
		}
		else if(CAR(exp).name.equalsIgnoreCase("QUOTE")) {
			if(exp.right.type!=3 || !CDR(exp.right).isNIL) {
				System.out.println("Error! Unexpected number of parameters for QUOTE");
				throw new MyLispException();
			}
			else return exp.right.left;
		}
		else if(CAR(exp).name.equalsIgnoreCase("COND")) return COND(CDR(exp),aList);
		else if(CAR(exp).name.equalsIgnoreCase("DEFUN")) {
			if(exp.right.type!=3 || CDR(exp.right).type!=3 || CDR(CDR(exp.right)).type!=3 || !CDR(CDR(exp.right)).right.isNIL) {
				System.out.println("Error! Unexpected number of parameters for DEFUN");
				throw new MyLispException();
			}
			if(!isList(CDR(exp.right).left)) {
				System.out.println("Error! the parameter list must be a list.");
				throw new MyLispException();
			}
			SExp newFnName=new SExp(CAR(CDR(exp)).name);
			SExp newFnParam=CAR(CDR(CDR(exp)));
			SExp newFnDefParam=new SExp(newFnParam,CDR(CDR(CDR(exp))));
			SExp newFn=new SExp(newFnName,newFnDefParam);
			InterpreterMain.dList=new SExp(newFn,InterpreterMain.dList);
			return newFnName;
		}
		else {
			String s=CAR(exp).name.toUpperCase();
			switch(s){
			case "CAR":
				if(exp.right.type!=3  || !CDR(exp.right).isNIL) {
					System.out.println("Error! Unexpected number of parameters for CAR " + exp.right.left.name );
					throw new MyLispException();
				}
				else return CAR(eval(exp.right.left,aList));
			case "CDR":
				if(exp.right.type!=3 || !CDR(exp.right).isNIL) {
					System.out.println("Error! Unexpected number of parameters for CDR");
					throw new MyLispException();
				}
				else return CDR(eval(exp.right.left,aList));
			case "TIMES":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for TIMES");
					throw new MyLispException();
				}
				else return TIMES(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "CONS":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for CONS");
					throw new MyLispException();
				}
				else {
					return CONS(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
				}
			case "ATOM":
				if(exp.right.type!=3 || !CDR(exp.right).isNIL) {
					System.out.println("Error! Unexpected number of parameters for ATOM");
					throw new MyLispException();
				}
				else return ATOM(eval(exp.right.left,aList));
			case "EQ":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for EQ");
					throw new MyLispException();
				}
				else return EQ(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "NULL":
				if(exp.right.type!=3 || !CDR(exp.right).isNIL) {
					System.out.println("Error! Unexpected number of parameters for NULL");
					throw new MyLispException();
				}
				else return NULL(eval(exp.right.left,aList));
			case "INT":
				if(exp.right.type!=3 || !CDR(exp.right).isNIL) {
					System.out.println("Error! Unexpected number of parameters for INT");
					throw new MyLispException();
				}
				else return INT(eval(exp.right.left,aList));
			case "QUOTIENT":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for QUOTIENT");
					throw new MyLispException();
				}
				else return QUOTIENT(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "REMAINDER":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for REMAINDER");
					throw new MyLispException();
				}
				else return REMAINDER(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "PLUS":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for PLUS");
					throw new MyLispException();
				}
				else return PLUS(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "MINUS":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for MINUS");
					throw new MyLispException();
				}
				else return MINUS(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "LESS":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for LESS");
					throw new MyLispException();
				}
				else return LESS(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			case "GREATER":
				if(exp.right.type!=3 || CDR(exp.right).type!=3 || !CDR(exp.right).right.isNIL) {
					System.out.println("Error! Unexpected number of parameters for GREATER");
					throw new MyLispException();
				}
				else return GREATER(eval(CAR(exp.right),aList),eval(CAR(CDR(exp.right)),aList));
			default:
				return checkdList(exp, aList);
			}	
	    }
	}
}
