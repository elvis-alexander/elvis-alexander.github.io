package graph;

/**
 * Created by kingfernandez on 10/22/17.
 */

import java.util.*;

public class Graph {
    Map<Integer, List<Edge>> adjList;



    /**
     * For the sake of cutting down on code adding vertices to the map by default
     * @param vertices
     */
    public Graph(List<Integer> vertices) {
        adjList = new HashMap<>();
        for(int v: vertices)
            adjList.put(v, new LinkedList<Edge>());
    }

    public void addEdge(int src, int dst, int weight) {
        Edge edge = new Edge(dst, weight);
        adjList.get(src).add(edge);
    }

    /* in case a graph doesn't care about weights */
    public void addEdge(int src, int dst) {
        addEdge(src, dst, -1);
    }

    public LinkedList<Integer> dfs() {
        /* used for testing only */
        LinkedList<Integer> dfsOrder = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for(Integer u: adjList.keySet()) {
            if (!visited.contains(u))
                dfsUtil(u, visited, dfsOrder);
        }
        return dfsOrder;
    }

    private void dfsUtil(Integer u, HashSet<Integer> visited, LinkedList<Integer> dfsOrder) {
        dfsOrder.add(u);
        visited.add(u);
        for(Edge neighbor: adjList.get(u)) {
            int v = neighbor.dst;
            if(! visited.contains(v))
                dfsUtil(v, visited, dfsOrder);
        }
    }

    public List bfs(int src) {
        List<Integer> bfsOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited.add(src);
        while(! queue.isEmpty()) {
            int curr = queue.poll();
            bfsOrder.add(curr);
            for(Edge neighbors: adjList.get(curr)) {
                if(! visited.contains(neighbors.dst)) {
                    visited.add(neighbors.dst);
                    queue.add(neighbors.dst);
                }
            }
        }
        return bfsOrder;
    }

    public boolean containsCycle() {
        return true;
    }

    public LinkedList<Integer> topologicalSort() {
        return null;
    }

    public HashMap<Integer, Integer> bellmanFord(int src) {
        /*
            for(var i = 1 => v-1)
                relaxAllEdges();
            for(Edge e: allEdges)
                if can_relax(u,v):
                    reportEdge(u, v);
         */
        HashMap<Integer, Integer> dist = initDist(src);

        for(int i = 1; i <= adjList.size() - 1; ++i) {
            for (Integer u : adjList.keySet()) {
                for (Edge neighborEdge : adjList.get(u)) {
                    int weight = neighborEdge.weight;
                    int v = neighborEdge.dst;
                    if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v))
                        dist.put(v, dist.get(u) + weight);
                }
            }
        }
        for (Integer u : adjList.keySet()) {
            for (Edge neighborEdge : adjList.get(u)) {
                int weight = neighborEdge.weight;
                int v = neighborEdge.dst;
                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v))
                    System.out.println(String.format("Negative cycle for edge u: %d, v: %d", u, v));
            }
        }
        return dist;
    }

    private HashMap<Integer,Integer> initDist(int src) {
        HashMap<Integer, Integer> dist = new HashMap<>();
        for(int u: adjList.keySet())
            dist.put(u, Integer.MAX_VALUE);
        dist.put(src, 0);
        return dist;
    }
}
