public class LaFila {
    private final int DURACION_SIMULACION = 120;
    private final int MINUTO_INICIO_REGLAS_EXTENDIDAS = 20;
    private final int TAMANO_FILA_LARGA = 30;
    private final int TAMANO_AVISO_PARLANTE = 25;

    private final double PROBABILIDAD_LLEGADA = 0.6;
    private final double PROBABILIDAD_CAJA_LIBRE = 0.4;
    private final double PROBABILIDAD_ABURRIRSE = 0.3;
    private final double PROBABILIDAD_DESISTIR = 0.5;
    private final double PROBABILIDAD_PREFERENTE = 0.1;
    private final double PROBABILIDAD_COLADO = 0.1;
    private final double PROBABILIDAD_ENTREGA_COMPRAS = 0.05;

    private Cola cola;
    private Tiempo tiempo;
    private Console console;
    private int personasAtendidas;
    private int personasDesistieron;
    private int personasAburridas;
    private int personasQueEntregaronCompras;
    private int siguienteNumeroCliente;

    public LaFila() {
        cola = new Cola();
        tiempo = new Tiempo(DURACION_SIMULACION);
        console = new Console();
        personasAtendidas = 0;
        personasDesistieron = 0;
        personasAburridas = 0;
        personasQueEntregaronCompras = 0;
        siguienteNumeroCliente = 1;
    }

    public static void main(String[] args) {
        LaFila simulacion = new LaFila();
        simulacion.simular();
    }

    public void simular() {
        console.writeln("Simulacion laFila");
        console.writeln("-----------------");

        while (!tiempo.haFinalizado()) {
            tiempo.avanzar();
            simularMinuto();
        }

        mostrarResumen();
    }

    private void simularMinuto() {
        procesarLlegadaNormal();
        procesarAtencion();

        if (tiempo.obtenerMinutoActual() >= MINUTO_INICIO_REGLAS_EXTENDIDAS) {
            procesarReglasExtendidas();
        }

        mostrarEstado();
    }

    private void procesarLlegadaNormal() {
        if (Math.random() <= PROBABILIDAD_LLEGADA) {
            Cliente cliente = crearCliente(false);
            intentarAnadirClienteNormal(cliente);
        }
    }

    private void procesarAtencion() {
        if (cola.hayClientes() && Math.random() <= PROBABILIDAD_CAJA_LIBRE) {
            cola.quitarCliente();
            personasAtendidas = personasAtendidas + 1;
        }
    }

    private void procesarReglasExtendidas() {
        procesarAburridos();
        procesarLlegadaPreferente();
        procesarColado();
    }

    private void procesarAburridos() {
        int retirados = 0;

        if (tiempo.esMomentoDeRevisarAburridos()) {
            retirados = cola.retirarClientesAburridos(
                tiempo.obtenerMinutoActual(),
                PROBABILIDAD_ABURRIRSE
            );
        }

        personasAburridas = personasAburridas + retirados;
    }

    private void procesarLlegadaPreferente() {
        if (Math.random() <= PROBABILIDAD_PREFERENTE) {
            Cliente cliente = crearCliente(true);
            intentarAnadirClientePreferente(cliente);
        }
    }

    private void procesarColado() {
        if (cola.hayClientes() && Math.random() <= PROBABILIDAD_COLADO) {
            Cliente cliente = crearCliente(false);
            intentarAnadirClienteColado(cliente);
        }
    }

    private Cliente crearCliente(boolean preferente) {
        Cliente cliente = new Cliente(
            siguienteNumeroCliente,
            tiempo.obtenerMinutoActual(),
            preferente
        );

        siguienteNumeroCliente = siguienteNumeroCliente + 1;

        return cliente;
    }

    private void intentarAnadirClienteNormal(Cliente cliente) {
        cola.anadirCliente(cliente);
    }

    private void intentarAnadirClientePreferente(Cliente cliente) {
        cola.anadirClientePreferente(cliente);
    }

    private void intentarAnadirClienteColado(Cliente cliente) {
        cola.anadirClienteColado(cliente);
    }

    private void mostrarEstado() {
        int longitudFila = cola.obtenerCantidadPersonasEnCola();

        console.writeln("Minuto " + tiempo.obtenerMinutoActual()
            + " | fila: " + longitudFila
            + " personas | longitud: " + longitudFila + " metros");
    }

    private void mostrarResumen() {
        console.writeln();
        console.writeln("Resumen");
        console.writeln("-------");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila al cierre: "
            + cola.obtenerCantidadPersonasEnCola());
    }
}
