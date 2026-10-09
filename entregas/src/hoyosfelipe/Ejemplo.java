public class Ejemplo {
    private Console console;

    public Ejemplo() {
        console = new Console();
    }

    public static void main(String[] args) {
        new Ejemplo().ejecutar();
    }

    public void ejecutar() {
        console.writeln("== Eliminar repetidos");
        this.probarEliminarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        this.probarEliminarRepetidos(new int[] { 1, 1, 1 });
        this.probarEliminarRepetidos(new int[] { 1, 2, 2 });
        this.probarEliminarRepetidos(new int[] { 1, 2, 3 });
        this.probarEliminarRepetidos(new int[] { 5, 5, 6, 6 });
        this.probarEliminarRepetidos(new int[] {});

        console.writeln("== Fusionar");
        this.probarFusionar(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 });
        this.probarFusionar(new int[] {}, new int[] { 2, 3 });
        this.probarFusionar(new int[] {}, new int[] {});
        this.probarFusionar(new int[] { 1, 1 }, new int[] { 1 });
    }
}
