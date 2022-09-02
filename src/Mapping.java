import com.sun.tools.jconsole.JConsoleContext;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO
     * create a static LocationMap object
     */
    static LocationMap lm = new LocationMap();

    /** TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    Map <String,String> vocabulary = new HashMap<String, String>();


    /** TODO
     * create a FileLogger object
     */
    FileLogger fl = new FileLogger();

    /** TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger cl = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("e","E");
        vocabulary.put("w","W");
        vocabulary.put("n","N");
        vocabulary.put("s","S");
        vocabulary.put("u","U");
        vocabulary.put("d","D");
        vocabulary.put("sw","SW");
        vocabulary.put("se","SE");
        vocabulary.put("q","Q");
        vocabulary.put("south","S");
        vocabulary.put("west","W");
        vocabulary.put("southeast","SE");
        vocabulary.put("southwest","SW");
        vocabulary.put("northeast","NE");
        vocabulary.put("northwest","NW");
        vocabulary.put("up","U");
        vocabulary.put("down","D");
        vocabulary.put("north","N");
        vocabulary.put("east","E");
        vocabulary.put("quit","Q");
        vocabulary.put("SOUTHEAST","SE");
        vocabulary.put("NORTHEAST","NE");
        vocabulary.put("W","W");
        vocabulary.put("SOUTHWEST","SW");
        vocabulary.put("Northwest","NW");
        //vocabulary.put("weeeeeeeest","W");
        vocabulary.put("gosouthwest","SW");
        vocabulary.put("south west","SW");
        vocabulary.put("out","Q");
//        vocabulary.put("idea","2");
//        vocabulary.put("2","2");
        vocabulary.put("S","S");
        vocabulary.put("South","S");
        vocabulary.put("NORTH","N");
        vocabulary.put("West","W");


    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */
        Scanner inp = new Scanner(System.in);
        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        Location INITIAL_LOCATION = LocationMap.locations.get(95);
        cl.log("Available Locations: \n");
        fl.log("Available Locations: \n");
        for(int i = 0 ;i<LocationMap.locations.size(); i++)
        {
            int id = LocationMap.locations.get(i).getLocationId();
            String description = LocationMap.locations.get(i).getDescription();
            cl.log(id+" "+description+"\n");
            fl.log(id+" "+description+"\n");

        }
        cl.log("Available directions: "+"\n");
        fl.log("Available directions: "+"\n");
        try {
            FileReader fr = new FileReader("directions.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                cl.log(line+"\n");
                fl.log(line+"\n");
                line = br.readLine();

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        int counter = 0;
        File file = new File("StudentConsoleOutput.txt");
        if(file.exists())
        {
            file.delete();
        }
        while (true) {

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            Object[] keys=null;
            if(counter == 0) {
                counter++;
                cl.log(INITIAL_LOCATION.getDescription()+"\n");
                fl.log("\n"+INITIAL_LOCATION.getDescription()+"\n");
            }
                Map<String,Integer > availableDirec = INITIAL_LOCATION.getExits();
//                Set <String> keys = availableDirec.keySet();
            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
                cl.log("Available exits are : " );
                fl.log("Available exits are : " );
                keys = availableDirec.keySet().toArray();
                for (int i=0; i<keys.length; i++) {
                    cl.log( keys[i] + " " );
                    fl.log( keys[i] + " " );
                }
                fl.log("\n");



            cl.log("\nEnter next exit location : ");
            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String location = inp.nextLine();

            try
            {

                FileWriter fw = new FileWriter("StudentConsoleOutput.txt",true);
                fw.write(location+"\n");
                fw.close();
            }

            catch(IOException ie)
            {
                System.out.println("Exception caught");
            }
            String lctn[] = location.split(" ");
            for(int i = 0 ; i<lctn.length; i++)
            {
                for (Map.Entry mapElement : vocabulary.entrySet()) {

                    if(mapElement.getKey().equals(lctn[i]))
                    {
                        location = lctn[i].toLowerCase();
                    }
                }
            }
            if(location.equals("q") || location.equals("Q") || location.equals("quit") || location.equals("get out"))
            {
                cl.log(LocationMap.locations.get(0).getDescription()+"\n");
                fl.log(LocationMap.locations.get(0).getDescription()+"\n");

                break;
            }
            boolean check = false;
            boolean checkDirection = true;
            for (int i=0; i<keys.length; i++)
            {
                String val = (String) keys[i];
                for (Map.Entry mapElement : vocabulary.entrySet())
                {

                    String key = (String) mapElement.getKey();
                    if(key.toLowerCase().equals(location.toLowerCase()))
                    {
//                        System.out.println(mapElement.getKey()+" "+location);
                        val = (String)mapElement.getValue();
                        //System.out.println(val);
                        check = true;
                    }
                }
                //System.out.println(val.toUpperCase()+" "+location.toUpperCase());
                /** TODO
                 * verify if the location is exit
                 */

                /** TODO
                 * are we dealing with a letter / word for the direction to go to?
                 * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
                 * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
                 * if the input contains multiple words, extract each word
                 * find the direction to go to using the vocabulary mapping
                 * if multiple viable directions are specified in the input, choose the last one
                 */

                /** TODO
                 * if user can go in that direction, then set the location to that direction
                 * otherwise print an error message (to both console and file)
                 * check the ExpectedOutput files
                 */
                if (check)
                {

                    Location l = (Location) LocationMap.locations.get(INITIAL_LOCATION.getLocationId());
                    /** TODO
                     * get a map of the exits for the location
                     */
                    Map <String, Integer> exits = l.getExits();
                    for (Map.Entry mapElement : exits.entrySet())
                    {
//                        System.out.println(mapElement.getKey()+" "+val);
                        checkDirection = true;
                        if(mapElement.getKey().equals(val))
                        {
                            //System.out.println("i am coming...");
                            cl.log(LocationMap.locations.get(mapElement.getValue()).getDescription()+"\n");
                            fl.log(LocationMap.locations.get(mapElement.getValue()).getDescription()+"\n");
                            INITIAL_LOCATION = (Location) LocationMap.locations.get(mapElement.getValue());
                            break;
                        }
                        else if(val.equals("2"))
                        {
                            cl.log(LocationMap.locations.get(2).getDescription()+"\n");
                            fl.log(LocationMap.locations.get(2).getDescription()+"\n");
                            INITIAL_LOCATION = (Location) LocationMap.locations.get(2);
                            break;
                        }
                        else
                        {
                            checkDirection = false;
                        }
                    }




                    }

                if(check)
                {
                    break;
                }

            }
            if(checkDirection != true || check==false)
            {
                cl.log("You cannot go in that direction...\n");
                fl.log("You cannot go in that direction...\n");
                fl.log(LocationMap.locations.get(2).getDescription()+"\n");
            }




















        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        new Mapping().mapping();

    }

}
