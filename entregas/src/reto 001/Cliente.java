public class Cliente {
    private int numero;
    private int minutoLlegada;
    private boolean preferente;
    private int comprasRecibidas;

    public Cliente(int numero, int minutoLlegada, boolean preferente) {
        this.numero = numero;
        this.minutoLlegada = minutoLlegada;
        this.preferente = preferente;
        comprasRecibidas = 0;
    }

    public int obtenerNumero() {
        return numero;
    }

    public int obtenerMinutoLlegada() {
        return minutoLlegada;
    }

    public boolean esPreferente() {
        return false;
    }

    public boolean llevaMasDe(int minutoActual, int minutos) {
        return false;
    }

    public void recibirCompras() {
    }

    public int obtenerComprasRecibidas() {
        return 0;
    }
}
