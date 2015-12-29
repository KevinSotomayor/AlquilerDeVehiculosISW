package test;

import logica.AlquilerVehiculos;


public class DAOPersistenceTester {

	public static void main(String[] args){
		try{
			AlquilerVehiculos alquiler = AlquilerVehiculos.getInstance();
		}catch ( Exception e ){
			System.out.println("Error al cargar alquiler vehiculos, el error es: " +e.getMessage() );
		}
	}
}
