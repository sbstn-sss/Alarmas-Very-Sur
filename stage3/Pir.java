public class Pir extends Sensor{
    //atributos
    private Position position;
    private float direction_angle;
    private float sensing_angle; //
    private float sensing_range;

    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
    //constructor
    public Pir(float x,float y,float direction_angle,float sensing_angle,float sensing_range){
        position = new Position(x, y);

        this.direction_angle = direction_angle;
        this.sensing_angle = sensing_angle;
        this.sensing_range = sensing_range;

    }
    {
        id = nextId++;
    }

    //metodos
    public String getHeader(){
        return "p"+id;
    }

    public int getState(){
        if(state == SwitchState.OPEN)
            return 1;
        else{
            return 0;
        }
    }

    public Position getPosition(){
        return position;
    }

    public void isInRange(Position position_p) {
        float x = position.getX(); float y = position.getY();
        float a = position_p.getX(); float b = position_p.getY();

        boolean condicion_distancia = false;
        boolean condicion_angulo = false;

        //is near?
        float distance = (float) Math.sqrt(Math.pow((x - a), 2) + Math.pow((y - b), 2));

        System.out.print("distancia persona - sensor: ");
        System.out.print(distance);
        System.out.println(); // delete this

        if (distance <= sensing_range)
            condicion_distancia = true;

        //is in angle?
        float angle_rad = (float) Math.atan2((b - y), (a - x));
        float angle = (angle_rad * 180) / ((float) Math.PI);

        float cota_inf = direction_angle - (sensing_angle / 2);
        float cota_sup = direction_angle + (sensing_angle / 2);

        System.out.print("cota inf: ");
        System.out.print(cota_inf);
        System.out.print(", angulo: ");
        System.out.print(angle);
        System.out.print(", cota sup: ");
        System.out.print(cota_sup);
        System.out.println();// delete this


        if (angle >= cota_inf && angle <= cota_sup)
            condicion_angulo = true;

        //condiciones
        if (condicion_angulo && condicion_distancia) {
            setState(SwitchState.CLOSE);
           // return true;
        }else {
            setState(SwitchState.OPEN);
          //  return false;
        }
    }
}