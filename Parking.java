package P7;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.*;
import java.util.Iterator;

public class Parking {
    private char maxZone; // un caracter de la A a la Z para indicar el maximo numero de zonas del parking 
    private int sizeZone; //un entero para indicar el numero de plazas por zona
    private char lowerElectricZone;
    CarSpace[][] carSpaces ;	
    private int contadorPlazas =0;
    int numZonas;
    CarSpace plazaBbuscada, plaza ;
    
    HashSet <CarSpace>busyCarSpaces = new HashSet<CarSpace>();
    TreeSet <CarSpace>freeCarSpaces = new TreeSet<CarSpace>();
    CarComparatorByTimeAndCoordinate comparadorTiempoYCoordenada = new CarComparatorByTimeAndCoordinate();
    
    public Parking() {
    }
    
    
    public Parking(char maxZone, int sizeZone,char lowerElectricZone,CarSpace[][] carSpaces) {
	this.maxZone= maxZone;
	this.sizeZone = sizeZone;
	this.lowerElectricZone=lowerElectricZone;
	this.carSpaces= carSpaces;
    }
    
    //CREACION PARKING
    public Parking (String filename) throws IOException {
	int z = 0,posicion;
	String linea= null;
	String[] palabra;
	char k, letra;
	String matricula,tiempo;
	Coordinate coordenada;
	
	File fichero = new File (filename);
	Scanner entrada = new Scanner(fichero);
	
	while (z == 0) {
	    linea = entrada.nextLine();
	    if (linea.startsWith("#")) {
		continue;
	    }
	    palabra = linea.split(";");
	    this.maxZone=palabra[0].charAt(0);
	    this.sizeZone = Integer.parseInt(palabra[1]);
	    this.lowerElectricZone = palabra[2].charAt(0);
	    
	    numZonas= maxZone-64;
	    z++;
	}
	
	for (int i=0; i <numZonas; i++) {
	    for (int j=1; j<=sizeZone; j++) {
		//Coordenada(zona,numero)
		k = (char)('A'+i);
		coordenada = new Coordinate (k,j);
		CarSpace coche = new CarSpace(coordenada,null,null);
		
		freeCarSpaces.add(coche);
		
		contadorPlazas++;
	    }
	}
	
	while(entrada.hasNextLine()) {
	    linea = entrada.nextLine();
	    if (linea.startsWith("#")) {
		continue;
	    }
	    palabra = linea.split(";");
	    letra = palabra[0].charAt(0);
	    posicion = Integer.parseInt(palabra[0].substring(1));
	    
	    Coordinate c = new Coordinate (letra,posicion);
	    matricula = palabra[1];
	    tiempo = palabra[2];
	    
	    plaza = new CarSpace(c,matricula,tiempo);
	    
	    if (freeCarSpaces.contains(plaza)){
		Coordinate co = plaza.getCoordinate();
		freeCarSpaces.remove(new CarSpace(co,null,null));
		busyCarSpaces.add(plaza);
	    }
	}
	entrada.close();
    }
    
    
    //ENTRADAS
    public void carEntry (Car car, String time) throws IOException {
    	if (car instanceof CombustionCar) {
	    if(freeCarSpaces.first().getPlate() == null){ //el primero que no esta ocupado 
		Coordinate c = freeCarSpaces.first().getCoordinate();
		freeCarSpaces.remove(new CarSpace(c,null,null));
		CarSpace plaza = new CarSpace(c,car.getPlate(),time);
		plaza.setEntryTime(time);
		busyCarSpaces.add(plaza);
	    }
	    
    	}else if  ((car instanceof ElectricCar)||(car instanceof HybridCar)){
	    Coordinate cElectric = new Coordinate(lowerElectricZone,0);
	    CarSpace plazaElectrica = new CarSpace();
	    plazaElectrica.setCoordinate(cElectric);
	    
	    if(freeCarSpaces.higher(plazaElectrica).getPlate() == null){ //el primero que no esta ocupado 
		Coordinate c = freeCarSpaces.higher(plazaElectrica).getCoordinate();
		freeCarSpaces.remove(new CarSpace(c,null,null));
		CarSpace plaza = new CarSpace(c,car.getPlate(),time);
		plaza.setEntryTime(time);
		busyCarSpaces.add(plaza);
		ElectricCharger.connect();		
	    }
    	}
    }
    
