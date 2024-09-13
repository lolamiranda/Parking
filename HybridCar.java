package P7;

public class HybridCar extends Car {
    //atribustos de instancia
    private String plate;
    private String manufacturer;
    private int mechanicalPower;
    private int electricPower;
    private float batteryCharge;
    float newLevel;
    float actualLevel=0;
    
    public static final String PLATE_FORMAT = "^[0-9]{4}[A-Z]{3}$";
    public static final int M_MIN_POWER = 60;
    public static final int M_MAX_POWER = 500;
    public static final int E_MIN_POWER = 20;
    public static final int E_MAX_POWER = 100;
    public static final float MIN_BATTERY = 0.0F;
    public static final float MAX_BATTERY = 100.0F;
    public static final int BATTERY_CAPACITY = 15;
    
    public HybridCar () {
    }
    
    public HybridCar (String plate, String manufacturer, int mPower, int ePower, float batteryCharge) {
	this.plate = plate;
	this.manufacturer = manufacturer;
	mechanicalPower = mPower;
	electricPower = ePower;
	this.batteryCharge = batteryCharge;
	
    }
    
    //modos os
    public static boolean isValidPlate (String plate) {
	if (plate.matches(PLATE_FORMAT)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public static boolean isValidMPower (int mPower) {
	if ((mPower >= M_MIN_POWER) && (mPower <= M_MAX_POWER)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public static boolean isValidEPower (int ePower) {
	if ((ePower >= E_MIN_POWER) && (ePower <= E_MAX_POWER)) {
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
	acumuladorPotencia=electricPower+mechanicalPower;
	return acumuladorPotencia;
    }
    
    
    //Getters
    public String getPlate () {
	return this.plate;
    }
    public String getManufacturer () {
	return this.manufacturer;
    }
    public int getMPower () {
	return this.mechanicalPower;
    }
    public int getEPower () {
	return this.electricPower;
    }
    public float getBatteryCharge () {
		return this.batteryCharge;
    }
    public float getNewLevel() {
	return newLevel;
    }
    
    //Setters
    public void setPlate (String plate) {
	this.plate = plate;
    }
    public void setManufacturer (String manufacturer) {
	this.manufacturer = manufacturer;
    }
    public void setMPower (int mPower) {
	mechanicalPower = mPower;
    }
    public void setEPower (int ePower) {
	electricPower = ePower;
    }
    public void setNewLevel(float newLevel) {
	this.newLevel = newLevel;
    }
    public String toString() {
	return ("H;" + plate + ";" + manufacturer + ";" + mechanicalPower + ";" + electricPower + ";" + batteryCharge).replace(".",",");
    }
}