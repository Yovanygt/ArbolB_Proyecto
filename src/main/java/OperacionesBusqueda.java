public class OperacionesBusqueda {
    private ArbolB arbol;

    public OperacionesBusqueda(ArbolB arbol) {
        this.arbol = arbol;
    }

    public NodoB buscar(int clave) {
        NodoB raiz = arbol.getRaiz();
        if (raiz == null) return null;
        return buscarEnNodo(raiz, clave);
    }

    private NodoB buscarEnNodo(NodoB nodo, int clave) {
        int i = 0;
        while (i < nodo.n && clave > nodo.claves[i]) i++;

        if (i < nodo.n && nodo.claves[i] == clave) return nodo;

        if (nodo.esHoja) return null;

        return buscarEnNodo(nodo.hijos[i], clave);
    }
}