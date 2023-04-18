import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage3 {
    //atributos
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private Central central;
    private ArrayList<Pir> pirs; // esto pasa a sensores
    private ArrayList<ArrayList<Sensor>> zones;
    private Siren siren;
    private ArrayList<Persona> persons;
    //constructor
    public Stage3() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
        pirs = new ArrayList<Pir>(); // esto pasa a sensores

        central = new Central();

        //inicializando zonas
        zones = new ArrayList<>(3); // zona 0 puerta princ, zona 1 ventanas y puertas, zona 2 PIR
        for (int i = 0; i < 3; i++){ // son 3 pero utilizo 2 para esta stage
            zones.add(new ArrayList<Sensor>());
        }

        persons = new ArrayList<Persona>();
    }
    //metodos
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>

        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            doors.add(new Door());

            central.addNewSensor(doors.get(i).getMagneticSensor()); // es private asi que xd
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++){
            windows.add(new Window());

            central.addNewSensor(windows.get(i).getMagneticSensor());// es private asi que xd
        }

        int numPirs = in.nextInt();

        for(int i=0;i<numPirs;i++){
            in.nextLine();
            //reading <x> <y> <direction_angle> <sensing_angle> <sensing_range>
            pirs.add(new Pir(in.nextFloat(), in.nextFloat() , in.nextFloat(), in.nextFloat(), in.nextFloat()));
// delete this
            System.out.print("pos pir: (");System.out.print(pirs.get(i).getX());System.out.print(", ");System.out.print(pirs.get(i).getY());System.out.print(")"); System.out.println();


            central.addNewSensorPir(pirs.get(i).getSensor());

        }


        in.nextLine();
        String soundFile = in.next();
        siren = new Siren(soundFile);
        central.setSiren(siren);
        in.close();
    }
    //prints para la salida
    public void printHeader(PrintStream out){
        out.print("Step");

        for (int i = 0; i < doors.size(); i++)
            out.print("\t" + doors.get(i).getHeader());

        for (int i = 0; i < windows.size(); i++)
            out.print("\t" + windows.get(i).getHeader());

        out.print("\t" + siren.getHeader());
        out.print("\t" + central.getHeader());

        out.println();
    }
    public void printState(int step, PrintStream out){
        out.print(step); out.print("   ");

        for (int i = 0; i < doors.size(); i++)
            out.print("\t" + doors.get(i).getState());

        for (int i = 0; i < windows.size(); i++)
            out.print("\t" + windows.get(i).getState());

        out.print("\t");out.print(0);out.print("    ");// completar para siren
        out.print("\t");out.print(central.getState()); // completar para central

        out.println();
    }
    //interaccion usuario
    public void executeUserInteraction (Scanner in, PrintStream out){
        String command;
        char parameter;
        int i;
        int step = 0;

        boolean correct_command = true;
        printHeader(out);
        while (true) {

            if (correct_command)
                printState(step++, out);
            correct_command = true;

            command = in.next();
            if (command.charAt(0)=='x') break;
            switch (command.charAt(0)) {
                case 'd':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter == 'o') {
                        doors.get(i).open();

                    } else if (parameter == 'c'){
                        doors.get(i).close();

                    } else{
                        correct_command = false;
                    }
                    break;

                case 'w':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter == 'o') {
                        windows.get(i).open();

                    } else if (parameter == 'c'){
                        windows.get(i).close();

                    } else{
                        correct_command = false;
                    }
                    break;

                case 'k':
                    parameter = in.next().charAt(0);
                    switch (parameter) {
                        case 'a':
                            //checkzone incluye armado
                            central.checkZone();//todo, todas las puertas y ventanas deben estar cerradas
                            break;
                        case 'p':
                            System.out.println("no se");
                            //perimetro
                            break;
                        case 'b':
                            central.disarm();
                            break;
                        default:
                            correct_command = false;
                    }
                    break;
                case 'c':
                    float x= in.nextFloat();
                    float y= in.nextFloat();

                    Persona p = new Persona(x,y);

                    persons.add(p);

                    for(int j = 0;j < pirs.size(); j++){
                        Pir pir_actual = pirs.get(j);

                        boolean condicion_distancia = pir_actual.isNear( p.PerPosX() , p.PerPosY()) ;
                        boolean condicion_angulo = pir_actual.isInAngle( p.PerPosX() , p.PerPosY() );
                        if( condicion_distancia && condicion_angulo){
                            siren.play();
                            System.out.println("sirena sonando xd");

                        }else{
                            if (siren.getState() == 1){
                                siren.stop();
                                System.out.println("sirena sonandon't xd");
                            }
                        }
                    }
                    break;

                //borrar
                case 'p':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if(parameter =='w'){
                        persons.get(i).arriba();
                    }
                    if(parameter =='s'){
                        persons.get(i).abajo();
                    }
                    if(parameter =='d'){
                        persons.get(i).derecha();
                    }
                    if(parameter =='o'){
                        persons.get(i).izquierda();
                    }
                    System.out.print("(");System.out.print(persons.get(i).PerPosX());System.out.print(", ");System.out.print(persons.get(i).PerPosY());System.out.print(")"); System.out.println();

                    for(int j = 0;j < pirs.size(); j++){
                        Pir pir_actual = pirs.get(j);

                        boolean condicion_distancia = pir_actual.isNear( persons.get(i).PerPosX() , persons.get(i).PerPosY()) ;
                        boolean condicion_angulo = pir_actual.isInAngle( persons.get(i).PerPosX() , persons.get(i).PerPosY() );
                        if( condicion_distancia && condicion_angulo){
                            siren.play();
                            System.out.println("sirena sonando xd");

                        }else{
                            if (siren.getState() == 1){
                                siren.stop();
                                System.out.println("sirena sonandon't xd");
                            }
                        }
                    }

                    //siren.play();
                    break;
                case 's':
                    siren.stop();
                    break;
                default:
                    correct_command = false;
            }
            //central.checkZone(); // dudoso
        }
    }

    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner(new File(args[0]));
        //System.out.println("File: " + args[0]);
        Stage3 stage = new Stage3();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }
}