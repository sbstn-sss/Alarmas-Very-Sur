import java.util.ArrayList;
import java.util.Arrays;

public class Central {
    //atributos
    //private ArrayList<Sensor> zone0;
    // alternativa para distintas zonas
    private ArrayList<ArrayList<Sensor>> zones;
    private boolean isArmed;
    private Siren siren;

    //constructor
    public Central(){
        //zone0 = new ArrayList<Sensor>();
        zones = null;
        isArmed = false;
        siren = null;
    }
    //metodos
    public void arm() {
        isArmed = true;
    }
    public void disarm() {
        isArmed = false;
        siren.stop();
    }
    public void setSiren(Siren s) {
        siren = s;
    }

    public void setZones(ArrayList<ArrayList<Sensor>> z){
        zones = z;
    }

    public void addNewSensor(ArrayList<Sensor> zone, Sensor s){
        zone.add(s);
    }
    public void checkZone(ArrayList<Sensor> zone) { // probablemente hay que agregar un parametro zone
        //recorer sensores
        boolean armable = true;
        for (int i = 0; i < zone.size(); i++) {
            Sensor sensor_actual = zone.get(i);
            System.out.println(sensor_actual.getState()); // borrar luego
            if (sensor_actual.getState() == SwitchState.CLOSE)
                armable = false;
        }
        if (armable) {
            arm();

        }
    }
    public boolean checkZoneV2(ArrayList<Sensor> zone){ // probablemente hay que agregar un parametro zone
        //recorer sensores
        boolean armable = true;
        for (int i = 0; i < zone.size(); i ++){
            Sensor sensor_actual = zone.get(i);
            //System.out.println(sensor_actual.getState()); // borrar luego
            if (sensor_actual.getState() == SwitchState.OPEN)
                armable = false;
        }
        if (armable)
            return true;
        else
            return false;
    }

    public String getHeader(){
        return "Central";
    }
    public int getState(){
        return isArmed?1:0;
    }
}
