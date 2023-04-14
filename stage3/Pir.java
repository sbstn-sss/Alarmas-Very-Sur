public class Pir {
    //atributos
    private State state;

    private int x;

    private int y;

    private int direction_angle;

    private int sensing_angle;

    private int sensing_range;
    //constructor
    public Pir(int x,int y,int direction_angle,int sensing_angle,int sensing_range){
        this.x = x;
        this.y = y;
        this.direction_angle =direction_angle;
        this.sensing_angle=sensing_angle;
        this.sensing_range = sensing_range;

    }

    public void SetX(int x){
        this.x =x;
    }

    public  void SetY(int y){
        this.y = y;
    }

    public int GetX(){
        return x;
    }

    public  int GetY(){
        return y;
    }

    public  boolean getDistance(int a,int b){
        double distan =0;

        distan = Math.sqrt((x -a)^2 + (y-b)^2);
        if(distan > sensing_range){
            return false;
        }else{
            return true;
        }

    }

    public boolean getAngle(int a,int b){
        double angle =0;

        angle = Math.atan2(x-a,y-b);

        if (angle>= direction_angle && angle<= direction_angle + sensing_angle){
            return true;
        }else{
            return false;
        }

    }


}
