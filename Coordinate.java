package P7;

public class Coordinate implements Comparable <Coordinate>{
    private char zone;
    private int number;
    
    public Coordinate(){
    }
	
    public Coordinate(char zone, int number) {
	this.number=number;
	this.zone=zone;
    }
    
    public boolean equals(Object o){
        if(this.zone==((Coordinate) o).getZone()){
            if(this.number==((Coordinate) o).getNumber()){
                return true;
            }
        }
        return false;
    }
    
    public char getZone() {
	return zone;
    }
    
    public void setZone(char zone) {
	this.zone = zone;
    }
    
    public int getNumber() {
	return number;
    }
    
    public void setNumber(int number) {
	this.number = number;
    }
    
    public String toText() {
	return zone +String.valueOf(number);
    }
    
    public int compareTo(Coordinate c) {
	int resultado=0;
	
	if (this.zone<c.zone) 
	    resultado = -1;      
	else if (this.zone>c.zone)   
	    resultado = 1;      
	else 
	    if (this.number<c.number) 
        	resultado = -1;    
	    else if (this.number>c.number)    
        	resultado = 1;  
	    else 
        	resultado = 0;   
	return resultado;
    }
}