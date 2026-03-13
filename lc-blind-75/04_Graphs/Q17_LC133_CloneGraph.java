/*
-------------------------------------------------------
Problem ID   : LC133
Title        : Clone Graph
Topic        : Graph
Pattern      : DFS / BFS + HashMap
Difficulty   : Medium

Problem Summary:
Given a reference of a node in a connected undirected graph,
return a deep copy (clone) of the graph.

Each node contains:
- value
- list of neighbors

-------------------------------------------------------

Approach 1: DFS + HashMap
- Traverse graph using DFS
- Clone node and store mapping

Time  : O(V + E)
Space : O(V)

Approach 2: BFS + HashMap
- Use queue to traverse level-wise

Time  : O(V + E)
Space : O(V)

-------------------------------------------------------
*/

import java.util.*;

class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Q17_LC133_CloneGraph {

    /*
    -------------------------------------------------------
    Approach 1: DFS
    -------------------------------------------------------
    */

    static HashMap<Node, Node> map = new HashMap<>();

    public static Node cloneGraphDFS(Node node) {

        if (node == null)
            return null;

        if (map.containsKey(node))
            return map.get(node);

        Node clone = new Node(node.val);
        map.put(node, clone);

        for (Node neighbor : node.neighbors) {

            clone.neighbors.add(cloneGraphDFS(neighbor));
        }

        return clone;
    }

    /*
    -------------------------------------------------------
    Approach 2: BFS
    -------------------------------------------------------
    */

    public static Node cloneGraphBFS(Node node) {

        if (node == null)
            return null;

        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);

        map.put(node, new Node(node.val));

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            for (Node neighbor : curr.neighbors) {

                if (!map.containsKey(neighbor)) {

                    map.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }

                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}