/**
 * Clase que representa una buseta como un tipo específico de vehículo.
 * Hereda de la clase base Vehiculo y especializa algunos de sus métodos.
 */
public class Buseta extends Vehiculo {

    // Array que almacena los kilómetros donde los pasajeros se bajan durante el trayecto
    float[] pasajerosKm;

    /**
     * Constructor de la clase Buseta.
     * Inicializa los valores específicos para una buseta, como el consumo por kilómetro,
     * los factores de consumo adicional, la capacidad, el precio del pasaje y del peaje.
     */
    public Buseta() {
        super(1 / 30f, new float[]{0.12f, 0.15f, 0.15f}, 15, 0, 12000, 25000, new float[]{0, 0, 0});
    }

    /**
     * Método para establecer el array de kilómetros donde los pasajeros se bajan durante la ruta.
     * @param pasajerosKm Array que contiene los kilómetros recorridos antes de que cada pasajero se baje.
     */
    public void setPasajerosKm(float[] pasajerosKm) {
        this.pasajerosKm = pasajerosKm;
    }

    /**
     * Calcula el consumo de gasolina adicional debido a los pasajeros durante la ruta.
     * Considera tanto los pasajeros que completan toda la ruta como aquellos que se bajan antes de finalizarla.
     *
     * @return El consumo total de gasolina adicional relacionado con los pasajeros, medido en galones.
     */
    @Override
    public float calcularConsumoPasajerosRuta() {
        // Calcula el número de pasajeros iniciales que realizan todo el trayecto
        float pasajerosIniciales = pasajeros - pasajerosKm.length;

        // Inicializa el consumo adicional debido a los pasajeros que se bajan antes de finalizar
        float consumoPasajeros = 0;

        // Itera sobre los pasajeros que se bajan en puntos intermedios del trayecto
        for (int i = 0; i < pasajerosKm.length; i++) {
            consumoPasajeros += (((ruta.distancia) - pasajerosKm[i]) * consumoPorKm) * (consumoAdicionalRuta[idRuta()]);
        }

        // Calcula el consumo total combinando el consumo base de los pasajeros iniciales y el adicional
        return (consumoPorKm * ruta.distancia) * (consumoAdicionalRuta[idRuta()] * pasajerosIniciales) + consumoPasajeros;
    }
}