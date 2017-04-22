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
        CheckDll checker = new CheckDll();
        checker.analyze();


    }
}
