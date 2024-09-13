package P7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class P7 {
    static CarDB cdb;
    static Parking miParking;
    
    public static void main (String[] args) throws IOException {
	cdb = new CarDB();
	cdb.readCityCarsFile("P6b/" + args[3]);
	
	miParking = new Parking("P6b/" + args[0]);
    	processIO("P6b/"+ args[1]);
    	miParking.saveParking("P6b/" + args[2]);
    	cdb.sortByPlate();
    	cdb.saveCarsToFile("P6b/"+args[4]);
    	miParking.ToMap("P6b/" + args[5]);
    }
    
    private static void processIO(String filename) throws IOException {
	File fichero= new File (filename);
	Scanner entrada= new Scanner(fichero);
	String linea="";
	String time = null;
	
	while(entrada.hasNext()) {
	    linea = entrada.nextLine();
	    if (!linea.startsWith("#")) {
		String[] palabra = linea.split(";");
		
		if(palabra[0].charAt(0) == 'I') {
		    String plate = palabra[1];
		    time = palabra[2];
		    Car coche =  cdb.getCarFromPlate(plate);
		    miParking.carEntry(coche,time);
		    
		} else {
		    String plate = palabra[1];
		    String departureTime = palabra[2];
		    String entryTime = miParking.carDeparture(plate);  
		    cdb.increaseCarBatteryChargeLevel(plate,entryTime,departureTime);
		}
	    }
	}
	entrada.close();
    }
}