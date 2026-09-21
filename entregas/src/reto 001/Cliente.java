public class Cliente {


public void añadirCliente(Cliente cliente) {
    clientes[tamaño] = cliente;
    tamaño = tamaño + 1;
}


public Cliente quitarCliente() {
    Cliente cliente = clientes[0];

    for (int i = 0; i < tamaño - 1; i++) {
        clientes[i] = clientes[i + 1];
    }

    clientes[tamaño - 1] = null;
    tamaño = tamaño - 1;

    return cliente;
}
}