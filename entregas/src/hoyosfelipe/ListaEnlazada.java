class ListaEnlazada {
    private Nodo cabeza;
    private Console console;

    public ListaEnlazada() {
        cabeza = null;
        console = new Console();
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            console.write(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        console.writeln("null");
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        int pasos = 0;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        cabeza = dummy.siguiente;
    }
}
