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

}
