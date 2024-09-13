package P7;

import java.util.Comparator;

public class CarComparatorByTimeAndCoordinate implements Comparator <CarSpace> {
    
    public int compare(CarSpace o1, CarSpace o2) {
	int resultado;
	if (o1.getEntryTime().compareTo(o2.getEntryTime()) < 0) return -1; // b1 < b2
	else if (o1.getEntryTime().compareTo(o2.getEntryTime()) > 0) return 1; // b1 > b2
	else 
	    resultado = o1.getCoordinate().compareTo(o2.coordinate) ;
	return resultado;
	
    }
}