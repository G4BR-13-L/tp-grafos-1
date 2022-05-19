
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import src.graph.Graph;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    int vertices = 0;
    int arestas = 0;
    int k = 0;
    Graph graph;

    // create a new file object
    File file = new File("./ORLib/pmedteste.txt");
    // create an object of Scanner
    // associated with the file
    Scanner sc = new Scanner(file);
    vertices = sc.nextInt();
    arestas = sc.nextInt();
    k = sc.nextInt();
    System.out.println(" | " + vertices + " | " + arestas + " | " + k + " | ");

    graph = new Graph(vertices);

    while (sc.hasNext()) {
      int i = sc.nextInt();
      int j = sc.nextInt();
      int weight = sc.nextInt();
      graph.matrix[i-1][j-1] = weight;
    }

    // close scanner
    sc.close();

    System.out.println("Grafo instanciado: \n\n"+graph.toString());
    graph.correctInfiniteWays();
   System.out.println("Grafo de Distancias corrigidas: \n\n"+graph.toString());
    graph.floydWharshall();
    System.out.println("Grafo Depois de floyd Wharshall: \n\n"+graph.toString());

    System.out.println("\n\n");
    //graph.findMinimumKCenterDisntaces();

  }
}
