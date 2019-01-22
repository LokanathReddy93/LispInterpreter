# LispInterpreter
Implementation of a Lisp parser and Interpreter from the scratch.

================================================================

//README-- This guides how to execute the java implementation of Lisp Interpreter (the front end part).
Command to run the Makefile:
	
make

// This produces three class files. To execute the main class, run the following command next.

java InterpreterMain

// This asks you to input the data. Now input your SExpressions (valid or invalid) and after each SExpression, enter "$" in a newline 
// and it immediately outputs the Dpot notation of your input. For the last input, enter "$$" (here two $ signs). This outputs the required SExpression and exits the program. If you want to clean the .class files, enter the following command.

make clean
