//&p-Analyzer
import java.io.*;
import java.util.*;



public class Analyzer {


    private String soname = "-1";
    private String realName = "-1";
    private String linkerName = "-1";


    /**
     * Checks if given file name it's actually a file
     * @param  String fileName
     * @return  true if it is a file
     */
     //&i
    public boolean isAFile(String fileName){

        File f = new File(fileName);

        if (f.isFile() && f.canRead()) {

            //System.out.println(fileName + " is a FILE ");
            return true;

        } else{
            System.out.println(fileName + " is not FILE ");
            return false;
        }
    }

    /**
     * [readByLine2 description]
     * @param  String  fileName
     * @param  Archivo archivo
     * @return a file with updated infomration
     */
     //&i
    public void readByLine(String fileName){

        String FILENAME = fileName;
        BufferedReader br = null;
        FileReader fr = null;
        int iN = 0;

        String handeler="...";
        boolean chido = true;
        boolean handeleropen = false;
        boolean handelerclose = false;

        LinkedList<String> lklOfC = new LinkedList<String>();

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(FILENAME));
            LinkedList<String> nombresTxt = new LinkedList<String>();
            boolean etapaExitosa = true;



            while ((sCurrentLine = br.readLine()) != null) {

                String strippedString = sCurrentLine.trim();

                if(FILENAME.contains(".txt")){

                    if(strippedString.contains("gcc") || strippedString.contains("cp") || strippedString.contains("ln")){

                        iN++;

                        if(iN == 1){

                            etapaExitosa = true;

                            getcFiles(strippedString, lklOfC);

                            System.out.println("1. Compilation of the source code phase");
                            if(!strippedString.contains("-fPIC")){
                                System.out.println("Missing -fPIC");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("-c")){
                                System.out.println("Missing -c");
                                etapaExitosa = false;
                            }

                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }

                            System.out.println("\n");

                        }

                        if(iN == 2){

                            etapaExitosa = true;
                            soname = getSoName(strippedString);
                            realName = getRealName(strippedString);
                            linkerName = getLinkerName(strippedString);
                            System.out.println(linkerName + "LOLA");


                            System.out.println("2. Merge files and build the library.");
                            if(!strippedString.contains("-shared")){
                                System.out.println("Missing -shared");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("-Wl")){
                                System.out.println("Missing -Wl");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains(",")){
                                System.out.println("Missing , between suffix and soname");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("soname=")){
                                System.out.println("Missing soname=");
                                etapaExitosa = false;
                            }

                            if(!soname.contains("lib") || !soname.contains(".so") || !hasVersion(soname, 2)){
                                System.out.println("soname: " + soname + " is incorrect!");
                                System.out.println("Remember that the syntax is: prefix lib + nombre + .so + .version");
                                etapaExitosa = false;
                            }

                            if(!hasVersion(realName, 3)){
                                System.out.println("realName: " + realName + " is incorrect!");
                                System.out.println("Remember that the syntax is: soname + .minor_num [ + .release ]");
                                etapaExitosa = false;
                            }

                            if(!compareOtoC(strippedString, lklOfC)){
                                System.out.println("Remember that your .C files must match with your object files");
                                etapaExitosa = false;
                            }


                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }

                            System.out.println("\n");

                        }

                        if(iN == 3){

                            etapaExitosa = true;

                            System.out.println("3. Install the library.");

                            if(!strippedString.contains("cp")){
                                System.out.println("Missing cp");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("/usr/lib")){
                                System.out.println("Missing /usr/");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains(realName)){
                                System.out.println("Missing " + realName);
                                etapaExitosa = false;
                            }
                        }

                        if(iN == 4){

                            if(!strippedString.contains("-sf")){
                                System.out.println("Missing –sf");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("/usr/lib/" + realName)){
                                System.out.println("Missing /usr/lib/" + realName);
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("/usr/lib/" + linkerName)){
                                System.out.println("Missing /usr/lib/" + linkerName);
                                etapaExitosa = false;
                            }
                        }

                        if(iN == 5){

                            if(!strippedString.contains("-sf")){
                                System.out.println("Missing –sf");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("/usr/lib/" + realName)){
                                System.out.println("Missing /usr/lib/" + realName);
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("/usr/lib/" + soname)){
                                System.out.println("Missing /usr/lib/" + soname);
                                etapaExitosa = false;
                            }


                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }

                            System.out.println("\n");

                        }


                        if(iN == 6){

                            etapaExitosa = true;

                            System.out.println("4. Compile the program that will use the library.");
                            if(!strippedString.contains("-Wall")){
                                System.out.println("Missing -Wall");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("-L/path/to/lib")){
                                System.out.println("Missing –L/path/to/lib");
                                etapaExitosa = false;
                            }

                            if(!strippedString.contains("-lDyn")){
                                System.out.println("Missing –lDyn");
                                etapaExitosa = false;
                            }

                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }

