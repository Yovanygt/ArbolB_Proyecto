public class ArbolB {
    NodoB raiz;
    int t;
    OperacionesInsercion insercion;
    OperacionesEliminacion eliminacion;
    OperacionesBusqueda busqueda;

    public ArbolB(int t) {
        this.raiz = null;
        this.t = t;
        this.insercion = new OperacionesInsercion(this);
        this.eliminacion = new OperacionesEliminacion(this);
        this.busqueda = new OperacionesBusqueda(this);
    }

    public void insertar(int clave) {
        insercion.insertar(clave);
    }

    public void eliminar(int clave) {
        eliminacion.eliminar(clave);
    }

    public NodoB buscar(int clave) {
        return busqueda.buscar(clave);
    }

    // Método getRaiz() correctamente implementado
    public NodoB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoB raiz) {
        this.raiz = raiz;
    }

    public int getT() {
        return t;
    }
}