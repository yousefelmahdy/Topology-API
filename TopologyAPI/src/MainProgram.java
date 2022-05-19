// This unchecked exception is thrown by the Scanner for indicating that the retrieved token does not match the pattern
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {

    private static Topologies handler = new Topologies();

    public static void main(String[] args) {
        while (true) {
            int input = 0;
            Scanner scn = new Scanner(System.in);
            System.out.println();
            System.out.println("1- Read a topology from a given JSON file: ");
            System.out.println("2- Write a given topology from the memory to a JSON file: ");
            System.out.println("3- Query about which topologies are currently in the memory: ");
            System.out.println("4- Delete a given topology from memory: ");
            System.out.println("5- Query about which devices are in a given topology: ");
            System.out.println("6- Query about which devices are connected to a given netlist: ");
            System.out.println("7- To terminate program ");
         
            try {
            	input = scn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please check your inputs");
            }
            
            if(input == 1) {
            	readJSONMain();
            }
            else if(input == 2) {
            	writeJSONMain();
            }
            else if(input == 3) {
            	QueryTopologiesMain();
            }
            else if(input == 4) {
            	DeleteTopologyMain();
            }
            else if(input == 5) {
            	QueryDevicesMain();
            }
            else if(input == 6) {
            	QueryDevicesWithNetlistNodeMain();
            }
            else if(input == 7) {
            	// exit program
            	System.out.println("You Terminated The Program");
            	return;
            }
            else {
            	System.out.println("Please enter valid number");
            }
            
        }

    }

    private static void readJSONMain() {
        System.out.print("Enter JSON file name: ");
        Scanner scn = new Scanner(System.in);
        String fileName = scn.next();
        handler.readJSON(fileName);
    }

    private static void writeJSONMain() {
        System.out.printf("Topology Name: ");
        Scanner scn = new Scanner(System.in);
        String topologyName = scn.next();
        System.out.println();
        handler.writeJSON(topologyName);
    }

    private static void QueryTopologiesMain() {
        System.out.println("topologies :");
        System.out.println(handler.getTopologies());
    }

    private static void DeleteTopologyMain() {
        System.out.printf("Topology Name: ");
        Scanner scn = new Scanner(System.in);
        String topologyName = scn.next();
        boolean check = handler.deleteTopology(topologyName);
        if(!check) {
        	System.out.println("No such topology in memory");
        }
    }

    private static void QueryDevicesMain() {
        System.out.printf("Topology Name: ");
        Scanner scn = new Scanner(System.in);
        String topologyName = scn.next();
        System.out.println();
        System.out.println(handler.queryDevices(topologyName));
    }

    private static void QueryDevicesWithNetlistNodeMain() {
        Scanner scn = new Scanner(System.in);
        System.out.printf("Topology Name: ");
        String topologyName = scn.next();
        System.out.printf("Node Name: ");
        String deviceName = scn.next();
        System.out.println();
        System.out.println(handler.queryDevicesWithNetlistNode(topologyName, deviceName));
    }
}