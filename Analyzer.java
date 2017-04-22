//&p-Analyzer
import java.io.*;
import java.util.*;

public class Analyzer {

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

        String soname = "-1";
        String realName = "-1";
        String linkerName = "-1";

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
                        }

                        if(iN == 2){

                            etapaExitosa = true;

                            soname = getSoName(strippedString);
                            realName = getRealName(strippedString);
                            linkerName = getLinkerName(strippedString);


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

                            if(!soname.contains("lib") && !soname.contains(".so") ){
                                System.out.println("soname: " + soname + " is incorrect!");
                                System.out.println("Remember that the syntax is: prefix lib + nombre + .so + .version");
                                etapaExitosa = false;
                            }

                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }
                        }

                        if(iN == 3){

                            etapaExitosa = true;

                            System.out.println("3. Install the library.");
                            System.out.println("Part 3.1");
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

                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }
                        }

                        if(iN == 4){

                            etapaExitosa = true;

                            System.out.println("Part 3.2");
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


                            if(etapaExitosa){
                                System.out.println("No mistakes were found.");
                            }
                        }

                        if(iN == 5){

                            etapaExitosa = true;

                            System.out.println("Part 3.3");
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
                        }
                    }
                }

                if(FILENAME.contains(".c")){

                }
            }

            br.close();

        } catch (IOException e) {

        }

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


}
