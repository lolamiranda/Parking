package P7;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;


public class CarDB  {
    private static int contador_electric = 0;
    private static int contador_combustion = 0;
    private static int contador_hybrid = 0;
    
    LinkedHashMap<String, Car> cityCars = new LinkedHashMap<String, Car>();
    Set<String> plateSet = cityCars.keySet();
    
    public CarDB () {
    }
	
    //Lee los coches del fichero
    public void readCityCarsFile (String fichero) throws FileNotFoundException {
	String linea;
	char tipoCoche ;
	
	File file = new File(fichero);
	Scanner entrada= new Scanner(file);
	
	while(entrada.hasNextLine()) {
	    linea = entrada.nextLine();
	    
	    if (linea.startsWith("#")) {
		continue;
	    }
	    
	    tipoCoche = linea.charAt(0);
	    String[] palabra = linea.split(";");
	    
	    switch (tipoCoche) {
		
	    case 'C':
			    
		String plateC = palabra[1];
		String manufacturerC = palabra[2];
		int powerC = Integer.parseInt(palabra[3]);
			    
		Car cc= new CombustionCar(plateC,manufacturerC,powerC);
		
		if (Car.isValidPlate(plateC) && (CombustionCar.isValidPower(powerC))) {
		    cityCars.put(plateC,cc);
		    contador_combustion++;
		}
		break;
		
		case 'E':
		    
		    String plateE = palabra[1];
		    String manufacturerE = palabra[2];
		    int powerE = Integer.parseInt(palabra[3]);
		    float batteryChargeE = Float.valueOf(palabra[4].replace(",","."));
		    
		    Car ec= new ElectricCar(plateE,manufacturerE,powerE,batteryChargeE);
		    
		    if ((Car.isValidPlate(plateE)) && (ElectricCar.isValidPower(powerE)) && (ElectricCar.isValidBatteryCharge(batteryChargeE))) {
			cityCars.put(plateE,ec);
			contador_electric++;
		    }
		    break;
		    
	    case 'H':
		String plateH = palabra[1];
		String manufacturerH = palabra[2];
		int mPowerH = Integer.parseInt(palabra[3]);
		int ePowerH = Integer.parseInt(palabra[4]);
		float batteryChargeH = Float.valueOf(palabra[5].replace(",","."));
		
		Car hc= new HybridCar(plateH,manufacturerH,mPowerH,ePowerH,batteryChargeH);
		
		if ((Car.isValidPlate(plateH)) && (HybridCar.isValidMPower(mPowerH)) && (HybridCar.isValidEPower(ePowerH))&& (HybridCar.isValidBatteryCharge(batteryChargeH))) {
		    cityCars.put(plateH,hc);
		    contador_hybrid++;
		}
		break;
		
	    }
	}
	entrada.close();
    }	
    
    //Obtiene un coche a traves de su matricula
    Car getCarFromPlate (String plate) {
	Car coche = null;
	
    	for(int i=0;i<cityCars.size();i++) {
	    coche=cityCars.get(plate);
	    
	    if(coche.getPlate().equals(plate)) {
		
		if (coche instanceof CombustionCar) {
		    coche = new CombustionCar();
		    coche.setPlate(plate);
		    cityCars.get(plate);
		    break;
		}
		if (coche instanceof ElectricCar) {
		    coche = new ElectricCar();
		    coche.setPlate(plate);
		    cityCars.get(plate);
		    break;
		}
		if (coche instanceof HybridCar) {
		    coche = new HybridCar();
		    coche.setPlate(plate);
		    cityCars.get(plate);
		    break;
		}
	    }
    	}
    	return coche;
    }
    //Potencia total 
    public int computeTotalPower () {
	Car coche;
	int acumuladorC=0;
	int acumuladorE=0;
	int acumuladorH=0;
	int acumulador=0;
	
	for(String plate : plateSet) {
	    coche = cityCars.get(plate);
				    
	    if (coche instanceof CombustionCar) {
		for (int j=0; j<contador_combustion; j++) {
		    int potencia = coche.getTotalPower();
		    acumuladorC =  + potencia;
		}
	    }
	    
	    if (coche instanceof ElectricCar) {
		for (int j=0; j<contador_electric; j++) {
		    int potencia = coche.getTotalPower();
		    acumuladorE =  + potencia;
		}
	    }
	    
	    if (coche instanceof HybridCar) {
		for (int j=0; j< contador_hybrid ; j++) {
		    int potencia = coche.getTotalPower();
		    acumuladorH = potencia;
		}
	    }
	}
	acumulador = acumuladorC+ acumuladorE +acumuladorH;
	return acumulador;
    }
     
