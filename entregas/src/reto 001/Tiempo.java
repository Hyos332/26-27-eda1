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
        return false;
    }

    public boolean esMomentoDeRevisarAburridos() {
        return false;
    }

    public boolean esMomentoDeParlante() {
        return false;
    }
}
