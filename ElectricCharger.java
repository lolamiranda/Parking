package P7;

public class ElectricCharger {
    private boolean connected;
    private final static int POWER=25;
    
    
    public ElectricCharger() {
	connected=false;
    }
    
    static void connect(){
	setConnected(true);
    }
    
    static void disconnect(){
	setConnected(false);
    }
    
    public boolean isConnected() {
	return connected;
    }
    public static void setConnected(boolean connected) {
	connected = connected;
    }
    
    public static int getPOWER() {
	return POWER;
    }
}