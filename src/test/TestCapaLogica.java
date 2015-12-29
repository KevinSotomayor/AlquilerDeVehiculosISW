package test;

import logica.AlquilerVehiculos;

public class TestCapaLogica {

	public static void main(String args[]){

		try{
			AlquilerVehiculos alquiler = AlquilerVehiculos.getInstance();
		}catch(Exception e){
			System.out.println("ha ocurrido un error " + e.getMessage()) ;
		}

		
	}
}

