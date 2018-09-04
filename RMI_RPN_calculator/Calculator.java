package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.locks.ReentrantLock;

//see implementation for descriptions of methods
public interface Calculator extends Remote {
    void pushValue(int operand) throws RemoteException;
    void pushOperator(String operator) throws RemoteException;
    int pop() throws RemoteException;
    boolean isEmpty() throws RemoteException;
    int delayPop(int millis) throws RemoteException;
}