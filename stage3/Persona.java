public class Persona {

    private float posX; // se puede crear una clase posicion.
    private float posY;

    public Persona(float x,float y){
        this.posX=x;
        this.posY=y;
        //delete
        System.out.print("(");System.out.print(x);System.out.print(", ");System.out.print(y);System.out.print(")"); System.out.println();

    }

    public void norte(){

        posY +=0.5;
    }
    public void sur(){
        posY -=0.5;
    }
    public void este(){

        posX +=0.5;
    }
    public void oeste(){

        posX -=0.5;
    }

    public float PerPosX(){
        return posX;
    }
    public float PerPosY(){
        return posY;
    }

}
