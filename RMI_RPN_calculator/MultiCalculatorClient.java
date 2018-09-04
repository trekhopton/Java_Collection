package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class MultiCalculatorClient implements Runnable {

    private Thread t;
    private String threadName;

    public MultiCalculatorClient(String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }
    //Find the registry and run all tests in tests.txt file
    // on the calculator then output the results
    public void run() {
        System.out.println("Running " +  threadName );

        try {
            Registry registry = LocateRegistry.getRegistry();
			Calculator stub = (Calculator) registry.lookup("Calculator");
			
            File file = new File("tests.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){ 
                int val1 = Integer.parseInt(sc.next());
                int val2 = Integer.parseInt(sc.next());
                String operator = sc.next();
                int expected = Integer.parseInt(sc.next());
                stub.pushValue(val1);
                stub.pushValue(val2);
                stub.pushOperator(operator);
                int result = stub.pop();
                if(result == expected){
                    System.out.println("Test Passed: " + val1 + " " + val2 + " " + 
                        operator + " = " + expected + " test returned: " + result);
                } else {
                    System.out.println("Test Failed: " + val1 + " " + val2 + " " + 
                        operator + " = " + expected + " test returned: " + result);
                }

            }

        } catch (Exception e) {
            System.err.println("CalculatorClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
    //test the calculator with multiple clients
    public static void main(String args[]) {
        
        CalculatorClient R1 = new CalculatorClient("Client-1");
        R1.start();

        CalculatorClient R2 = new CalculatorClient("Client-2");
        R2.start();

        CalculatorClient R3 = new CalculatorClient("Client-3");
        R3.start();

        CalculatorClient R4 = new CalculatorClient("Client-4");
        R4.start();
    }   
}
