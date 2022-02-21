import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Projekt 1 - ruttsökning med A*
 *
 * Datastrukturer och algoritmer
 *
 * Programmeringsteam: Jenna & Sophia
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LinkedHashMap<String, Node> nodes = (new GraphData()).createGraph();
		Route route = new Route();

		System.out.println(route.listNodesAndLinks());
		System.out.print("Ange startbiblioteket: ");
		String start = scan.nextLine();

		System.out.print("Ange destination: ");
		String dest = scan.nextLine(); 

		System.out.println("");
		System.out.println("Kortaste rutten: ");

		System.out.println(route.getRoute(nodes.get(start), nodes.get(dest)));
		scan.close();
	}

}
