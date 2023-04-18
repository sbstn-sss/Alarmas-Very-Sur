public class Persona {

    private float posX; // se puede crear una clase posicion.
    private float posY;

    public Persona(float x,float y){
        this.posX=x;
        this.posY=y;
        //delete
        System.out.print("(");System.out.print(x);System.out.print(", ");System.out.print(y);System.out.print(")"); System.out.println();

    }

    public void arriba(){

        posY +=0.5;
    }
    public void abajo(){
        posY -=0.5;
    }
    public void derecha(){

        posX +=0.5;
    }
    public void izquierda(){

        posX -=0.5;
    }

    public float getX(){
        return posX;
    }
    public float getY(){
        return posY;
    }

}
