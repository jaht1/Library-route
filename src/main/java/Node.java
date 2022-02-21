import java.util.ArrayList;

public class Node {
    String name;
    String key;
    double latitude;
    double longitude;
    ArrayList<Node> neighbors = new ArrayList<Node>();
    double G = 0.0;
    double H = 0.0;
    Node previous;

    public Node() {

    }

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    // Beräknar uppskattade distansen från nuvarande nod till destinationen
    public double calculateH(Node endNode) {
        H = Utils.getDistance(latitude, longitude, endNode.latitude, endNode.longitude);
        return H;
    }

    // Beräknar & returnerar verkliga distansen från startnoden till nuvarande nod
    // via möjliga mellannoder
    public double calculateG(Node startNode) {
        G = 0.0;
        // Nuvarande nod
        Node current = new Node(name, latitude, longitude);
        previous = current;
        while (current != startNode) {

            double prevDist = Utils.getDistance(current.latitude, current.longitude, previous.latitude,
                    previous.longitude);
            double dist = Utils.getDistance(current.latitude, current.longitude, current.previous.latitude,
                    current.previous.longitude);
            G += dist;
            current = current.previous;
        }
        return G;
    }

    public double getF() {
        return G + H;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getPrevious() {
        return previous;
    }

}
