public class Pir{
    //atributos
    private Sensor sensor;

    private State state;

    private float x;

    private float y;

    private float direction_angle;

    private float sensing_angle;

    private float sensing_range;
    //constructor
    public Pir(float x,float y,float direction_angle,float sensing_angle,float sensing_range){
        sensor = new Sensor();
        state = state.OPEN;
        this.x = x;
        this.y = y;
        this.direction_angle =direction_angle;
        this.sensing_angle=sensing_angle;
        this.sensing_range = sensing_range;

    }

    public void SetX(float x){
        this.x =x;
    }

    public  void SetY(float y){
        this.y = y;
    }

    public float GetX(){
        return x;
    }

    public  float GetY(){
        return y;
    }

    public  double getDistance(float a,float b){
        double distan =0;

        distan = Math.sqrt((x -a)*(x-a) + (y-b)*(y-b));
        return distan;
    }

    public void CloseP(){
        state = State.CLOSE;
    }

    public void OpenP(){
        state = State.OPEN;
    }

    public double getAngle(Float a,Float b){
        double angle =0;

        angle = Math.atan2(x-a,y-b);
        return angle;

    }

    public boolean IsNear(float a,float b){
        if(getDistance(a,b)>sensing_range){
            return false;
        }else{
            return true;
        }
    }

    public  boolean isInAngle(float a,float b){
        if(getAngle(a,b)>=direction_angle && getAngle(a,b)<=direction_angle+sensing_angle){
            return true;
        }else {
            return false;
        }

    }

    public Sensor getSensor() {
        return sensor;
    }
}
