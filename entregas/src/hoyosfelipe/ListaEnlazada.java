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

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        this.eliminarRepetidosTras(dummy);
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && this.empiezaRepeticion(cabeza)) {
            cabeza = this.siguienteDistinto(cabeza);
        }

        if (cabeza == null) {
            return;
        }

        this.eliminarRepetidosTras(cabeza);
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        assert a != null && b != null;

        Nodo dummy = new Nodo(-1);
        Nodo ultimo = dummy;
        while (!a.estaVacia() || !b.estaVacia()) {
            ultimo.siguiente = conMenorCabeza(a, b).sacar();
            ultimo = ultimo.siguiente;
        }

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;
        return resultado;
    }
}
