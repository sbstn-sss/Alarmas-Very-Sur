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
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++)
            doors.add(new Door());

        central.addNewSensor(doors.get(i).magneticSensor); // es private asi que xd
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++)
            windows.add(new Window());

            central.addNewSensor(windows.get(i).magneticSensor); // es private asi que xd
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

        out.print("\t" + central.getHeader());
        out.print("\t" + central.getHeader());

        out.println();
    }
    public void printState(int step, PrintStream out){
        out.print(step); out.print("   ");

        for (int i = 0; i < doors.size(); i++)
            out.print("\t" + doors.get(i).getState());

        for (int i = 0; i < windows.size(); i++)
            out.print("\t" + windows.get(i).getState());

        out.print(0); // completar para siren
        out.print(0); // completar para central

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
                    parameter = in.next().charAt(0);
                    if (parameter == 'o') {
                        windows.get(0).open();

                    } else if (parameter == 'c'){
                        windows.get(0).close();

                    } else{
                        correct_command = false;
                    }
                    break;

                case 'k':
                    parameter = in.next().charAt(0);
                    switch (parameter) {
                        case 'a':
                            //todo, todas las puertas y ventanas deben estar cerradas
                            break;
                        case 'p';
                            //perimetro
                            break;
                        case 'b';
                            //desarmar
                            break;
                        default:
                            correct_command = false
                    }
                default:
                    correct_command = false;
            }
            central.checkZone();
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
