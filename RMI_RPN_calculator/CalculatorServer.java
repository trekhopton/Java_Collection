package calculator;
	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
import java.util.*;

// Locate the registry, create a calculator and bind the interface to the registry
public class CalculatorServer {
	
    public static void main(String args[]) {
		String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);

            System.out.println("Creating Calculator...");
            CalculatorImplementation obj = new CalculatorImplementation();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);
            try {
				System.out.println("Binding Calculator...");
                registry.bind("Calculator", stub);            
                System.out.println("CalculatorServer ready");
            } catch (Exception e) {
                System.err.println("Binding exception: " + e.toString());
                try {
                    System.out.println("Unbinding Calculator...");
                    registry.unbind("Calculator");
                    try {
                        System.out.println("Trying to Bind Calculator Again...");
                        registry.bind("Calculator", stub);   
                        System.out.println("CalculatorServer ready");
                    } catch (Exception h) {
                        System.err.println("Second Bind exception: " + h.toString());
                    }
                } catch (Exception f) {
                    System.err.println("Unbinding exception: " + f.toString());
                }
            }
        } catch (Exception g) {
            System.err.println("Registry Locating exception: " + g.toString());
        }
    }
}