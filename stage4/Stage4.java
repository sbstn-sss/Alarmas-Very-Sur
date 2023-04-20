import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage4 {
    //atributos
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private ArrayList<Pir> pirs;
    private Central central;
    private ArrayList<ArrayList<Sensor>> zones;
    private Siren siren;

    private ArrayList<Persona> persons;


    //constructor
    public Stage4() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();

        pirs = new ArrayList<Pir>(); // esto pasa a sensores
        central = new Central();

        //inicializando zonas
        zones = new ArrayList<>(3); // zona 0 puerta princ, zona 1 ventanas y puertas, zona 2 PIR
        for (int i = 0; i < 3; i++){ // son 3 pero utilizo 2 para esta stage
            zones.add(new ArrayList<Sensor>());
        }

        pirs = new ArrayList<Pir>();
        persons = new ArrayList<Persona>();

    }
    //metodos
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        central.setZones(zones);

        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            doors.add(new Door());
            if (i == 0)
                central.addNewSensor(0, doors.get(i).getMagneticSensor());
            else
                central.addNewSensor(1, doors.get(i).getMagneticSensor()); // zona 1
        }

        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++){
            windows.add(new Window());

            central.addNewSensor(1 ,windows.get(i).getMagneticSensor());//
        }

        int numPirs = in.nextInt();
        for(int i = 0; i < numPirs; i++){
            in.nextLine();
            //reading <x> <y> <direction_angle> <sensing_angle> <sensing_range>
            pirs.add(new Pir(in.nextFloat(), in.nextFloat() , in.nextFloat(), in.nextFloat(), in.nextFloat()));


            central.addNewSensor(2, pirs.get(i));
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


        for (int i = 0; i < zones.get(2).size(); i++)
            out.print("\t" + ((Pir) zones.get(2).get(i)).getHeader());


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


        for (int i = 0; i < zones.get(2).size(); i++)
            out.print("\t" + zones.get(2).get(i).getState());


        out.print("\t");out.print(siren.getState());out.print("    ");
        out.print("\t");out.print(central.getState()); // completar para central

        out.println();
    }
    //interaccion usuario
    public void executeUserInteraction (Scanner in, PrintStream out){
        String command;
        char parameter;
        int i;
        int step = 0;
        int limit = 0; // si es 2, aplica para zona 0,1 ; si es 3, aplica para todas las zonas

        boolean correct_command = true;
        boolean done = false;
        printHeader(out);
        while (!done) {

            if (correct_command)
                printState(step++, out);
            correct_command = true;

            command = in.next();

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

                    boolean state_z0 = central.checkZone(0);
                    boolean state_z1 = central.checkZone(1); // para usar esto en abrir puerta y ventanas , colocar estas variables mas arriba

                    switch (parameter) {
                        case 'a':
                            if ( state_z0 && state_z1 ) { // si ambas son armables se arma
                                if(central.getState() == 0) {
                                    central.arm();
                                    limit = 3;
                                    System.out.println("Se ha armado la alarma");
                                }else{
                                    System.out.println("La alarma ya esta armada");
                                }
                            }else{
                                System.out.print("No se ha podido armar la alarma por las zonas:");
                                if(!state_z0) {
                                    System.out.print(" 0 ");
                                }
                                if (!state_z1)
                                    System.out.print(" 1 ");
                                System.out.println();
                            }
                            break;

                        case 'p':
                            if ( state_z0 && state_z1 ) { // si ambas son armables se arma
                                if(central.getState() == 0) {
                                    central.arm();
                                    limit = 2;
                                    System.out.println("Se ha armado la alarma");
                                }else{
                                    System.out.println("La alarma ya esta armada");
                                }
                            }else{
                                System.out.print("No se ha podido armar la alarma por las zonas:");
                                if(!state_z0) {
                                    System.out.print(" 0 ");
                                }
                                if (!state_z1)
                                    System.out.print(" 1 ");
                                System.out.println();
                            }
                            break;

                        case 'd':
                            if (central.getState() == 1) {
                                central.disarm();
                                System.out.println("Se ha desarmado la alarma");
                            }else{
                                System.out.println("La alarma ya esta desarmada");
                            }
                            break;

                        default:
                            correct_command = false;
                    }
                    break;


                case 'c':
                    float x = in.nextFloat();
                    float y = in.nextFloat();

                    Persona p = new Persona(x,y);

                    persons.add(p);

                    for(int j = 0; j < central.getZone(2).size(); j++) {
                        Pir pir_actual = ((Pir) central.getZone(2).get(j));

                        pir_actual.isInRange(p.getPosition());
                    }
                    break;

                //borrar
                case 'p':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if(parameter =='w'){
                        persons.get(i).getPosition().arriba();
                    }else if(parameter =='s'){
                        persons.get(i).getPosition().abajo();
                    }else if(parameter =='a'){
                        persons.get(i).getPosition().izquierda();;
                    }else if(parameter =='d'){
                        persons.get(i).getPosition().derecha();;
                    }else{
                        correct_command = false;
                        break;
                    }

                    System.out.print("(");System.out.print(persons.get(i).getPosition().getX());System.out.print(", ");System.out.print(persons.get(i).getPosition().getY());System.out.print(")"); System.out.println();

                    for(int j = 0; j < central.getZone(2).size(); j++) {
                        Pir pir_actual = ((Pir) central.getZone(2).get(j));

                        pir_actual.isInRange(persons.get(i).getPosition());

                    }
                    break;

                case 'x':
                    done = true;   // Added to finish the program
                    if (siren.getState() == 1)
                        siren.stop();
                    break;

                default:
                    correct_command = false;
            }
            if (central.getState() == 1)
                central.checkAllZones(limit); // AL FINAL TENIA SENTIDO XD, si se cumple que central = 1, que checkzone sea falso para z0 o z1,
                    // y que pir cambie a 0  // trabajar en eso ma;ana xd
        }
    }

    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner(new File(args[0]));
        //System.out.println("File: " + args[0]);
        Stage4 stage = new Stage4();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }
}
