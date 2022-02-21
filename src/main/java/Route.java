import java.util.LinkedHashMap;
import java.util.Set;
import java.util.ArrayList;

public class Route {

    public String listNodesAndLinks() {

        LinkedHashMap<String, Node> nodes = GraphData.createGraph();

        Set<String> keys = nodes.keySet();
        String neighbours = "";
        String name = "";
        String names = "";

        // Loopar igenom alla bibliotek enligt node keys
        for (String key : keys) {

            name = nodes.get(key).getName();

            // Array för grannarna
            ArrayList<Node> arr = nodes.get(key).getNeighbors();
            for (int i = 0; i < arr.size(); i++) {

                // Skapar en string med alla grannar för utprintning
                neighbours += arr.get(i).getKey() + " ";
            }

            names += name + " Grannbibliotek: " + neighbours + "\n";
            neighbours = "";
        }
        return names;

    }

    public String getRoute(Node startNode, Node endNode) {

        ArrayList<Node> candidates = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Node> neigh = new ArrayList<Node>();

        Node current = new Node();
        current = startNode;
        boolean done = false;

        while (done == false) {

            double minF = 0;

            Node next = null;

            neigh = current.getNeighbors();

            // Loopar genom alla grannar till nuvarande nod
            for (int i = 0; i < current.getNeighbors().size(); i++) {

                // tillägger grannen i candidates-listan ifall grannen inte finns i visited elr
                // candidates
                if (!visited.contains(neigh.get(i)) && !candidates.contains(neigh.get(i))) {
                    candidates.add(neigh.get(i));

                    neigh.get(i).setPrevious(current);

                }

            }

            for (int i = 0; i < candidates.size(); i++) {
                // Om rutten är klarräknad slutar while-loopen
                if (candidates.get(i) == endNode) {
                    done = true;
                    break;

                } else {
                    double F = candidates.get(i).getF();
                    if (minF == 0 || minF > F) {
                        minF = F;
                        next = candidates.get(i);

                        // Uppdaterar kandidatens previous att vara current
                        if (neigh.contains(candidates.get(i))) {
                            candidates.get(i).setPrevious(current);
                        }

                    }
                }

            }

            if (done == false) {
                current = next;
                visited.add(current);
                candidates.remove(current);

            }

        }

        ArrayList<Node> route = new ArrayList<Node>();
        current = endNode;

        while (current != startNode) {

            route.add(current);
            current = current.previous;
        }
        route.add(startNode);
        String RouteName = "";

        // rutten printades fel håll med normal for loop så vi reversar ordningen -
        // funkar varje gång
        for (int j = route.size() - 1; j >= 0; j--) {
            RouteName += " --> " + route.get(j).getName();
        }
        return RouteName;
    }

}
