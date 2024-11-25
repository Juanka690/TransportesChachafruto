public class Ruta {

    // Nombre de la ruta (por ejemplo, "Autopista", "Santa Elena", "Las Palmas")
    String nombreRuta;

    // Distancia total de la ruta en kilómetros
    float distancia;

    // Consumo adicional de gasolina por cada marcha: [0] primera, [1] segunda, [2] tercera (Esta es una constante)
    final float[] CONSUMO_ADICIONAL_CAMBIO = {0.03f, 0.02f, 0.01f};

    /**
     * Constructor que inicializa una ruta con un nombre y una distancia específica.
     * @param nombreRuta nombre de la ruta.
     * @param distancia distancia total de la ruta en kilómetros.
     */
    public Ruta(String nombreRuta, float distancia) {
        this.nombreRuta = nombreRuta;
        this.distancia = distancia;
    }

    /**
     * Calcula el consumo adicional de gasolina debido al uso de diferentes marchas en la ruta.
     * @param cambioKm array con los kilómetros recorridos en cada marcha: [0] primera, [1] segunda, [2] tercera.
     * @param consumoPorKm consumo base de gasolina por kilómetro en condiciones normales.
     * @return el consumo total adicional de gasolina debido al uso de marchas, en galones.
     */
    public float calcularConsumoCambios(float[] cambioKm, float consumoPorKm) {
        float consumoAdicional = 0;
        // Itera sobre cada marcha y calcula el consumo adicional correspondiente
        for (int i = 0; i < cambioKm.length; i++) {
            consumoAdicional += cambioKm[i] * consumoPorKm * (1 + CONSUMO_ADICIONAL_CAMBIO[i]);
        }
        return consumoAdicional;
    }

    /**
     * Método de fábrica para crear una ruta predefinida: "Autopista".
     * @return una instancia de la ruta "Autopista" con una distancia de 45 km.
     */
    public static Ruta crearRutaAutopista() {
        return new Ruta("Autopista", 45);
    }

    /**
     * Método de fábrica para crear una ruta predefinida: "Santa Elena".
     * @return una instancia de la ruta "Santa Elena" con una distancia de 50 km.
     */
    public static Ruta crearRutaSantaElena() {
        return new Ruta("Santa Elena", 50);
    }

    /**
     * Método de fábrica para crear una ruta predefinida: "Las Palmas".
     * @return una instancia de la ruta "Las Palmas" con una distancia de 55 km.
     */
    public static Ruta crearRutaLasPalmas() {
        return new Ruta("Las Palmas", 55);
    }
}