                            System.out.println("\n");

                        }
                    }
                }

                if(FILENAME.contains(".c")){
                    if(strippedString.contains("void")){ // Buscar por el putnador void para ser el handeler de la librerias dinamicas
                        String result="";
                        boolean checkifFunction = false; // verificar que si tiene  () lo mas probable es que es una llamada de funcion en vez de un handeler tmb asumire que solo es 1
                        for(int iA=0;iA<strippedString.length();iA++){
                            if(strippedString.charAt(iA)=='*'){
                                    iA++;
                                while(strippedString.charAt(iA)!=';' && strippedString.charAt(iA)!='(' && strippedString.charAt(iA)!=')'){
                                    result+=strippedString.charAt(iA);
                                    iA++;
                                }
                            }
                            if(strippedString.charAt(iA)=='('||strippedString.charAt(iA)==')'){
                                checkifFunction=true;
                            }
                        }
                        if(!checkifFunction){
                            handeler = result;
                        }
                    }else if(strippedString.contains(handeler) && strippedString.contains("dlopen")){ //handler va a ser utilizado para algo
                        String strippedString2 = "";

                        for(int iA=0;iA < strippedString.length();iA++){
                            if(strippedString.charAt(iA) != ' '){
                                strippedString2+=strippedString.charAt(iA);
                            }
                        }

                        if(strippedString2.contains(handeler + "=dlopen(\"/opt/lib/") && strippedString2.contains("\",RTLD_LAZY")){//el handeler va a abrir algo
                            handeleropen=true;
                            if(!strippedString2.contains(linkerName)){

                                    System.out.println(strippedString2);
                                    System.out.println(linkerName);
                                System.out.println("The linker name wasnt generated or is misspelled");

                                chido=false;
                            }
                        }else{
                            System.out.println("Wrong syntax in the opening of dynamic librery");
                            chido=false;

                            if(!strippedString2.contains(linkerName)){
                                System.out.println("The linker name wasnt generated or is misspelled");
                            }
                        }
                    }else if(strippedString.contains(handeler) && strippedString.contains("dlclose")){
                            String strippedString2 = "";
                            for(int iA=0;iA<strippedString.length();iA++){
                                if(strippedString.charAt(iA) !=' '){
                                    strippedString2+=strippedString.charAt(iA);
                                }
                            }
                            if(strippedString2.contains("dlclose("+handeler+");")){
                                handelerclose = true;
                            }
                    }
                }
            }


            br.close();

        } catch (IOException e) {

        }
        if(handelerclose^handeleropen){
            System.out.println("You forgot to either open or close the dynamic librery");
            chido = false;
        }
        if(chido){

            System.out.println("All good homes");
        }

    }

    public void getcFiles(String linea, LinkedList<String> list){
        String[] result = linea.split("\\s");

        for(int iA=0; iA<result.length;iA++){
            if(result[iA].contains(".c")){
                String[] result2 = result[iA].split("\\.");
                list.add(result2[0]);
            }
        }
    }


    public boolean compareOtoC(String linea, LinkedList<String> list){

        String[] result = linea.split("\\s");

        LinkedList<String> list2 = new LinkedList<String>();

        for(int iA=0; iA<result.length;iA++){
            if(result[iA].contains(".o")){
                String[] result2 = result[iA].split("\\.");
                list2.add(result2[0]);
                if(result2[0].equals('*'))

                    //System.out.println("result2[0]" + result2[0]);
                    return true;
            }
        }

        // System.out.println(list.containsAll(list2));
        //
        // System.out.println(list);
        //
        // System.out.println(list2);

        return list.containsAll(list2);

    }


    public String getLinkerName(String line){

        int indexFrom = line.indexOf("=") + 1;
        String subLine = line.substring(indexFrom);

        String[] result = subLine.split("\\s");

        String uno;
        uno = result[0].substring(0, result[0].lastIndexOf('.'));


        return uno;
    }

    public String getSoName(String line){

        int indexFrom = line.indexOf("=") + 1;
        String subLine = line.substring(indexFrom);

        String[] result = subLine.split("\\s");

        return result[0];
    }

    public String getRealName(String line){

        int indexFrom = line.indexOf("=") + 1;
        String subLine = line.substring(indexFrom);

        String[] result = subLine.split("\\s");

        return result[2];
    }

    public boolean hasVersion(String word, int iN){

        String[] result = word.split("\\.");

        // for (int x=0; x<result.length; x++) {
        //     System.out.println(result[x]);
        // }

        if(result.length > iN){

            return true;
        }
        return false;
    }
}
