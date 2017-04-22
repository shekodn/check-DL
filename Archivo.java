//&p-Archivo
//&b=46
public class Archivo implements Comparable <Archivo> {

    private String sName;
    private int iBlankLines;
    private int iLines;

    /**
    * empty constructor for file
    */
    //&i
    public Archivo(){
        this.sName = "no name";
        this.iBlankLines = 0;
        this.iLines = 0;
    }

    /**
     * Copy Constructor
     */
    //&i
    Archivo(Archivo a) {
        System.out.println("Copy constructor called");
        sName = a.getName();
        iLines = a.getLines();
        iBlankLines = a.getBlankLines();
    }

    /**
     * Default constructor
     * @param  String sName         [file name]
     * @param  int    iBlankLines   [number of blank lines]
     * @param  int    iLines        [number of lines with information]
     * @return        [Object file]
     */
     //&i
    public Archivo(String sName, int iBlankLines, int iLines){
        this.sName = sName;
        this.iBlankLines = iBlankLines;
        this.iLines = iLines;
    }

    /* Getters and setters */

    /**
    * gets file name
    * @return [sName]
    */
    //&i
    public String getName(){
        return sName;
    }

    /**
    * Changes the name of the file
    * @param String sName [variable to change the name of the file]
    */
    //&i
    public void setName(String sName){
        this.sName = sName;
    }

    /**
    * getBlankLines of file
    * @return [number of blank lines]
    */
    //&i
    public int getBlankLines(){
        return iBlankLines;
    }

    /**
    * setBlankLines
    * @param int iN [number of blank lines]
    */
    //&i
    public void setBlankLines(int iN){
        this.iBlankLines = iN;
    }

    /**
    * [get number of lines with information]
    * @return [number of lines with info]
    */
    //&i
    public int getLines(){
        return iLines;
    }

    /**
    * sets number of blank lines in file
    * @param int iN [number of blank lines]
    */
    //&i
    public void setLines(int iN){
        this.iLines = iN;
    }

    /* Special funcitons */

    /**
     * resets all File values
     */
     //&i
    public void resetValues(){
        this.sName = "reset name";
        this.iBlankLines = 0;
        this.iLines = 0;
    }

    //@Override
    /**
     * Function used to sort according to lines with information
     * @param  Archivo File to compare with
     * @return         [gretest file (according to lines with information)]
     */
     //&i
    public int compareTo(Archivo o) {
        int comparedSize = o.iLines;
        if (this.iLines > comparedSize) {
            return 1;
        } else if (this.iLines == comparedSize) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
    * Prints file description in the desired format
    */
    //&i
    public void printFileData(){
        System.out.println("Nombre del archivo: " + this.sName);
        System.out.println("Cantidad de líneas en blanco: " + this.iBlankLines);
        System.out.println("Cantidad de líneas con información: " + this.iLines);
        System.out.println("-------------------------------------------------");
    }
}
