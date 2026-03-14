/*
-------------------------------------------------------
Problem ID   : LC323
Title        : Number of Connected Components in Graph
Topic        : Graph
Pattern      : DFS / BFS / Union-Find
Difficulty   : Medium

Problem Summary:
Given n nodes labeled from 0 to n-1 and a list of edges,
return the number of connected components in the graph.

Example:
n = 5
edges = [[0,1],[1,2],[3,4]]

Graph:
0 - 1 - 2    3 - 4

Output = 2

-------------------------------------------------------

Approach 1: DFS
- Traverse graph and count components

Time  : O(V + E)
Space : O(V)

Approach 2: BFS
- Similar traversal using queue

Time  : O(V + E)
Space : O(V)

Approach 3: Union-Find (Disjoint Set)
- Merge sets using edges
- Count remaining roots

Time  : O(E * α(n))
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q22_LC323_NumberOfConnectedComponents {

    /*
    -------------------------------------------------------
    Approach 1: DFS
    -------------------------------------------------------
    */

    public static int countComponentsDFS(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges) {

            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                dfs(i, graph, visited);
                components++;
            }
        }

        return components;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor])
                dfs(neighbor, graph, visited);
        }
    }

    /*
    -------------------------------------------------------
    Approach 2: BFS
    -------------------------------------------------------
    */

    public static int countComponentsBFS(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges) {

            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                visited[i] = true;

                while (!q.isEmpty()) {

                    int curr = q.poll();

                    for (int neighbor : graph.get(curr)) {

                        if (!visited[neighbor]) {

                            visited[neighbor] = true;
                            q.add(neighbor);
                        }
                    }
                }

                components++;
            }
        }

        return components;
    }

    /*
    -------------------------------------------------------
    Approach 3: Union-Find
    -------------------------------------------------------
    */

    public static int countComponentsUF(int n, int[][] edges) {

        int[] parent = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;

        int components = n;

        for (int[] edge : edges) {

            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);

            if (root1 != root2) {

                parent[root1] = root2;
                components--;
            }
        }

        return components;
    }

    private static int find(int[] parent, int x) {

        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);

        return parent[x];
    }

    // Optional testing
    public static void main(String[] args) {

        int n = 5;

        int[][] edges = {
                {0,1},
                {1,2},
                {3,4}
        };

        System.out.println("DFS : " + countComponentsDFS(n, edges));
        System.out.println("BFS : " + countComponentsBFS(n, edges));
        System.out.println("UF  : " + countComponentsUF(n, edges));
    }
}