package P7;

import java.util.Comparator;

public class CarComparatorByBatteryLevelAndPlate implements Comparator <Car>{
    float bateria1,bateria2;
    
    public CarComparatorByBatteryLevelAndPlate() {
    }
    
    public int compare(Car c1, Car c2) {
	if (c1 instanceof CombustionCar) 
	    bateria1=0;
	else if (c1 instanceof ElectricCar) {
	    ElectricCar ec1 = (ElectricCar)c1;
	    bateria1 = ec1.getNewLevel();
	}
	else {
	    HybridCar hc1 = (HybridCar)c1;
	    bateria1 = hc1.getNewLevel();
	}
	
	if (c2 instanceof CombustionCar) 
	    bateria2=0;
	else if (c2 instanceof ElectricCar) {
	    ElectricCar ec2 = (ElectricCar)c2;
	    bateria2 = ec2.getNewLevel();
	}
	else {
	    HybridCar hc2 = (HybridCar)c2;
	    bateria2 = hc2.getNewLevel();
	}
	
	
	int resultado=0;
	if (bateria1 < bateria2) return -1;
	else if (bateria1 > bateria2) return 1;
	else 
	    if (c1.plate.compareTo(c2.plate)<0) 
		resultado = -1;      
	    else if (c1.plate.compareTo(c2.plate)>0)   
		resultado = 1;        
	    else 
		resultado = 0;
	return resultado;
    }		
}