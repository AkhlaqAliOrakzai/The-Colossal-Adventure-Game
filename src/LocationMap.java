import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    /** TODO
     * create a static locations HashMap
     */
    static private Map<String, Integer> locationExits = new HashMap<>();
    static public Map<Integer, Location> locations = new HashMap<>();
    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger fl = new FileLogger();

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger cl = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */
        try
        {
            FileReader fr = new FileReader(LOCATIONS_FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null)
            {
                String [] arr = line.split(",");
                int locationId = Integer.parseInt(arr[0]);
                String descriptions = arr[1];
                Location l = new Location(locationId,descriptions,locationExits);
                locations.put(locationId, l);
                line = br.readLine();
            }

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        try
        {
            FileReader fr = new FileReader(DIRECTIONS_FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int id;
            while(line!=null)
            {
                String [] arr = line.split(",");
                int locationId = Integer.parseInt(arr[0]);
                String descriptions = arr[1];
                int destinations = Integer.parseInt(arr[2]);
                id = locationId;
                int counter = 0;
                while(line!=null)
                {
                    if(id!=locationId)
                    {
                        counter = 0;
                        break;
                    }
                    else
                    {
                        for (Map.Entry mapElement : locations.entrySet()) {
                            if(id==(int)mapElement.getKey())
                            {
                                Location l = (Location) mapElement.getValue();
                                if(counter == 0)
                                {
                                    l.setExits();
                                    counter++;
                                }
                                if(l.getLocationId()==id)
                                {
                                    l.addExit(descriptions,destinations);
                                }
                            }

                        }

                        line = br.readLine();
                        if(line!=null)
                        {
                            arr = line.split(",");
                            locationId = Integer.parseInt(arr[0]);
                            descriptions = arr[1];
                            destinations = Integer.parseInt(arr[2]);
                        }
                    }

                }

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**TODO
     * implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        //TODO
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        //TODO
        return (Location) locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return locations.entrySet();
    }
}
