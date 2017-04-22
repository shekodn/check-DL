import java.util.*;
import java.io.*;

public class CheckDll {

    private LinkedList <Conexion> lklConexiones;
    private Scanner scFileName; //scanner with user input to name files
    private LinkedList <Archivo> lklFiles; //Lista de archivo



    /**
     * Initializes variables
     */
    public void init(){
        lklConexiones = new LinkedList<Conexion>();
        lklFiles = new LinkedList<Archivo>();
        scFileName = new Scanner(System.in);

    }


    /**
     * The user put the name of the files he is going to scan
     * @param int iNumberOfFiles
     */
    void fileName(int iNumberOfFiles){

        String sName;
        int iCounter = 1;
        Analyzer analyzer = new Analyzer();

        for(int iI = 0; iI < iNumberOfFiles; iI++){

            System.out.print("Name for file " + iCounter + " out of " +
            iNumberOfFiles + " ->");
            System.out.println();
            sName = scFileName.nextLine();
            Archivo temporalFile = new Archivo();


            if(analyzer.isAFile(sName)){

                temporalFile.setName(sName);
                lklFiles.add(temporalFile);
                iCounter++;

            } else{
                System.out.println("PLEASE TRY AGAIN!" + "\n");
                iI-=1;
            }
        }
    }




    public void scan(){
        for(int iI = 0; iI < lklFiles.size(); iI++){
            Analyzer analyzer = new Analyzer();
            analyzer.readByLine(lklFiles.get(iI).getName());
        }
    }



    public void analyze(){
        //methods
        init();
        fileName(1); //2 files
        scan();
    }

    public static void main(String[] args) {
        //CheckDll checker = new CheckDll();
        //checker.analyze();

        String line = "gcc -shared -Wl, -soname=libDyn.so.1 -o libDyn.so.1.0.1 fileDone.o fileDtwo.o";
        int indexFrom = line.indexOf("=") + 1;
        String subLine = line.substring(indexFrom);

        String[] result = subLine.split("\\s");

        for (int x=0; x<result.length; x++) {
            System.out.println(result[x]);
        }

        String uno;
        uno = result[0].substring(0, result[0].lastIndexOf('.'));

    }
}
