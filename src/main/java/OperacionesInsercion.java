public class OperacionesInsercion {
    private ArbolB arbol;

    public OperacionesInsercion(ArbolB arbol) {
        this.arbol = arbol;
    }

    public void insertar(int clave) {
        NodoB raiz = arbol.getRaiz();
        int t = arbol.getT();

        if (raiz == null) {
            raiz = new NodoB(t, true);
            raiz.claves[0] = clave;
            raiz.n = 1;
            arbol.setRaiz(raiz);
        } else {
            if (raiz.n == 2 * t - 1) {
                NodoB nuevaRaiz = new NodoB(t, false);
                nuevaRaiz.hijos[0] = raiz;
                dividirHijo(nuevaRaiz, 0, raiz);
                insertarNoLleno(nuevaRaiz, clave);
                arbol.setRaiz(nuevaRaiz);
            } else {
                insertarNoLleno(raiz, clave);
            }
        }
    }

    private void insertarNoLleno(NodoB nodo, int clave) {
        int i = nodo.n - 1;

        if (nodo.esHoja) {
            while (i >= 0 && clave < nodo.claves[i]) {
                nodo.claves[i + 1] = nodo.claves[i];
                i--;
            }
            nodo.claves[i + 1] = clave;
            nodo.n++;
        } else {
            while (i >= 0 && clave < nodo.claves[i]) i--;
            i++;
            if (nodo.hijos[i].n == 2 * arbol.getT() - 1) { // Cambiado t por arbol.getT()
                dividirHijo(nodo, i, nodo.hijos[i]);
                if (clave > nodo.claves[i]) i++;
            }
            insertarNoLleno(nodo.hijos[i], clave);
        }
    }

    private void dividirHijo(NodoB padre, int i, NodoB hijoLleno) {
        int t = arbol.getT(); // Obtenemos t desde ArbolB
        NodoB nuevo = new NodoB(t, hijoLleno.esHoja);
        nuevo.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            nuevo.claves[j] = hijoLleno.claves[j + t];
        }

        if (!hijoLleno.esHoja) {
            for (int j = 0; j < t; j++) {
                nuevo.hijos[j] = hijoLleno.hijos[j + t];
            }
        }

        hijoLleno.n = t - 1;

        for (int j = padre.n; j >= i + 1; j--) {
            padre.hijos[j + 1] = padre.hijos[j];
        }

        padre.hijos[i + 1] = nuevo;

        for (int j = padre.n - 1; j >= i; j--) {
            padre.claves[j + 1] = padre.claves[j];
        }

        padre.claves[i] = hijoLleno.claves[t - 1];
        padre.n++;
    }
}