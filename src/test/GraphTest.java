package test;

import graph.Graph;
import org.junit.Assert;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by kingfernandez on 10/22/17.
 */
public class GraphTest {

    /*

            (1)<---3---(2)
             |  \       ^ <
             |   \      |  \
             |    \     |   4
             6     3    1    \
             |      \   |    (5)
             |       \  |    /
             |        \ |   2
             >         >|  <
            (3)---1--> (4)
               <--2---

           1 3 4 2

    */

    Graph g;

    @org.junit.Before
    public void setGraph() {
        List<Integer> vertices = Arrays.asList(1, 2, 3, 4, 5);
        g = new Graph(vertices);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 1, 3);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 3, 2);
        g.addEdge(4, 2, 1);
        g.addEdge(5, 2, 4);
        g.addEdge(5, 4, 2);

    }

    @org.junit.Test
    public void addEdge() throws Exception {

    }

    @org.junit.Test
    public void addEdge1() throws Exception {

    }

    @org.junit.Test
    public void dfs() throws Exception {
        Object[] dfsOrder = g.dfs().toArray();
        Assert.assertArrayEquals(new Object[]{1,3,4,2,5}, dfsOrder);
    }

    @org.junit.Test
    public void bfs() throws Exception {
        Object[] order = g.bfs(1).toArray();
        System.out.println(Arrays.toString(order));
        Object[] expected = {1, 3, 4, 2};
        Assert.assertArrayEquals(expected, order);
    }

    @org.junit.Test
    public void containsCycle() throws Exception {

    }

    @org.junit.Test
    public void bellmanFord() throws Exception {
        HashMap<Integer, Integer> shortestPath = g.bellmanFord(1);
        Assert.assertEquals((long)0, (long)shortestPath.get(1));
        Assert.assertEquals((long)4, (long)shortestPath.get(2));
        Assert.assertEquals((long)5, (long)shortestPath.get(3));
        Assert.assertEquals((long)3, (long)shortestPath.get(4));
        Assert.assertEquals((long)Integer.MAX_VALUE, (long)shortestPath.get(5));


        Graph negCycleGraph = new Graph(Arrays.asList(1, 2, 3, 4));
        negCycleGraph.addEdge(1, 2, -10);
        negCycleGraph.addEdge(2, 3, -20);
        negCycleGraph.addEdge(3, 1, -30);
        negCycleGraph.addEdge(3, 4, 30);
        /* detects negative cycle */
        negCycleGraph.bellmanFord(1);

    }

}