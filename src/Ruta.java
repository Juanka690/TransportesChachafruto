public class Ruta {

    String nombreRuta;
    float distancia;
    //[0]1era, [1]2da y [2]3era
    final float[] CONSUMO_ADICIONAL_CAMBIO = {0.03f, 0.02f, 0.01f};

    public Ruta(String nombreRuta, float distancia) {
        this.nombreRuta = nombreRuta;
        this.distancia = distancia;
    }

    // Calculo del consumo adicional de gasolina por cambio (devuelve galones)
    public float calcularConsumoCambios(float[] cambioKm, float consumoPorKm) {
        float consumoAdicional = 0;
        for (int i = 0; i < cambioKm.length; i++) {
            consumoAdicional += cambioKm[i] * consumoPorKm * (1 + CONSUMO_ADICIONAL_CAMBIO[i]);
        }
        return consumoAdicional;
    }

    // Creacion de las rutas
    public static Ruta crearRutaAutopista() {
        return new Ruta("Autopista", 45);
    }

    public static Ruta crearRutaSantaElena() {
        return new Ruta("Santa Elena", 50);
    }

    public static Ruta crearRutaLasPalmas() {
        return new Ruta("Las Palmas", 55);
    }

}
