import java.util.ArrayList;

public class Central {
    //atributos
    public int getState(){
        return isArmed?1:0;
    }
    private ArrayList<Sensor> zone0;
    private boolean isArmed;
    private Siren siren;

    //constructor
    public Central(){
        zone0 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    //metodos
    public void arm() {
        isArmed=true;
    }
    public void disarm() {
        ....
    }
    public void setSiren(Siren s) {
        siren =s;
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }
    public void checkZone(){
        ...
    }
    public String getHeader(){
        return "Central";
    }
}
