public class NodoB {
    int[] claves;         // Claves almacenadas en el nodo
    int t;                // Grado del árbol
    NodoB[] hijos;        // Referencias a hijos
    int n;                // Número actual de claves
    boolean esHoja;       // Si el nodo es hoja o no

    // Constructor del nodo
    public NodoB(int t, boolean esHoja) {
        this.t = t;
        this.esHoja = esHoja;
        this.claves = new int[2 * t - 1];
        this.hijos = new NodoB[2 * t];
        this.n = 0;
    }
}
