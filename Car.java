package P7;

public abstract  class Car implements Comparable <Car>  {
    //Atributos
    String plate;
    String manufacturer;
    float batteryCharge;
    public static final String PLATE_FORMAT = "^[0-9]{4}[A-Z]{3}$";
    
    public static boolean isValidPlate (String plate) {
	if (plate.matches(PLATE_FORMAT)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public Car () {
    }
    
    public Car(String plate, String manufacturer,float batteryCharge) {
	this.plate=plate;
	this.manufacturer=manufacturer;
	this.batteryCharge=batteryCharge;
    }
    
    public Car (String plate) {
	this.plate = plate;
    }
    
    //Metodo compareTo
    public int compareTo(Car c) {
	int resultado=0;
	if(this.getPlate() !=null){
	    if ((this.getPlate()).compareTo(c.getPlate())<0) 
		resultado = -1;      
	    else if ((this.getPlate()).compareTo(c.getPlate())>0)   
		resultado = 1;        
	    else 
		resultado = 0;   
	} else
	    resultado = 0;
	return resultado;
    }
    
    //Getters, setters y toString
    abstract int getTotalPower();
    
    public abstract String toString();
    
    public String getPlate() {
	return plate;
    }
    
    public void setPlate(String plate) {
	this.plate = plate;
    }
    
    public String getManufacturer() {
	return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }
    
    public static String getPlateFormat() {
	return PLATE_FORMAT;
    }
    
    public float getBatteryCharge() {
	return batteryCharge;
    }
    
    public void setBatteryCharge(Float batteryCharge) {
	this.batteryCharge = batteryCharge;
    }
    
}