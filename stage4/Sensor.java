public class Sensor {
    //atributos
    protected SwitchState state;
    //Constructor
    public Sensor(){

        this(SwitchState.OPEN);
    }
    public Sensor(SwitchState s){
        this.state = s;
    }

    //metodos

    public int getState(){
        if (state == SwitchState.CLOSE)
            return 1;
        else
            return 0;
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
}
