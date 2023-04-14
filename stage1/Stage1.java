import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage1 {
    //atributos
    private ArrayList< Door > doors;
    private ArrayList< Window > windows;
    public Stage1() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
    }
    // lectura de config.txt
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++)
            doors.add(new Door());
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++)
            windows.add(new Window());

        //System.out.println(numDoors);
        //System.out.println(numWindows);
        in.close();
    }

    //prints para la salida
    public void printHeader(PrintStream out){
        out.print("Step");

        for (int i = 0; i < doors.size(); i++)
            out.print("\t" + doors.get(i).getHeader());

        for (int i = 0; i < windows.size(); i++)
            out.print("\t" + windows.get(i).getHeader());

        out.println();
    }
    public void printState(int step, PrintStream out){
        out.print(step); out.print("   ");

        for (int i = 0; i < doors.size(); i++)
            out.print("\t" + doors.get(i).getState());

        for (int i = 0; i < windows.size(); i++)
            out.print("\t" + windows.get(i).getState());

        out.println();
    }

    //interaccion usuario
    public void executeUserInteraction (Scanner in, PrintStream out){
        String command;
        char parameter;
        int i;
        int step = 0;

        boolean correct_command = true;
        boolean done = false;
        printHeader(out);
        while (!done) {

            if (correct_command)
                printState(step++, out); // se podria agregar un try que haga que al ingresar un comando invalido, no se ejecute print state.
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
                case 'w': // caso 0
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

                case 'x':
                    done = true;   // Added to finish the program
                    break;
                default:
                    correct_command = false;
            }
        }
    }

    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner(new File(args[0]));
        //
        //System.out.println("File: " + args[0]);
        //
        Stage1 stage = new Stage1();

        //lectura de config.txt
        stage.readConfiguration(in);
        //acciones del usuario
        Scanner in_ui = new Scanner(System.in);
        PrintStream out_ui = new PrintStream(new File("output.csv"));
        stage.executeUserInteraction(in_ui, out_ui); // falta la interaccion con los sensores magneticos.
    }
}
