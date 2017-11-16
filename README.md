# elvis-alexander.github.io



static class Edge {
        int weight;
        int v;
        Edge(int v) {
            this.v = v;
        }
    }

    static class Graph {
        LinkedList<Edge>[] adjList;
        Graph(int num_vertices) {
            adjList = new LinkedList[num_vertices];
            for(int i = 0; i < adjList.length; ++i)
                adjList[i] = new LinkedList<>();
        }
        void addEdge(int src, int dst) {


            adjList[src].add(new Edge(dst));
        }
        void bfs(int src) {
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            queue.add(src);
            visited.add(src);
            while (! queue.isEmpty()) {
                int curr = queue.poll();
                System.out.print(curr + "," + "\t");
                for(Edge neighbor: adjList[curr]) {
                    if(! visited.contains(neighbor.v)) {
                        visited.add(neighbor.v);
                        queue.add(neighbor.v);
                    }
                }
            }
        }

        void dfs() {
            HashSet<Integer> visitedSet = new HashSet<>();
            for(int vertex = 0; vertex < adjList.length; ++vertex) {
                if(! visitedSet.contains(vertex)) {
                    dfsUtil(vertex, visitedSet);
                }
            }
        }

        private void dfsUtil(int vertex, HashSet<Integer> visitedSet) {
            visitedSet.add(vertex);
            System.out.print(vertex + "\t");
            for(Edge neighbor: adjList[vertex]) {
                if(! visitedSet.contains(neighbor.v))
                    dfsUtil(neighbor.v, visitedSet);
            }
        }

        private boolean isCyclic() {
            HashSet<Integer> visitedSet = new HashSet<>();
            HashSet<Integer> recursiveStack = new HashSet<>();
            for(int vertex = 0; vertex < adjList.length; ++vertex) {
                if(! visitedSet.contains(vertex)) {
                    if(isCyclicUtil(vertex, visitedSet, recursiveStack))
                        return true;
                }
            }
            return false;
        }

        private boolean isCyclicUtil(int vertex, HashSet<Integer> visitedSet, HashSet<Integer> recursiveStack) {
            visitedSet.add(vertex);
            recursiveStack.add(vertex);
            for(Edge neighbor: adjList[vertex]) {
                if(recursiveStack.contains(neighbor.v))
                    return true;
                if((! visitedSet.contains(neighbor.v)) && isCyclicUtil(neighbor.v, visitedSet, recursiveStack))
                    return true;
            }
            recursiveStack.remove(vertex);
            return false;
        }

        void topSort() {
            HashSet<Integer> visitedSet = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            for(int vertex = 0; vertex < adjList.length; ++vertex) {
                if(! visitedSet.contains(vertex)) {
                    topSort(vertex, visitedSet, stack);
                }
            }
            while (! stack.isEmpty())
                System.out.printf("%d\t", stack.pop());
        }

        private void topSort(int vertex, HashSet<Integer> visitedSet, Stack<Integer> stack) {
            visitedSet.add(vertex);
            for(Edge neighbor: adjList[vertex]) {
                if(! visitedSet.contains(neighbor.v))
                    topSort(neighbor.v, visitedSet, stack);
            }
            stack.push(vertex);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
//        g.addEdge(2, 1);  creates cycle
        g.addEdge(2, 3);
        g.addEdge(4, 2);
        g.bfs(0);
        System.out.println();
        g.dfs();
        System.out.println();
        System.out.print(g.isCyclic());
        System.out.println();
        g.topSort();
    }
