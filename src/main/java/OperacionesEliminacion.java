public class OperacionesEliminacion {
    private ArbolB arbol;

    public OperacionesEliminacion(ArbolB arbol) {
        this.arbol = arbol;
    }

    public void eliminar(int clave) {
        NodoB raiz = arbol.getRaiz();
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        eliminarEnNodo(raiz, clave);

        if (raiz.n == 0) {
            if (raiz.esHoja) {
                arbol.setRaiz(null);
            } else {
                arbol.setRaiz(raiz.hijos[0]);
            }
        }
    }

    private void eliminarEnNodo(NodoB nodo, int clave) {
        int idx = encontrarClave(nodo, clave);

        if (idx < nodo.n && nodo.claves[idx] == clave) {
            if (nodo.esHoja) {
                eliminarDeHoja(nodo, idx);
            } else {
                eliminarDeNoHoja(nodo, idx);
            }
        } else {
            if (nodo.esHoja) {
                System.out.println("La clave " + clave + " no está en el árbol");
                return;
            }

            boolean flag = (idx == nodo.n);

            if (nodo.hijos[idx].n < arbol.getT()) {
                llenar(nodo, idx);
            }

            if (flag && idx > nodo.n) {
                eliminarEnNodo(nodo.hijos[idx - 1], clave);
            } else {
                eliminarEnNodo(nodo.hijos[idx], clave);
            }
        }
    }

    private int encontrarClave(NodoB nodo, int clave) {
        int idx = 0;
        while (idx < nodo.n && nodo.claves[idx] < clave) {
            idx++;
        }
        return idx;
    }

    private void eliminarDeHoja(NodoB nodo, int idx) {
        for (int i = idx + 1; i < nodo.n; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }
        nodo.n--;
    }

    private void eliminarDeNoHoja(NodoB nodo, int idx) {
        int clave = nodo.claves[idx];

        if (nodo.hijos[idx].n >= arbol.getT()) {
            int pred = obtenerPredecesor(nodo, idx);
            nodo.claves[idx] = pred;
            eliminarEnNodo(nodo.hijos[idx], pred);
        } else if (nodo.hijos[idx + 1].n >= arbol.getT()) {
            int suc = obtenerSucesor(nodo, idx);
            nodo.claves[idx] = suc;
            eliminarEnNodo(nodo.hijos[idx + 1], suc);
        } else {
            fusionar(nodo, idx);
            eliminarEnNodo(nodo.hijos[idx], clave);
        }
    }

    private int obtenerPredecesor(NodoB nodo, int idx) {
        NodoB actual = nodo.hijos[idx];
        while (!actual.esHoja) {
            actual = actual.hijos[actual.n];
        }
        return actual.claves[actual.n - 1];
    }

    private int obtenerSucesor(NodoB nodo, int idx) {
        NodoB actual = nodo.hijos[idx + 1];
        while (!actual.esHoja) {
            actual = actual.hijos[0];
        }
        return actual.claves[0];
    }

    private void fusionar(NodoB nodo, int idx) {
        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[arbol.getT() - 1] = nodo.claves[idx];

        for (int i = 0; i < hermano.n; i++) {
            hijo.claves[i + arbol.getT()] = hermano.claves[i];
        }

        if (!hijo.esHoja) {
            for (int i = 0; i <= hermano.n; i++) {
                hijo.hijos[i + arbol.getT()] = hermano.hijos[i];
            }
        }

        hijo.n += hermano.n + 1;

        for (int i = idx + 1; i < nodo.n; i++) {
            nodo.claves[i - 1] = nodo.claves[i];
        }

        for (int i = idx + 2; i <= nodo.n; i++) {
            nodo.hijos[i - 1] = nodo.hijos[i];
        }

        nodo.n--;
    }

    private void llenar(NodoB nodo, int idx) {
        if (idx != 0 && nodo.hijos[idx - 1].n >= arbol.getT()) {
            prestarDeAnterior(nodo, idx);
        } else if (idx != nodo.n && nodo.hijos[idx + 1].n >= arbol.getT()) {
            prestarDeSiguiente(nodo, idx);
        } else {
            if (idx != nodo.n) {
                fusionar(nodo, idx);
            } else {
                fusionar(nodo, idx - 1);
            }
        }
    }

    private void prestarDeAnterior(NodoB nodo, int idx) {
        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx - 1];

        for (int i = hijo.n - 1; i >= 0; i--) {
            hijo.claves[i + 1] = hijo.claves[i];
        }

        if (!hijo.esHoja) {
            for (int i = hijo.n; i >= 0; i--) {
                hijo.hijos[i + 1] = hijo.hijos[i];
            }
        }

        hijo.claves[0] = nodo.claves[idx - 1];

        if (!hijo.esHoja) {
            hijo.hijos[0] = hermano.hijos[hermano.n];
        }

        nodo.claves[idx - 1] = hermano.claves[hermano.n - 1];

        hijo.n++;
        hermano.n--;
    }

    private void prestarDeSiguiente(NodoB nodo, int idx) {
        NodoB hijo = nodo.hijos[idx];
        NodoB hermano = nodo.hijos[idx + 1];

        hijo.claves[hijo.n] = nodo.claves[idx];

        if (!hijo.esHoja) {
            hijo.hijos[hijo.n + 1] = hermano.hijos[0];
        }

        nodo.claves[idx] = hermano.claves[0];

        for (int i = 1; i < hermano.n; i++) {
            hermano.claves[i - 1] = hermano.claves[i];
        }

        if (!hermano.esHoja) {
            for (int i = 1; i <= hermano.n; i++) {
                hermano.hijos[i - 1] = hermano.hijos[i];
            }
        }

        hijo.n++;
        hermano.n--;
    }
}