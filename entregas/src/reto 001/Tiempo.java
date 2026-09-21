public class Tiempo {
    private int minuto;

    public Tiempo() {
        minuto = 0;
    }

    public void avanzar() {
        minuto = minuto + 1;
    }

    public boolean haFinalizado() {
        final int DURACION = 120;

        return minuto >= DURACION;
    }

    public int minuto() {
        return minuto;
    }

    public boolean reglasExtendidasActivas() {
        final int MINUTO_INICIO_REGLAS_EXTENDIDAS = 20;

        return minuto >= MINUTO_INICIO_REGLAS_EXTENDIDAS;
    }

    public boolean esMomentoDeRevisarAburridos() {
        return this.reglasExtendidasActivas() && minuto % 5 == 0;
    }

    public boolean esMomentoDeParlante() {
        return minuto % 15 == 0;
    }
}
