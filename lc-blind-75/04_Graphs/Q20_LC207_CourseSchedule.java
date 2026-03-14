/*
-------------------------------------------------------
Problem ID   : LC207
Title        : Course Schedule
Topic        : Graph
Pattern      : Topological Sort / Cycle Detection
Difficulty   : Medium

Problem Summary:
You have numCourses labeled from 0 to n-1.

Some courses have prerequisites.

Example:
[1,0] means → to take course 1 you must first take 0.

Return true if you can finish all courses.

-------------------------------------------------------

Approach 1: DFS Cycle Detection
- Use visited + recursion stack
- If we revisit a node in recursion stack → cycle

Time  : O(V + E)
Space : O(V)

Approach 2: BFS Topological Sort (Kahn's Algorithm)
- Count indegree
- Process nodes with indegree 0

Time  : O(V + E)
Space : O(V)

-------------------------------------------------------
*/

import java.util.*;

public class Q20_LC207_CourseSchedule {

    /*
    -------------------------------------------------------
    Approach 1: DFS Cycle Detection
    -------------------------------------------------------
    */

    public static boolean canFinishDFS(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] p : prerequisites)
            graph.get(p[1]).add(p[0]);

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {

            if (dfs(i, graph, visited, recStack))
                return false;
        }

        return true;
    }

    private static boolean dfs(int node, List<List<Integer>> graph,
                               boolean[] visited, boolean[] recStack) {

        if (recStack[node])
            return true;

        if (visited[node])
            return false;

        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : graph.get(node)) {

            if (dfs(neighbor, graph, visited, recStack))
                return true;
        }

        recStack[node] = false;

        return false;
    }

    /*
    -------------------------------------------------------
    Approach 2: BFS (Kahn's Algorithm)
    -------------------------------------------------------
    */

    public static boolean canFinishBFS(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] p : prerequisites) {

            graph.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {

            if (indegree[i] == 0)
                q.add(i);
        }

        int completed = 0;

        while (!q.isEmpty()) {

            int course = q.poll();
            completed++;

            for (int next : graph.get(course)) {

                indegree[next]--;

                if (indegree[next] == 0)
                    q.add(next);
            }
        }

        return completed == numCourses;
    }

    // Optional testing
    public static void main(String[] args) {

        int numCourses = 2;
        int[][] prerequisites = {{1,0}};

        System.out.println("DFS : " + canFinishDFS(numCourses, prerequisites));
        System.out.println("BFS : " + canFinishBFS(numCourses, prerequisites));
    }
}