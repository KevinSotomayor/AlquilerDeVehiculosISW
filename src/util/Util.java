package util;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Util {
	//Reemplazar los / por - para que acepte bien la fecha el sistema en formato localDateTime
	public static String getDateinString(String date){
		String res = date.replace("/", "-");
		res+="T00:00:00";//hour by default with only date
		return res;
	}

	public static LocalDateTime setDateinLocaleDateTime(String date){
		LocalDateTime dateTime = null;
		try{
			date = getDateinString(date);
			System.out.println(date);
			dateTime= LocalDateTime.parse(date);
		}catch(Exception e){
			System.out.println("Ocurrió un error al obtener una fecha válida por el sistema");
		}
		return dateTime;
	
	}
	
	public static LocalDateTime transformLocalDate(LocalDate l){
		return l.atStartOfDay();
	}
	
	//Devuelve una fecha en un formato
	public static String getDateinStringByLocale(LocalDateTime localDateTime){
		String res = "";
		res+=localDateTime.getYear() + "/";
		res+=localDateTime.getMonthValue()+ "/";
		res+=localDateTime.getDayOfMonth();
		return res;

	}
	
	public static boolean isNumeric(String aux){
		try{	
			Integer.parseInt(aux);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	}
	
	public static boolean isNumericDouble(String aux){
		try{	
			Double.parseDouble(aux);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	}
}
