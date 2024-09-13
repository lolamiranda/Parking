package P7;

public class CarSpace implements Comparable <CarSpace> {
    Coordinate coordinate;
    String plate=null;
    String entryTime;
    
    public CarSpace() {
    }
    
    public CarSpace(Coordinate coordinate,String plate) {
	this.coordinate=coordinate;
	this.plate=plate;
    }
    
    public CarSpace(Coordinate coordinate,String plate,String entryTime) {
	this.coordinate=coordinate;
	this.plate=plate;
	this.entryTime=entryTime;
    }
    
    public String toText() {
	if(plate== null)  {
	    return coordinate.toText();
	}else {
	    return coordinate.toText()+";"+plate+";"+entryTime;
	}
    }
    
    public boolean equals(Object o){
	Coordinate coordinate2 = ((CarSpace) o).getCoordinate();
	if(equals(coordinate2)){
	    if(this.plate==((CarSpace) o).getPlate()){
		if(this.entryTime==((CarSpace) o).getEntryTime()){
		    return true;
		}
	    }
	}
	return false;
    }
    
    public Coordinate getCoordinate() {
		return coordinate;
    }
    
    public void setCoordinate(Coordinate coordinate) {
	this.coordinate = coordinate;
    }
    
    public String getPlate() {
	return plate;
    }
    
    public void setPlate(String plate) {
	this.plate = plate;
    }
    
    public String getEntryTime() {
	return entryTime;
	}
    
    public void setEntryTime(String entryTime) {
	this.entryTime = entryTime;
    }
    
    public int compareTo(CarSpace o) {
	if (this.coordinate.compareTo(o.getCoordinate()) < 0) {
	    return -1;
	} else if (this.coordinate.compareTo(o.getCoordinate()) > 0) {
	    return 1;
	} else {
	    return 0;
	}
    }
}