import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;


// many topology
public class Topologies {
    private static HashMap<String, Topology> topologies = new HashMap<>();

    private void addToTopologies(JSONObject json) {
        topologies.put((String) json.get("id"), new Topology(json, (List<JSONObject>) json.get("components")));
    }


    /**
     * This function read a given JSON file and adds it to Topologies
     * @param fileName string 
     */
    public void readJSON(String fileName) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            JSONObject json = (JSONObject) jsonParser.parse(reader);
            addToTopologies(json);
        } catch (FileNotFoundException e) {
        	System.out.println("File doesnt exist");
            // e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("Error in input/output");
            // e.printStackTrace();
        } catch (ParseException e) {
        	System.out.println("Error in parsing");
           // e.printStackTrace();
        }
    }
    

    /**
     * This function write given topology in a JSON file and store it
     * @param topologyID string
     */
    public void writeJSON(String topologyID) {
        try (FileWriter file = new FileWriter(topologyID + ".json")) {
            file.write(topologies.get(topologyID).getJson().toJSONString());
            file.flush();

        } catch (IOException e) {
        	System.out.println("Error in input/output");
            // e.printStackTrace();
        } catch (NullPointerException e) {
        	System.out.println("Not a valid input");
            //e.printStackTrace();
        }
    }

    /**
     * which topologies are currently in the memory
     * @return returning List of topologies
     */
    public Set<String> getTopologies() {
        return topologies.keySet();
    }

    /**
     * This function deletes a given topology from memory
     * @param topologyID string
     * @return true or false
     */
    public boolean deleteTopology(String topologyID) {
        if (topologies.containsKey(topologyID)) {
            topologies.remove(topologyID);
            return true;
        }
        return false;
    }

    /**
     * which devices are in a given topology
     * @param topologyID string
     * @return List of components
     */
    public List<Component> queryDevices(String topologyID) {
        if (topologies.get(topologyID) == null) {
            System.out.println("Topology not found in Memory");
            // empty one 
            return new ArrayList<Component>();
        }
        List<Component> temp = topologies.get(topologyID).getComponents();
        return temp;
    }

    /**
     * which devices are connected to a given netlist node in a given topology
     * @param topologyID string
     * @param node string
     * @return List of nodes to a given topology
     */
    public List<Component> queryDevicesWithNetlistNode(String topologyID, String node) {
        if(topologies.get(topologyID) == null){
            System.out.println("Topology not found in Memory");
            return new ArrayList<>();
        }
        if(topologies.get(topologyID).getNodes().get(node) == null){
            System.out.println("node not found in Memory");
            return new ArrayList<>();
        }
        return topologies.get(topologyID).getNodes().get(node);
    }
}