    //Nivel de bateria total
    public float computeAverageBatteryLevel () {
	Car coche;
	float acumuladorElectricos=(float) 0.0;
	float acumuladorHibridos =(float) 0.0;
	float acumuladorTotal= (float)0.0;
	
	for(String plate : plateSet) {
	    coche = cityCars.get(plate);
	    		 
	    if (coche instanceof ElectricCar) {
		float battery = coche.getBatteryCharge();
		acumuladorElectricos = +battery;
	    }
	    		 
	    if (coche instanceof HybridCar) {
		float battery = coche.getBatteryCharge();
		acumuladorHibridos = +battery;
	    }
	}
	
	acumuladorTotal= ((acumuladorHibridos+acumuladorElectricos)/2);
	return acumuladorTotal;
    }
    
    //Guarda los coches en un fichero
    public void saveCarsToFile (String filename) throws FileNotFoundException {
	File file = new File(filename);
	PrintWriter pw = new PrintWriter(file);
	Car coche = null;
	
	for(String plate : plateSet) {
	    coche = cityCars.get(plate);
	    
	    if (coche instanceof CombustionCar) {
		pw.println(coche.toString());
	    }
	    if (coche instanceof ElectricCar) {
		pw.println(coche.toString());
	    }
	    if (coche instanceof HybridCar) {
		pw.println(coche.toString());
	    }
	}
	pw.close();
    }
    
    private float intervalInHours(String inTime, String outTime) {
	int hi = Integer.parseInt(inTime.split(":")[0].trim());
	int mi = Integer.parseInt(inTime.split(":")[1].trim());
	int ho = Integer.parseInt(outTime.split(":")[0].trim());
	int mo = Integer.parseInt(outTime.split(":")[1].trim());
	    			int dif = (ho*60+mo)-(hi*60+mi);
	    			return ((float)dif/60);
    }
    
    //Incrementa el nivel de bateria
    public void increaseCarBatteryChargeLevel(String plate, String entryTime, String departureTime) {
	Car cocheEncontrado = cityCars.get(plate);
	float chargeTime = intervalInHours(entryTime,departureTime);
	
	if (cocheEncontrado instanceof ElectricCar) {
	    ElectricCar cocheElectrico = (ElectricCar)cocheEncontrado;
	    cocheElectrico.increaseBatteryChargeLevel(chargeTime);
	    cityCars.put(cocheElectrico.getPlate(),cocheEncontrado);
	}
	
	if (cocheEncontrado instanceof HybridCar){
	    HybridCar cocheHibrido = (HybridCar)cocheEncontrado;
	    cocheHibrido.increaseBatteryChargeLevel(chargeTime);
	    cityCars.put(cocheHibrido.getPlate(),cocheEncontrado);
	}
    }
    
    //Ordena por matriculas
    public void sortByPlate () {
	ArrayList <Car> cityCarsPlate = new ArrayList <Car> ();
	
	for (Car coche : cityCars.values()) {
	    cityCarsPlate.add(coche);
	}
	
	Collections.sort(cityCarsPlate);
	cityCars.clear();
	
	for (Car coche : cityCarsPlate) {
	    String plate = coche.getPlate();
	    cityCars.put(plate,coche);
	    		 }
    }
    
    //Ordena por bateria y matricula
    public void sortByBatteryChargeAndPlate () {
	ArrayList <Car> cityCarsBattery = new ArrayList <Car> ();
	
	for (Car coche : cityCars.values()) {
	    cityCarsBattery.add(coche);
	}
	
	Collections.sort(cityCarsBattery);
	cityCars.clear();
	
	for (Car coche : cityCarsBattery) {
	    String plate = coche.getPlate();
	    cityCars.put(plate,coche);
	}
    }
    
    public boolean saveToBinary(String fichero) throws FileNotFoundException, IOException {
		File file = new File(fichero);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(file));
		int lectura = 0;
		
		if (cityCars.size()!= lectura) {
		for (Car coche : cityCars.values()) {
				o.writeObject(coche);
				lectura ++;
		}
		}else {
			o.close();
			return true;
		}
			o.close();
			return false;
	}
	
	public boolean savePowersToBinary(String fichero) throws IOException {
		File file = new File(fichero);
		DataOutputStream o = new DataOutputStream(new FileOutputStream(file));
		int lectura = 0;
		
		if (cityCars.size()!=lectura) {
		for (Car coche : cityCars.values()) {
			if (coche instanceof CombustionCar) {
				int potenciaC = 0;
				o.writeInt(potenciaC);
				lectura++;
			}
			if (coche instanceof ElectricCar) {
				int potenciaE = coche.getTotalPower();
				o.writeInt(potenciaE);
				lectura++;
			}
			if (coche instanceof HybridCar) {
				int potenciaH = coche.getTotalPower();
				o.writeInt(potenciaH);
				lectura++;
			}
		}
		}else {
			o.close();
			return true;
			}
		o.close();
		return false;
		}
}
    