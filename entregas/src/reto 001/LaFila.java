public class LaFila {
    private Cola cola;
    private Tiempo tiempo;
    private int personasAtendidas;
    private Console console;
    private int personasDesistieron;
    private int personasAburridas;
    private int personasQueEntregaronCompras;
    private int numeroCliente;

    public LaFila() {
        cola = new Cola();
        tiempo = new Tiempo();
        personasAtendidas = 0;
        console = new Console();
        personasDesistieron = 0;
        personasAburridas = 0;
        personasQueEntregaronCompras = 0;
        numeroCliente = 1;
    }

    public static void main(String[] args) {
        LaFila simulacion = new LaFila();
        simulacion.simular();
    }

    public void simular() {
        console.writeln("Simulacion laFila");
        console.writeln("-----------------");

        while (!tiempo.haFinalizado()) {
            this.procesarLlegadaCliente();
            this.procesarAtencion();

            if (tiempo.reglasExtendidasActivas()) {
                this.procesarReglasNuevas();
            }

            this.procesarParlante();
            this.mostrarEstado();
            tiempo.avanzar();
        }

        this.mostrarResumen();
    }

    private void procesarLlegadaCliente() {
        if (this.llegaCliente()) {
            Cliente cliente = crearCliente(false);
            this.anadirClienteSiEntra(cliente);
        }
    }

    private boolean llegaCliente() {
        final double PROBABILIDAD_LLEGADA = 0.6;

        return Math.random() <= PROBABILIDAD_LLEGADA;
    }

    private void procesarAtencion() {
        if (this.hayCajaLibre() && cola.hayClientes()) {
            cola.quitarCliente();
            personasAtendidas = personasAtendidas + 1;
        }
    }

    private boolean hayCajaLibre() {
        final double PROBABILIDAD_CAJA_LIBRE = 0.4;

        return Math.random() <= PROBABILIDAD_CAJA_LIBRE;
    }

    private void procesarReglasNuevas() {
        this.procesarAburridos();
        this.procesarPreferente();
        this.procesarColado();
        this.procesarEntregaCompras();
    }

    private void procesarAburridos() {
        final double PROBABILIDAD_ABURRIRSE = 0.3;
        int retirados = 0;

        if (tiempo.esMomentoDeRevisarAburridos()) {
            retirados = cola.retirarClientesAburridos(
                tiempo.obtenerMinutoActual(),
                PROBABILIDAD_ABURRIRSE
            );
        }

        personasAburridas = personasAburridas + retirados;
    }

    private void procesarPreferente() {
        if (this.llegaClientePreferente()) {
            Cliente cliente = crearCliente(true);
            this.anadirPreferenteSiEntra(cliente);
        }
    }

    private boolean llegaClientePreferente() {
        final double PROBABILIDAD_PREFERENTE = 0.1;

        return Math.random() <= PROBABILIDAD_PREFERENTE;
    }

    private void procesarColado() {
        if (cola.hayClientes() && this.hayColado()) {
            Cliente cliente = crearCliente(false);
            this.anadirColadoSiEntra(cliente);
        }
    }

    private boolean hayColado() {
        final double PROBABILIDAD_COLADO = 0.1;

        return Math.random() <= PROBABILIDAD_COLADO;
    }

    private void procesarEntregaCompras() {
        if (this.alguienEntregaCompras()) {
            if (cola.entregarComprasAOtroCliente()) {
                personasQueEntregaronCompras = personasQueEntregaronCompras + 1;
            }
        }
    }

    private boolean alguienEntregaCompras() {
        final double PROBABILIDAD_ENTREGA_COMPRAS = 0.05;

        return Math.random() <= PROBABILIDAD_ENTREGA_COMPRAS;
    }

    private Cliente crearCliente(boolean preferente) {
        Cliente cliente = new Cliente(
            numeroCliente,
            tiempo.minuto(),
            preferente
        );

        numeroCliente = numeroCliente + 1;

        return cliente;
    }

    private void anadirClienteSiEntra(Cliente cliente) {
        if (this.clienteDesiste()) {
            personasDesistieron = personasDesistieron + 1;
        } else {
            cola.anadirCliente(cliente);
        }
    }

    private void anadirPreferenteSiEntra(Cliente cliente) {
        if (this.clienteDesiste()) {
            personasDesistieron = personasDesistieron + 1;
        } else {
            cola.anadirClientePreferente(cliente);
        }
    }

    private void anadirColadoSiEntra(Cliente cliente) {
        if (this.clienteDesiste()) {
            personasDesistieron = personasDesistieron + 1;
        } else {
            cola.anadirClienteColado(cliente);
        }
    }

    private boolean clienteDesiste() {
        final double PROBABILIDAD_DESISTIR = 0.5;
        boolean desiste = false;

        if (this.filaMuyLarga()) {
            desiste = Math.random() <= PROBABILIDAD_DESISTIR;
        }

        return desiste;
    }

    private boolean filaMuyLarga() {
        final int TAMANO_FILA_LARGA = 30;

        return cola.obtenerCantidadClientes() >= TAMANO_FILA_LARGA;
    }

    private void procesarParlante() {
        if (tiempo.esMomentoDeParlante() && this.filaParaAviso()) {

            console.writeln("Minuto " + tiempo.minuto()
                + ": pasen por esta caja en orden de fila");
        }
    }

    private boolean filaParaAviso() {
        final int TAMANO_AVISO_PARLANTE = 25;

        return cola.obtenerCantidadClientes() > TAMANO_AVISO_PARLANTE;
    }

    private void mostrarEstado() {
        int longitudFila = cola.obtenerCantidadClientes();

        console.writeln("Minuto " + tiempo.minuto()
            + " | fila: " + longitudFila
            + " personas | longitud: " + longitudFila + " metros");
    }

    private void mostrarResumen() {
        console.writeln();
        console.writeln("Resumen");
        console.writeln("-------");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila al cierre: "
            + cola.obtenerCantidadClientes());
        console.writeln("Personas que desistieron: " + personasDesistieron);
        console.writeln("Personas aburridas que se fueron: " + personasAburridas);
        console.writeln("Personas que entregaron sus compras: "
            + personasQueEntregaronCompras);
    }
}
