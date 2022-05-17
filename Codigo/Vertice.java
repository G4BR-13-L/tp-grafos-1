class Vertice{
    public int id;
    
}

class Aresta{
    public int custo;
    public Vertice saida;
    public Vertice destino;

}

class Grafo{
    public Vertice[] vertices;
    public Aresta[] arestas;


    Grafo(int vertices){
        this.vertices = new Vertice[vertices];
    }
}


