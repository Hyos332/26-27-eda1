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
        Cliente cliente = null;

        if (posicion >= 0 && posicion < tamano) {
            cliente = clientes[posicion];

            for (int i = posicion; i < tamano - 1; i++) {
                clientes[i] = clientes[i + 1];
            }

            clientes[tamano - 1] = null;
            tamano = tamano - 1;
        }

        return cliente;
    }

    public void insertarClienteEn(int posicion, Cliente cliente) {
        if (tamano < clientes.length) {
            if (posicion < 0) {
                posicion = 0;
            }

            if (posicion > tamano) {
                posicion = tamano;
            }

            for (int i = tamano; i > posicion; i--) {
                clientes[i] = clientes[i - 1];
            }

            clientes[posicion] = cliente;
            tamano = tamano + 1;
        }
    }

    public void anadirClientePreferente(Cliente cliente) {
        int posicion = 0;

        for (int i = 0; i < tamano; i++) {
            if (clientes[i].esPreferente()) {
                posicion = i + 1;
            }
        }

        insertarClienteEn(posicion, cliente);
    }

    public void anadirClienteColado(Cliente cliente) {
        int posicionConocido = 0;

        if (tamano > 0) {
            posicionConocido = (int) (Math.random() * tamano);
        }

        insertarClienteEn(posicionConocido + 1, cliente);
    }

    public int retirarClientesAburridos(int minutoActual, double probabilidadAburrirse) {
        int clientesRetirados = 0;
        int i = 0;

        while (i < tamano) {
            if (clientes[i].llevaMasDe(minutoActual, 8)
                && Math.random() <= probabilidadAburrirse) {

                quitarClienteEn(i);
                clientesRetirados = clientesRetirados + 1;
            } else {
                i = i + 1;
            }
        }

        return clientesRetirados;
    }

    public boolean entregarComprasAOtroCliente() {
        boolean entregaRealizada = false;

        if (tamano > 1) {
            int posicionEntrega = (int) (Math.random() * tamano);
            int posicionRecibe = (int) (Math.random() * tamano);

            while (posicionRecibe == posicionEntrega) {
                posicionRecibe = (int) (Math.random() * tamano);
            }

            clientes[posicionRecibe].recibirCompras();
            quitarClienteEn(posicionEntrega);
            entregaRealizada = true;
        }

        return entregaRealizada;
    }

    public boolean hayClientes() {
        return false;
    }

    public int obtenerCantidadPersonasEnCola() {
        return 0;
    }
}
