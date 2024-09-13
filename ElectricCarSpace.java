package P7;

public class ElectricCarSpace extends CarSpace{
    private ElectricCharger charger;
    
    public ElectricCarSpace(Coordinate coordinate,String plate,ElectricCharger charger) {
	this.coordinate=coordinate;
	this.plate=plate;
	this.charger=charger;
    }
}
