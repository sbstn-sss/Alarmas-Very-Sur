public class persona {

    private float posX;
    private float posY;

    public persona(float x,float y){
        this.posX=x;
        this.posY=y;
    }

    public void norte(){

        posY +=0.5;
    }
    public void sur(){
        posY -=0.5;
    }
    public void este(){

        posX -=0.5;
    }
    public void oeste(){

        posX +=0.5;
    }

    public float PerPosX(){
        return posX;
    }
    public float PerPosY(){
        return posY;
    }

}
