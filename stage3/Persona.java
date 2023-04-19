public class Persona {

    private Position position;

    public Persona(float x, float y){
        position = new Position(x, y);
        //delete
        System.out.print("(");System.out.print(x);System.out.print(", ");System.out.print(y);System.out.print(")"); System.out.println();

    }
    public Position getPosition(){
        return position;
    }
}
