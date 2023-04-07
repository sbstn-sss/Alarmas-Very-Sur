public class Sensor {
    public Sensor(){

        this(SwitchState.OPEN);
    }
    public Sensor(SwitchState s){
        this.state = s;
    }
    //metodos
    public SwitchState getState(){
        return state;
    }
    protected void setState(SwitchState s) {
        this.state = s;
    }
    public String toString(){
        if (state== SwitchState.CLOSE)
            return "1";
        else
            return "0";
    }
    //atributos
    private SwitchState state;
}