    //SALIDAS
    public String carDeparture (String plate) {
  	String entryTime=null;
  	CarSpace plaza;
	
  	Iterator <CarSpace> it = busyCarSpaces.iterator();
	
  	while(it.hasNext()) {
	    plaza = (CarSpace) it.next();
	    String matricula = plaza.getPlate();
	    if(matricula.equals(plate)){
		Coordinate c = plaza.getCoordinate();
		entryTime = plaza.getEntryTime();
		it.remove();
		freeCarSpaces.add(new CarSpace(c,null,null));
		ElectricCharger.disconnect();
	    }
	}
	return entryTime;
    }
    
    //GUARDAR PARKING
    public void saveParking (String fileName) throws FileNotFoundException {
	File file = new File(fileName);
	PrintWriter pw = new PrintWriter(file);
	
	pw.println(maxZone+";"+sizeZone+";"+lowerElectricZone);
	
	TreeSet <CarSpace> hashSetToTreeSet = new TreeSet<>(comparadorTiempoYCoordenada);
	hashSetToTreeSet.addAll(busyCarSpaces);
	
	for (CarSpace coche : hashSetToTreeSet) {
	    pw.println(coche.toText());
	}
	pw.close();
    }
    
    //DIBUJO
    public String ToMap (String filename) throws FileNotFoundException {
	
	File fichero = new File(filename);
	PrintWriter pw = new PrintWriter(fichero);
	String espacio = "";
	
	TreeSet <CarSpace> dibujo = new TreeSet<>(busyCarSpaces);
	dibujo.addAll(freeCarSpaces);
	
    	for (CarSpace coche: dibujo) {
	    if (coche.getCoordinate().getZone() >= lowerElectricZone){
		if(coche.getPlate() != null){
		    espacio = espacio+coche.getCoordinate().toText()+" E " + coche.getPlate()+"|";
		    pw.print(espacio);
		    if (coche.getCoordinate().getNumber() == sizeZone) {
			espacio = "";
			espacio = espacio+"\n";
			pw.print(espacio);
		    }
		    espacio = "";
		    continue;
		    
		} else {
		    espacio = espacio+coche.toText()+" E        " + "|";
		    pw.print(espacio);
		    if (coche.getCoordinate().getNumber() == sizeZone) {
			espacio = "";
			espacio = espacio+"\n";
			pw.print(espacio);
		    }
		    espacio = "";
		    continue;
		}
	    }else{
		if(coche.getPlate() != null){
		    espacio = espacio+coche.getCoordinate().toText() + "   " + coche.getPlate()+"|";
		    pw.print(espacio);
		    if (coche.getCoordinate().getNumber() == sizeZone) {
			espacio = "";
			espacio = espacio+"\n";
			pw.print(espacio);
		    }
		    espacio="";
		    continue;
		    
		} else {
		    espacio = espacio+coche.getCoordinate().toText()+"          |";  		 
		    pw.print(espacio);
		    if (coche.getCoordinate().getNumber() == sizeZone) {
			espacio = "";
			espacio = espacio+"\n";
			pw.print(espacio);
		    }
		    espacio="";
		    continue;
		}
		
	    }
	}    	
        pw.close();
        return espacio;
    }
    
    
    
    public char getMaxZone() {
	return maxZone;
    }
    
    
    public void setMaxZone(char maxZone) {
	this.maxZone = maxZone;
    }
    
    
    public int getSizeZone() {
	return sizeZone;
    }
    
    
    public void setSizeZone(int sizeZone) {
	this.sizeZone = sizeZone;
    }
    
    
    public char getLowerElectricZone() {
	return lowerElectricZone;
    }
    
    
    public void setLowerElectricZone(char lowerElectricZone) {
	this.lowerElectricZone = lowerElectricZone;
    }
    
    
    public CarSpace[][] getCarSpaces() {
	return carSpaces;
    }
    
    
    public void setCarSpaces(CarSpace[][] carSpaces) {
	this.carSpaces = carSpaces;
    }
    
    
}