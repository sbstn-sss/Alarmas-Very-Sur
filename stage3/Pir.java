public class Pir{
    //atributos
    private Sensor sensor;
    private State state;
    private float x; // crear clase posicion para esto
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

    public void setX(float x){
        this.x =x;
    }

    public  void setY(float y){
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public  float getY(){
        return y;
    }

    public void closeP(){
        state = State.CLOSE;
    }

    public void openP(){
        state = State.OPEN;
    }

    public boolean isNear(float a, float b){
        float distance = (float) Math.sqrt( Math.pow((x - a), 2) + Math.pow((y - b), 2));;

        System.out.print("distancia persona - sensor: "); System.out.print(distance); System.out.println();

        if(distance > sensing_range){
            return false;
        }else{
            return true;
        }
    }

    public  boolean isInAngle(float a, float b){

        float angle_rad = (float) Math.atan2((b - y) , (a - x));
        float angle = (angle_rad * 180)/ ((float) Math.PI) ;

        float cota_inf = direction_angle - sensing_angle;
        float cota_sup = direction_angle + sensing_angle;

        System.out.print("cota inf: "); System.out.print(cota_inf); System.out.print(", angulo: ");System.out.print(angle);System.out.print(", cota sup: ");System.out.print(cota_sup);System.out.println();


        if( angle >= cota_inf && angle <= cota_sup ){
            return true;
        }else {
            return false;
        }

    }

    public Sensor getSensor() {
        return sensor;
    }
}
