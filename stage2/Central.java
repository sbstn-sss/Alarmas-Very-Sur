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
        if (siren.getState() == 1)
            siren.stop();
    }
    public void setSiren(Siren s) {
        siren = s;
    }

    public void setZones(ArrayList<ArrayList<Sensor>> z){
        zones = z;
    }

    public ArrayList<Sensor> getZone(int zone_id){ // no usado de momento
        return zones.get(zone_id);
    }

    public void addNewSensor(int zone_id, Sensor s){
        zones.get(zone_id).add(s);
    }

    public boolean checkZone(int zone_id){
        //recorer sensores
        ArrayList<Sensor> zone = zones.get(zone_id);
        boolean armable = true;
        for (int i = 0; i < zone.size(); i ++){
            Sensor sensor_actual = zone.get(i);
            //System.out.println(sensor_actual.getState()); // borrar luego
            if (sensor_actual.getState() == 0)
                armable = false;
        }
        if (armable)
            return true;
        else
            return false;
    }
    public void checkAllZones(int limit) { // limites: 3 - all ; 2 - perimetral
        if (isArmed == true)
            for (int i = 0; i < limit; i++)
                for (int j = 0; j < zones.get(i).size(); j++) {
                    Sensor sensor_actual = zones.get(i).get(j);

                    if (sensor_actual.getState() == 0 && siren.getState() == 0) {
                        siren.play();
                    }

                }
    }
    public String getHeader(){
        return "Central";
    }
    public int getState(){
        return isArmed?1:0;
    }
}
