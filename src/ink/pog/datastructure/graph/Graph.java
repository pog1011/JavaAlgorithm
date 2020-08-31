package ink.pog.datastructure.graph;

import java.util.*;

public class Graph {

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numEdges;
    private boolean[] visited;
    private int[] tree;
    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        visited = new boolean[n];
        tree = new int[n];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public int getNumEdges() {
        return numEdges;
    }

    public String getVertex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void edgesList() {
        System.out.print("  ");
        for (String s : vertexList) {
            System.out.print(s + " ");
        }
        System.out.println();
        int index = 0;
        for (int[] edge : edges) {
            System.out.print(vertexList.get(index++) + " ");
            for (int i : edge) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numEdges++;
    }

    public int getNext(int v1, int v2) {
        for (int i = (v2 == -1 ? 0 : v2 + 1); i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void bfs(boolean[] visited, int i) {
        System.out.print(vertexList.get(i) + " => ");
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(i);
        visited[i] = true;
        int w, u;
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getNext(u, -1);
            while (w != -1) {
                if (!visited[w]) {
                    System.out.print(vertexList.get(w) + " => ");
                    tree[w] = u;
                    queue.addLast(w);
                    visited[w] = true;
                }
                w = getNext(u, w);
            }
        }
    }

    public void bfs() {
        tree[0] = -1;
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) {
                bfs(visited, i);
            }
        }
    }

    public void dfs(boolean[] visited, int i) {
        System.out.print(getVertex(i) + " => ");
        visited[i] = true;
        int w = getNext(i, -1);

        while (w != -1) {
            if (!visited[w]) {
                dfs(visited, w);
            }
            w = getNext(i, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) {
                stackDfs(i);
            }
        }
    }

    public void treelist(){
        for(int i = 0 ; i < tree.length; i ++){
            System.out.print(tree[i] + "  ");
        }
    }

    public void stackDfs(int vertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);
        visited[vertex] = true;
        System.out.println(vertexList.get(vertex));
        while (!stack.isEmpty()) {
            int v1 = stack.pop();
            vertex = getNext(v1,-1);
            while (vertex != -1) {
                if (!visited[vertex]) {
                    System.out.println(vertexList.get(vertex));
                    visited[vertex] = true;
                    stack.push(vertex);
                }
                vertex = getNext(v1, vertex);
            }
        }
    }


    public static void main(String[] args) {

        String[] s = {"A", "B", "C", "D", "E", "F", "G", "H"};
        Graph graph = new Graph(s.length);
        for (String s1 : s) {
            graph.insertVertex(s1);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.edgesList();
        graph.bfs();
        graph.treelist();
//        graph.dfs();
    }


}
