package P7;

public class ElectricCar extends Car {
    private String plate;
    private String manufacturer;
    private int electricPower;
    private float batteryCharge;
    float newLevel;
    float actualLevel;
   
    
    public static final String PLATE_FORMAT = "^[0-9]{4}[A-Z]{3}$";
    public static final int MIN_POWER = 50;
    public static final int MAX_POWER = 800;
    public static final float MIN_BATTERY =  (float) 0.0;
    public static final float MAX_BATTERY = (float) 100.0;
    public static final int BATTERY_CAPACITY = 100;
    
    public ElectricCar () {
    }
    
    public ElectricCar (String plate, String manufacturer, int power, float batteryCharge) {
	this.plate = plate;
	this.manufacturer = manufacturer;
	electricPower = power;
	this.batteryCharge = batteryCharge;
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
    
    public static boolean isValidBatteryCharge (float batteryCharge) {
	if ((batteryCharge >= MIN_BATTERY) && (batteryCharge <= MAX_BATTERY)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    
    public void increaseBatteryChargeLevel (float chargeTime) {
	int chargerPower = ElectricCharger.getPOWER();
	actualLevel = batteryCharge;
	newLevel = (float) (actualLevel +(chargeTime*chargerPower/BATTERY_CAPACITY)*100);
	if (newLevel>100)
	    newLevel = 100;
	this.batteryCharge = newLevel;
    }
    
    int getTotalPower() {
	int acumuladorPotencia=0;
	acumuladorPotencia=electricPower;
	return acumuladorPotencia;
    }
    
    public String getPlate () {
	return this.plate;
    }
    
    public String getManufacturer () {
	return this.manufacturer;
    }
    
    public int getPower () {
	return this.electricPower;
    }
    
    public float getBatteryCharge () {
	return this.batteryCharge;
    }
    
    public float getNewLevel() {
	return newLevel;
    }
    
    
    public void setPlate (String plate) {
	this.plate = plate;
    }
    
    public void setManufacturer (String manufacturer) {
		this.manufacturer = manufacturer;
    }
    public void setPower (int power) {
	electricPower = power;
    }
    
    public void setBatteryCharge (float batteryCharge) {
	this.batteryCharge = batteryCharge;
    }
    
    public void setNewLevel(float newLevel) {
	this.newLevel = newLevel;
    }
    
    public String toString() {
	return ("E;" + plate + ";" + manufacturer + ";" + electricPower + ";" + batteryCharge).replace(".",",");
    }
    
}