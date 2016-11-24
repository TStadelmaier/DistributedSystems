package de.unistgt.ipvs.vs.ex1.calcRMIclient;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

import de.unistgt.ipvs.vs.ex1.calculation.ICalculation;
import de.unistgt.ipvs.vs.ex1.calculation.ICalculationFactory;

/**
 * Implement the getCalcRes-, init-, and calculate-method of this class as
 * necessary to complete the assignment. You may also add some fields or methods.
 */
public class CalcRmiClient {
	private ICalculation calc = null;

	public CalcRmiClient() {
		this.calc = null;
	}

	public int getCalcRes() {
		// TODO

		try {
			return calc.getResult();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean init(String url) {
		// TODO
		/*if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}*/

		try {
			String name = "sessionFactory";
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			ICalculationFactory sessionFactory = (ICalculationFactory) registry.lookup("//localhost:2345/sessionFactory");

			//ICalculationFactory sessionFactory = (ICalculationFactory) java.rmi.Naming.lookup(name);

			this.calc = sessionFactory.getSession();



			return true;

		} catch (Exception e) {
			System.err.println("init() failed");
			e.printStackTrace();

			return false;
		}

	}

	public boolean calculate(CalculationMode calcMode, Collection<Integer> numbers) {
		// TODO
		for (Integer num: numbers) {
			switch (calcMode){
				case ADD:
					try {
						calc.add(num);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					break;
				case SUB:
					try {
						calc.subtract(num);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					break;
				case MUL:
					try {
						calc.multiply(num);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					break;
			}
		}
		return false;
	}
}
