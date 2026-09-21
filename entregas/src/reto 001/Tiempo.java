public class Tiempo {
    private int minutoActual;
    private int duracion;

    public Tiempo(int duracion) {
        minutoActual = 0;
        this.duracion = duracion;
    }

    public void avanzar() {
        minutoActual = minutoActual + 1;
    }

    public boolean haFinalizado() {
        return minutoActual >= duracion;
    }

    public int obtenerMinutoActual() {
        return minutoActual;
    }

    public boolean reglasExtendidasActivas() {
        return minutoActual >= 20;
    }

    public boolean esMomentoDeRevisarAburridos() {
        return reglasExtendidasActivas() && minutoActual % 5 == 0;
    }

    public boolean esMomentoDeParlante() {
        return minutoActual % 15 == 0;
    }
}
