public class Van extends Vehiculo {

    // Array que indica el número de pasajeros en diferentes segmentos de la ruta
    float[] pasajerosKm;

    /**
     * Constructor por defecto para la clase Van.
     * Configura las características específicas de este tipo de vehículo:
     * - Consumo por kilómetro (40 km por galón)
     * - Consumo adicional por pasajero según la ruta
     * - Capacidad máxima: 10 pasajeros
     * - Costo del pasaje: 15,000
     * - Costo del peaje: 20,500
     * - Kilómetros iniciales recorridos en cada marcha: 0.
     */
    public Van() {
        super(1 / 40f, new float[]{0.08f, 0.11f, 0.11f}, 10, 0, 15000, 20500, new float[]{0, 0, 0});
    }

    /**
     * Asigna un arreglo que indica el número de pasajeros en cada segmento de la ruta.
     * @param pasajerosKm array con los kilómetros donde descienden pasajeros.
     */
    public void setPasajerosKm(float[] pasajerosKm) {
        this.pasajerosKm = pasajerosKm;
    }

    /**
     * Calcula el consumo adicional de gasolina debido al peso de los pasajeros,
     * considerando los descensos en diferentes puntos de la ruta.
     * Sobrescribe el método base de la clase Vehiculo para adaptar la lógica a una Van.
     *
     * @return el consumo total adicional por los pasajeros en galones.
     */
    @Override
    public float calcularConsumoPasajerosRuta() {
        // Calcula el consumo inicial con todos los pasajeros a bordo
        float pasajerosIniciales = pasajeros - pasajerosKm.length;
        float consumoPasajeros = 0;

        // Itera por cada segmento de la ruta donde descienden pasajeros
        for (int i = 0; i < pasajerosKm.length; i++) {
            consumoPasajeros += (((ruta.distancia) - pasajerosKm[i]) * consumoPorKm)
                    * (consumoAdicionalRuta[idRuta()]);
        }

        // Suma el consumo inicial y el adicional calculado
        return (consumoPorKm * ruta.distancia)
                * (consumoAdicionalRuta[idRuta()] * pasajerosIniciales)
                + consumoPasajeros;
    }
}
