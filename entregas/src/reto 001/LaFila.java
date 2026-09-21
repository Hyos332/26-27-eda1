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
    }
}
