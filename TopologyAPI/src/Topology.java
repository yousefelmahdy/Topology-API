import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;


// topology contains everything like documentation
public class Topology {
	// VAR
    private final JSONObject json;
    private static List<Component> components = new ArrayList<>();
    private static HashMap<String, List<Component>> nodes = new HashMap<>();
    
    
    // FUN
    public HashMap<String, List<Component>> getNodes() {
        return nodes;
    }

    public JSONObject getJson() {
        return json;
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        return "Topology{ " +
                "json=" + json +
                ", components=" + components +
                '}';
    }

    public Topology(JSONObject json, List<JSONObject> components) {
        this.json = json;
        addToNodes(json);
        for (JSONObject obj : components) {
            this.components.add(new Component((String) obj.get("id"), (String) obj.get("type")));
        }
    }

    private  HashMap<String, List<Component>> addToNodes(JSONObject json) {
        for (JSONObject component : (List<JSONObject>) json.get("components")) {
        	// System.out.println(component);
            JSONObject netlist = (JSONObject) component.get("netlist");
            // System.out.println(netlist);
            
            /*List<String> temp = new ArrayList<>();
            temp.add(netlist.keySet())*/
            
            
            Set<String> keys = (Set<String>) netlist.keySet();
            // System.out.println(keys);

            for (String key : keys) {
                String keyValue = (String) netlist.get((String) key);
                // create empty node
                if (!nodes.containsKey(keyValue)) {
                	List<Component> temp = new ArrayList<>();
                    nodes.put(keyValue, temp);
                }
             // adding new values to old node
                nodes.get(keyValue).add(new Component((String) component.get("id"), (String) component.get("type")));
                
            }
        }
 
        return nodes;
    }
}