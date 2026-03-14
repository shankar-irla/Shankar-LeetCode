/*
-------------------------------------------------------
Problem ID   : LC210
Title        : Course Schedule II
Topic        : Graph
Pattern      : Topological Sort
Difficulty   : Medium

Problem Summary:
You are given numCourses and prerequisites.

Return an ordering of courses you should take
to finish all courses.

If it is impossible (cycle exists), return empty array.

Example:
numCourses = 4
prerequisites = [[1,0],[2,0],[3,1],[3,2]]

Valid Output:
[0,1,2,3]

-------------------------------------------------------

Approach 1: DFS Topological Sort
- Use recursion
- Push nodes after exploring neighbors

Time  : O(V + E)
Space : O(V)

Approach 2: BFS (Kahn's Algorithm)
- Use indegree
- Process nodes with indegree 0

Time  : O(V + E)
Space : O(V)

-------------------------------------------------------
*/

import java.util.*;

public class Q21_LC210_CourseScheduleII {

    /*
    -------------------------------------------------------
    Approach 1: BFS (Kahn's Algorithm)
    -------------------------------------------------------
    */

    public static int[] findOrderBFS(int numCourses, int[][] prerequisites) {

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

        int[] order = new int[numCourses];
        int index = 0;

        while (!q.isEmpty()) {

            int course = q.poll();
            order[index++] = course;

            for (int next : graph.get(course)) {

                indegree[next]--;

                if (indegree[next] == 0)
                    q.add(next);
            }
        }

        if (index == numCourses)
            return order;

        return new int[0];
    }

    /*
    -------------------------------------------------------
    Approach 2: DFS Topological Sort
    -------------------------------------------------------
    */

    static boolean hasCycle = false;

    public static int[] findOrderDFS(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] p : prerequisites)
            graph.get(p[1]).add(p[0]);

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourses; i++) {

            if (!visited[i])
                dfs(i, graph, visited, recStack, stack);
        }

        if (hasCycle)
            return new int[0];

        int[] order = new int[numCourses];
        int i = 0;

        while (!stack.isEmpty())
            order[i++] = stack.pop();

        return order;
    }

    private static void dfs(int node, List<List<Integer>> graph,
                            boolean[] visited, boolean[] recStack,
                            Stack<Integer> stack) {

        if (recStack[node]) {
            hasCycle = true;
            return;
        }

        if (visited[node])
            return;

        visited[node] = true;
        recStack[node] = true;

        for (int next : graph.get(node))
            dfs(next, graph, visited, recStack, stack);

        recStack[node] = false;
        stack.push(node);
    }

    // Optional testing
    public static void main(String[] args) {

        int numCourses = 4;

        int[][] prerequisites = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        };

        System.out.println(Arrays.toString(
                findOrderBFS(numCourses, prerequisites)
        ));
    }
}