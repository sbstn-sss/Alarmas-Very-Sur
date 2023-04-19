public class Position { // no implementada aun
    //atributos
    private float x;
    private float y;

    //constructores
    public Position(){
        x = 0;
        y = 0;
    }
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
    //metodos
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    // add movement
    public void arriba(){
        y += 0.5;
    }
    public void abajo(){
        y -= 0.5;
    }
    public void derecha(){
        x += 0.5;
    }
    public void izquierda(){
        x -= 0.5;
    }

}
