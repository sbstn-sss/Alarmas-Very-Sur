import java.util.ArrayList;

public class Central {
    //atributos
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
        isArmed = true;
    }
    public void disarm() {
        isArmed = false;
    }
    public void setSiren(Siren s) {
        siren = s;
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }
    public void checkZone(){ // probablemente hay que agregar un parametro zone
        //recorer sensores
        boolean armable = true;
        for (int i = 0; i < zone0.size(); i ++){
            Sensor sensor_actual = zone0.get(i);
            System.out.println(sensor_actual.getState()); // borrar luego
            if (sensor_actual.getState() == SwitchState.CLOSE)
                armable = false;
        }
        if (armable) {
            arm();
            System.out.println("Se ha armado la zona");
        }else
            System.out.println("No se puede armar la zona"); // ver que pasa con el numero
    }
    public String getHeader(){
        return "Central";
    }
    public int getState(){
        return isArmed?1:0;
    }
}