public class Door {
    //atributos
    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
    //constructor
    public Door () {
        magneticSensor = new MagneticSensor();
        close();
    }
    {
        id = nextId++;
    }

    public MagneticSensor getMagneticSensor() {
        return magneticSensor;
    }

    public void open() {
        state = State.OPEN;
        magneticSensor.moveMagnetAwayFromSwitch();
    }
    public void close() {
        state = State.CLOSE;
        magneticSensor.putMagnetNearSwitch();
    }
    public String getHeader(){
        return "d"+id;
    }
    public int getState(){
        if(state == State.OPEN)
            return 1;
        else{
            return 0;
        }
    }
}
