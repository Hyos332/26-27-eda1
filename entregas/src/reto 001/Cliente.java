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
        return preferente;
    }

    public boolean llevaMasDe(int minutoActual, int minutos) {
        return minutoActual - minutoLlegada > minutos;
    }

    public void recibirCompras() {
        comprasRecibidas = comprasRecibidas + 1;
    }

    public int obtenerComprasRecibidas() {
        return comprasRecibidas;
    }
}
