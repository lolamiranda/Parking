package P7;

public class CombustionCar extends Car{
    private String plate;
    private String manufacturer;
    private int mechanicalPower;
    
    public static final String PLATE_FORMAT = "^[0-9]{4}[A-Z]{3}$";
    public static final int MIN_POWER = 60;
    public static final int MAX_POWER = 500;
    
    public CombustionCar () {
    }
    
    public CombustionCar (String plate, String manufacturer, int power) {
	this.plate = plate;
	this.manufacturer = manufacturer;
	mechanicalPower = power;
    }
    
    public static boolean isValidPlate (String plate) {
	if (plate.matches(PLATE_FORMAT)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public static boolean isValidPower (int power) {
	if ((power >= MIN_POWER) && (power <= MAX_POWER)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    
    public String getPlate () {
    	return this.plate;

    }
    public String getManufacturer () {
    	return this.manufacturer;
    }
    public int getPower () {
    	return this.mechanicalPower;
    }
    
    public void setPlate (String plate) {
    	this.plate = plate;
    }
	
    public void setManufacturer (String manufacturer) {
    	this.manufacturer = manufacturer;
    }
    
    public void setPower (int power) {
    	mechanicalPower = power;
    }
    
    public String toString() {
    	return "C;" + plate + ";" + manufacturer + ";" + mechanicalPower ;
    }
    
    int getTotalPower() {
    	int acumuladorPotencia=0;
    	acumuladorPotencia=mechanicalPower;
		return acumuladorPotencia;
    }
    
}