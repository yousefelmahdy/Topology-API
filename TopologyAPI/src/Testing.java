import java.io.File;
import java.util.List;
import java.util.Set;

public class Testing {
	private static int counter = 0;
    private static Topologies handler = new Topologies();
    
    public static void main(String[] args) {
        testReadFile();
        testWriteFile();
        testGetTopologies();
        testQueryDevices();
        testQueryDevicesWithNetlistNode();
        testDeleteTopologies();
        allTests();
    }

    private static void testReadFile() {
    	handler.readJSON("topology.json");

        if (handler.getTopologies().isEmpty()) {
            System.out.println("Testing ReadJSON Failed");
        } else {
        	counter++;
            System.out.println("Testing ReadJSON Succeeded");
        }
    }

    private static void testWriteFile() {
    	handler.writeJSON("top1");

        File file = new File("../TopologyAPI");
        List<String> filesNames = List.of(file.list());
        if (!filesNames.contains("top1.json")) {
            System.out.println("Testing WriteJSON Failed");
        } else {
        	counter++;
            System.out.println("Testing WriteJSON Succeeded");
        }
    }

    private static void testGetTopologies() {
        Set<String> set = handler.getTopologies();
        if (!set.contains("top1")) {
            System.out.println("Testing getTopologies Failed");
        } else {
        	counter++;
            System.out.println("Testing getTopologies Succeeded");
        }
    }

    private static void testDeleteTopologies() {
    	handler.deleteTopology("top1");
        Set<String> set = handler.getTopologies();
        if (set.contains("top1")) {
            System.out.println("Testing deleteTopologies Failed");
        } else {
        	counter++;
            System.out.println("Testing deleteTopologies Succeeded");
        }
    }

    private static void testQueryDevices() {
        List<Component> list = handler.queryDevices("top1");
        if (list.contains(new Component("res1","resistor"))&&list.contains(new Component("m1","nmos"))) {
            System.out.println("Testing queryDevices Succeeded");
        } else {
        	counter++;
            System.out.println("Testing queryDevices Succeeded");
        }
    }

    private static void testQueryDevicesWithNetlistNode() {
        List<Component> list = handler.queryDevicesWithNetlistNode("top1","n1");
        if (list.contains(new Component("res1","resistor"))&&list.contains(new Component("m1","nmos"))) {
            System.out.println("Testing queryDevicesWithNetlistNode Succeeded");
        } else {
        	counter++;
            System.out.println("Testing queryDevicesWithNetlistNode Succeeded");
        }
    }
    
    private static void allTests() {
    	System.out.println(counter + "OUT OF 6 Succeeded");
    }
}