package src.graph;

public class Graph {
    public int[][] matrix;
    public int n_vertices;

    public Graph(int n_vertices) {
        this.matrix = new int[n_vertices][n_vertices];
        this.n_vertices = n_vertices;
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

    public void findMinimumKCenterDisntaces(){
        int[] k1Group = new int[n_vertices];
        int[] k2Group = new int[n_vertices];
        int actualK1 = 0;
        int actualK2 = 0;

        for ( int zero = 0 ; zero < n_vertices ; zero++ ){
            k1Group[zero] = 0;
            k2Group[zero] = 0;
        }

        for( int k1 = 0 ; k1 < n_vertices ; k1++ ){
            for( int k2 = 0 ; k2 < n_vertices ; k2++ ){
                if( !(k1 == k2 )){

                    for( int o = 0 ; o < n_vertices ; o++ ){
                        if( this.matrix[o][k2] < this.matrix[o][k1] ){
                            k2Group[o] = this.matrix[o][k2];
                        }else{
                            k1Group[o] = this.matrix[o][k1];
                        }
                    }
                }
                actualK2 = k2;
            }
            actualK1 = k1;
        }
        for ( int print = 0 ; print < n_vertices ; print++ ){
            System.out.print(" | "+k1Group[print]);
            
        }
        System.out.print("\n");
        for ( int print = 0 ; print < n_vertices ; print++ ){
            System.out.print(" | "+k2Group[print]);
            
        }
        System.out.print("\n");
        System.out.println("K1 e K2"+actualK1+" - "+actualK2);
        int maximum_lightning = 0;
        for( int y = 0 ; y < n_vertices ; y++){
            if(k1Group[y] > k2Group[y]){
                maximum_lightning = k1Group[y];
            }else if(k2Group[y] > 0){
                maximum_lightning = k2Group[y];
            }
        }
        System.out.println("Resultado pmed1: "+maximum_lightning);
    }
}
