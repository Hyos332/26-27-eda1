public class Cola {
    private final int CAPACIDAD_MAXIMA = 200;
    private Cliente[] clientes;
    private int tamano;

    public Cola() {
        clientes = new Cliente[CAPACIDAD_MAXIMA];
        tamano = 0;
    }

    public void anadirCliente(Cliente cliente) {
        if (tamano < clientes.length) {
            clientes[tamano] = cliente;
            tamano = tamano + 1;
        }
    }

    public Cliente quitarCliente() {
        Cliente cliente = null;

        if (tamano > 0) {
            cliente = clientes[0];

            for (int i = 0; i < tamano - 1; i++) {
                clientes[i] = clientes[i + 1];
            }

            clientes[tamano - 1] = null;
            tamano = tamano - 1;
        }

        return cliente;
    }

    public Cliente quitarClienteEn(int posicion) {
        return null;
    }

    public void insertarClienteEn(int posicion, Cliente cliente) {
    }

    public void anadirClientePreferente(Cliente cliente) {
    }

    public void anadirClienteColado(Cliente cliente) {
    }

    public int retirarClientesAburridos(int minutoActual, double probabilidadAburrirse) {
        return 0;
    }

    public boolean entregarComprasAOtroCliente() {
        return false;
    }

    public boolean hayClientes() {
        return false;
    }

    public int obtenerCantidadPersonasEnCola() {
        return 0;
    }
}
