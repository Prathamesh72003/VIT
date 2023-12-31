import java.util.*;

class Node implements Comparable<Node> {
    Node parent;
    int[] position;
    int g;
    int h;
    int f;

    public Node(Node parent, int[] position) {
        this.parent = parent;
        this.position = position;
        this.g = 0;
        this.h = 0;
        this.f = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Arrays.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }
}

public class AStar {
    public static List<int[]> astar(int[][] maze, int[] start, int[] end) {
        Node startNode = new Node(null, start);
        startNode.g = startNode.h = startNode.f = 0;
        Node endNode = new Node(null, end);
        endNode.g = endNode.h = endNode.f = 0;

        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.get(0);
            int currentIndex = 0;
            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).f < currentNode.f) {
                    currentNode = openList.get(i);
                    currentIndex = i;
                }
            }

            openList.remove(currentIndex);
            closedList.add(currentNode);

            if (currentNode.equals(endNode)) {
                List<int[]> path = new ArrayList<>();
                Node current = currentNode;
                while (current != null) {
                    path.add(current.position);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            List<Node> children = new ArrayList<>();
            int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
            for (int[] newPosition : directions) {
                int[] nodePosition = {currentNode.position[0] + newPosition[0], currentNode.position[1] + newPosition[1]};

                if (nodePosition[0] > maze.length - 1 || nodePosition[0] < 0 ||
                    nodePosition[1] > maze[0].length - 1 || nodePosition[1] < 0) {
                    continue;
                }

                if (maze[nodePosition[0]][nodePosition[1]] != 0) {
                    continue;
                }

                Node newNode = new Node(currentNode, nodePosition);
                children.add(newNode);
            }

            for (Node child : children) {
                if (closedList.contains(child)) {
                    continue;
                }

                child.g = currentNode.g + 1;
                child.h = (int) (Math.pow((child.position[0] - endNode.position[0]), 2) + Math.pow((child.position[1] - endNode.position[1]), 2));
                child.f = child.g + child.h;

                boolean shouldAdd = true;
                for (Node openNode : openList) {
                    if (child.equals(openNode) && child.g > openNode.g) {
                        shouldAdd = false;
                        break;
                    }
                }

                if (shouldAdd) {
                    openList.add(child);
                }
            }
        }

        return null; 
    }

    public static void main(String[] args) {
        int[][] maze = {
            {0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0},
        };

        int[] start = {3, 1};
        int[] end = {4, 3};

        List<int[]> path = astar(maze, start, end);

        if (path != null) {
            System.out.println("Path:");
            for (int[] position : path) {
                System.out.println("(" + position[0] + ", " + position[1] + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
