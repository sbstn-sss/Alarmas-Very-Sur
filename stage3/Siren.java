


import java.io.File;
import java.net.URL;

public class Siren {
    //atributos
    private URL dir;
    private boolean isSounding;
    private AePlayWave aWave;
    //constructor
    public Siren (String soundFileName){
        try {
            dir = new File(soundFileName).toURI().toURL();
        }
        catch (Exception exc){
            exc.printStackTrace(System.out);
        }
        isSounding = false;
    }
    //metodos
    public void play(){
        isSounding = true;
        aWave= new AePlayWave(dir);
        aWave.start();
    }
    public void stop(){
        isSounding = false;
        aWave.stopSounding();
    }
    public String getHeader() {
        return "Siren";
    }
    public int getState() {
        return isSounding?1:0;
    }
}