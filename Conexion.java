import java.util.*;
import java.io.*;

public class Conexion {

    /* For txt file */
    protected String soname;
    protected String linkerName;
    protected String realName;

    protected boolean hasSoname = false;
    protected boolean hasLibHandler = false;
    protected boolean hasRealName = false;


    /** For cpp file */


    /* GETTERS */

    /* SETTERS */

    public void printReminders(){

        System.out.println("Link your library before compiling 'C' file");
    }

    public void printTxtReminders(){

        System.out.println("Compiled libraries");
        System.out.println("Example: gcc -fPIC -c \"List of desired libraries\"");

    }


}
