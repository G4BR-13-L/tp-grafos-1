
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.graph.GFG;
import src.graph.Graph;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    int vertices = 0;
    int arestas = 0;
    int k = 0;
    Graph graph;
    int[] maxDistancesList = new int[40];

    for (int instance = 0; instance < maxDistancesList.length; instance++) {
      File file = new File("./ORLib/pmed"+(instance+1)+".txt");
      Scanner sc = new Scanner(file);
      vertices = sc.nextInt();
      arestas = sc.nextInt();
      k = sc.nextInt();
      //System.out.println("Instancia: pmed"+(instance+1)+"| " + vertices + " | " + arestas + " | " + k + " |");
      graph = new Graph(vertices, k);
      
      
      while (sc.hasNext()) {
        int i = sc.nextInt();
        int j = sc.nextInt();
        int weight = sc.nextInt();
        graph.matrix[i- 1][j- 1] = weight;
      }

      // close scanner
      sc.close();

      // System.out.println("Grafo instanciado: \n\n"+graph.toString());
      graph.correctInfiniteWays();
      // System.out.println("Grafo de Distancias corrigidas: \n\n"+graph.toString());
      graph.floydWharshall();
      //graph.selectCenters();
      graph.selectCenters();;
      maxDistancesList[instance] = graph.maxDistance;
     //System.out.println("Grafo: \n\n"+graph.toString());

    }

    for ( int print = 0 ; print < maxDistancesList.length ; print++ ){
      System.out.println("pmed"+(print+1)+": "+maxDistancesList[print]);
    }
  }

}
