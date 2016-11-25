package de.unistgt.ipvs.vs.ex1.calcRMIserver;

import de.unistgt.ipvs.vs.ex1.calculation.ICalculationFactory;
import de.unistgt.ipvs.vs.ex1.calculationImpl.CalculationImplFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implement the run-method of this class to complete
 * the assignment. You may also add some fields or methods.
 */
public class CalcRmiServer extends Thread {
	private String regHost;
	private String objName;
	
	public CalcRmiServer(String regHost, String objName) {
		this.regHost = regHost;
		this.objName = objName;
	}
	
	@Override
	public void run() {
		if (regHost == null || objName == null) {
			System.err.println("<registryHost> and/or <objectName> not set!");
			return;
		}

		String SERVICE_NAME = "ICalculationFactory";

		/*if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}*/

		Registry reg = null;
		try {

			//reg = LocateRegistry.getRegistry();

			reg = LocateRegistry.createRegistry(1099);


			ICalculationFactory factory = new CalculationImplFactory();
			ICalculationFactory stub = (ICalculationFactory) UnicastRemoteObject.exportObject(factory,0);

			reg.rebind("//" +regHost+"/"+objName, stub);



		} catch (Exception ex) {
			System.err.println("SessionFactory exception");
			ex.printStackTrace();
		}

		/*try {
			ICalculationFactory factory = new CalculationImplFactory();

			java.rmi.Naming.rebind("//localhost:2345/sessionFactory", factory);

		} catch (Exception e) {
			e.printStackTrace();
		}*/




		// TODO
	}

}
