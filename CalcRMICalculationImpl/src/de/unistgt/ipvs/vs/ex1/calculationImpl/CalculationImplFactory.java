package de.unistgt.ipvs.vs.ex1.calculationImpl;

import de.unistgt.ipvs.vs.ex1.calculation.ICalculation;
import de.unistgt.ipvs.vs.ex1.calculation.ICalculationFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Change this class (implementation/signature/...) as necessary to complete the assignment.
 * You may also add some fields or methods.
 */
public class CalculationImplFactory implements ICalculationFactory {

public CalculationImplFactory () {
    super();
}

    @Override
    public ICalculation getSession() throws RemoteException {
        return new CalculationImpl();
    }




}