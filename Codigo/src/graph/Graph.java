package src.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public int[][] matrix;
    public int n_vertices;
    public int k_centers;
    List<Integer> cities;
    List<Integer> centerCities;

    public Graph(int n_vertices, int k_centers) {
        this.matrix = new int[n_vertices][n_vertices];
        this.n_vertices = n_vertices;
        this.k_centers = k_centers;
        centerCities = new ArrayList<>();
        cities = new ArrayList <>();
    }

    public void correctInfiniteWays() {
        for (int i = 0; i < n_vertices; i++) {
            for (int j = 0; j < n_vertices; j++) {
                if (i != j && this.matrix[i][j] == 0) {
                    this.matrix[i][j] = 9999;
                }
            }
        }
    }

    @Override
    public String toString() {
        String graphStrMatrix = "";
        for (int i = 0; i < n_vertices; i++) {
            graphStrMatrix += i + " ";
            for (int j = 0; j < n_vertices; j++) {
                graphStrMatrix += String.format("| %-6d ", this.matrix[i][j]);
            }
            graphStrMatrix += "\n";
        }
        return graphStrMatrix;
    }

    public String getCentersList(){
        String str = "";
        for ( int i = 0 ; i < centerCities.size() ; i++ ){
            str += centerCities.get(i)+"\n";
        }
        return str;
    }

    public void printCentersList(){
       
        for ( int i = 0 ; i < centerCities.size() ; i++ ){
            System.out.println(centerCities.get(i));
        }
        
    }

    public void floydWharshall() {
        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    if (this.matrix[i][j] > (this.matrix[i][k] + this.matrix[k][j])) {
                        this.matrix[i][j] = this.matrix[i][k] + this.matrix[k][j];
                    }
                }
            }
        }
    }

    public List<Integer> findCities() {
        int radius = 1;
        int[] dummy = new int[(this.matrix.length * this.matrix.length) + 1];
        int ij = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                dummy[ij] = this.matrix[i][j];
                ij++;
            }
        }
        Arrays.sort(dummy);
        radius = dummy[dummy.length / 2];
        return kCenter(radius);
    }


    public List<Integer> getCities(int n) {
        List<Integer> cityList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cityList.add(i);
        }
        return cityList;
    }

    public void removeAllCitiesAt2rRadius(List<Integer> cities, int radius, int city) {
        for (int i = 0; i < this.matrix.length; i++) {
            if (i < cities.size() && this.matrix[city][i] <= 2 * radius)
                cities.remove(cities.get(i));
        }
        int i = 0;
        while (!cities.isEmpty()) {
            centerCities.add(cities.remove(i));
            i++;
        }
    }

    public List<Integer> kCenter(int radius) {
        List<Integer> cities = getCities(this.matrix.length);
        int i = 0;
        this.printCentersList();
        while (cities.isEmpty()) {
            int city = cities.get(i);
            centerCities.add(city);
            removeAllCitiesAt2rRadius(cities, radius, city);
            i++;
        }
        if (centerCities.size() <= k_centers)
            return centerCities;
        return kCenter(radius + 1);
    }

    
    /*
     * static int maxindex(int[] distances, int n) {
     * int mi = 0;
     * for (int i = 0; i < n; i++) {
     * if (distances[i] > distances[mi])
     * mi = i;
     * }
     * return mi;
     * }
     */

    public void findMinimumKCenterDisntaces() {
        /*
         * int[] distances = new int[n_vertices];
         * ArrayList<Integer> centers = new ArrayList<>();
         * int max = 0;
         * for (int i = 0; i < k_centers; i++) {
         * centers.add(max);
         * for (int j = 0; j < n_vertices; j++) {
         * distances[j] = Math.min(distances[j], this.matrix[max][j]);
         * }
         * max = maxindex(distances, n_vertices);
         * 
         * }
         * //System.out.println("Resultado:"+distances[max]);
         * for (int i = 0; i < centers.size(); i++) {
         * System.out.print(centers.get(i) + " ");
         * }
         * System.out.print("\n");
         */

        /*
         * int minDistance = 0;
         * int[] minimum_lightnings = new int[k_centers];
         * for (int k = 0; k < k_centers; k++) {
         * int minimum_n_lightning = Integer.MAX_VALUE;
         * for (int i = 0; i < n_vertices; i++) {
         * for (int j = 0; j < n_vertices; j++) {
         * if( i != j ){
         * if (this.matrix[i][j] < minimum_n_lightning) {
         * minimum_n_lightning = this.matrix[i][j];
         * }
         * }
         * }
         * }
         * minimum_lightnings[k] = minimum_n_lightning;
         * }
         * for ( int h = 0 ; h < k_centers ; h++ ){
         * System.out.print(minimum_lightnings[h]+" | ");
         * }
         * System.out.println("0000");
         */

    }

}
