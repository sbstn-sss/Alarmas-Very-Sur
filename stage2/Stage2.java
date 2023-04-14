import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2 {
    //atributos
    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private Central central;
    private ArrayList<ArrayList<Sensor>> zones;
    private Siren siren;

    //constructor
    public Stage2() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
    }
    //metodos
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        central = new Central();

        //inicializando zonas
        zones = new ArrayList<>(2); // zona 0 puerta princ, zona 1 ventanas y puertas, zona 2 PIR
        for (int i = 0; i < 2; i++){ // son 3 pero utilizo 2 para esta stage
            zones.add(new ArrayList<Sensor>());
        }

        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++){
            doors.add(new Door());
            if (i == 0)
                central.addNewSensor(zones.get(0), doors.get(i).getMagneticSensor()); // es private asi que xd
            else
                central.addNewSensor(zones.get(1), doors.get(i).getMagneticSensor()); // zona 1
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++){
            windows.add(new Window());

            central.addNewSensor(zones.get(1) ,windows.get(i).getMagneticSensor());// es private asi que xd
        }

        in.nextLine();
        String soundFile = in.next();
        central.setZones(zones);
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

        out.print("\t");out.print(siren.getState());out.print("    ");// completar para siren
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
                        if (central.getState() == 1)
                            if(siren.getState() == 0)
                                siren.play();

                    } else if (parameter == 'c'){
                        doors.get(i).close();
                        if (central.getState() == 1)
                            if (central.checkZoneV2(zones.get(0)) && central.checkZoneV2(zones.get(1)))
                                siren.stop();

                    } else{
                        correct_command = false;
                    }
                    break;

                case 'w':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter == 'o') {
                        windows.get(i).open();
                        if (central.getState() == 1)
                            if(siren.getState() == 0)
                                siren.play();

                    } else if (parameter == 'c'){
                        windows.get(i).close();
                        if (central.getState() == 1)
                            if (central.checkZoneV2(zones.get(0)) && central.checkZoneV2(zones.get(1)))
                                siren.stop();

                    } else{
                        correct_command = false;
                    }
                    break;

                case 'k':
                    parameter = in.next().charAt(0);

                    boolean state_z0 = central.checkZoneV2(zones.get(0));
                    boolean state_z1 = central.checkZoneV2(zones.get(1)); // para usar esto en abrir puerta y ventanas , colocar estas variables mas arriba

                    switch (parameter) {
                        case 'a':
                            if ( state_z0 && state_z1 ) { // si ambas son armables se arma
                                if(central.getState() == 0) {
                                    central.arm();
                                    System.out.println("Se ha armado la alarma");
                                }else{
                                    System.out.println("La alarma ya esta armado");
                                }
                            }else{
                                System.out.println("No se ha podido armar la alarma por las zonas:");
                                if(!state_z0) {
                                    System.out.println(0);
                                }
                                if (!state_z1)
                                    System.out.println(1);
                                System.out.println();
                            }

                            break;
                        case 'p':
                            if ( state_z0 && state_z1 ) { // si ambas son armables se arma
                                if(central.getState() == 0) {
                                    central.arm();
                                    System.out.println("Se ha armado la alarma");
                                }else{
                                    System.out.println("La alarma ya esta armada");
                                }
                            }else{
                                System.out.println("No se ha podido armar la alarma debido a las siguientes zonas:");
                                if(!state_z0) {
                                    System.out.println(0);
                                }
                                if (!state_z1)
                                    System.out.println(1);
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
        Stage2 stage = new Stage2();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }
}
