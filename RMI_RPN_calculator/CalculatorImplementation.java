package calculator;
	
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

//Reverse Polish Notation Calculator Class
public class CalculatorImplementation implements Calculator {
	
    private Stack<Integer> calcStack;

    public CalculatorImplementation() {
        calcStack = new Stack<Integer>();
    }
	// push an integer on to the stack
    public void pushValue(int operand){
		calcStack.push(operand);
    }
	// take the two top integers from the stack, perform the given 
	// operation and push the result to the stack
    public void pushOperator(String operator){

        switch(operator){
			case "+": 
				int a1 = calcStack.pop();
				int a2 = calcStack.pop();
				calcStack.push(a1 + a2);
				break;
			case "-": 
				int b1 = calcStack.pop();
				int b2 = calcStack.pop();
				calcStack.push(b2 - b1);
				break;
			case "*": 
				int c1 = calcStack.pop();
				int c2 = calcStack.pop();
				calcStack.push(c1 * c2);
				break;
			case "/": 
				int d1 = calcStack.pop();
				int d2 = calcStack.pop();
				if(d2 != 0){
					calcStack.push(d2 / d1);
				}
				break;
			default: System.err.println("Error: invalid operator, did nothing");
				break;
		}
    }
	//remove and return the top integer from the stack
    public int pop(){
		int result = calcStack.pop();
		return result;
	}
	//check if the stack is empty
    public boolean isEmpty(){
		boolean b = calcStack.empty();
		return b;
	}
	//wait given number of milliseconds then remove and return the top integer from the stack
    public int delayPop(int millis){
		try {
			Thread.sleep(millis);
			return calcStack.pop();
		} catch (Exception e) {
			System.err.println("Failed to sleep");
		}
		return 0;
	}
}