public class Window {
    //atributos
    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId=0;

    //constructor
    public Window() {
        magneticSensor= new MagneticSensor();
        close();
    }
    {
        id = nextId++;
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
        return "w"+id;
    }
    public int getState(){
        if(state == State.OPEN)
            return 0;
        else {
            return 1;
        }
    }
    public Sensor getMagneticSensor(){
        return magneticSensor;
    }

}
