/**
 * Clase que representa un taxi como un tipo específico de vehículo.
 * Hereda de la clase base Vehiculo y define los valores predeterminados para un taxi.
 */
public class Taxi extends Vehiculo {

    /**
     * Constructor de la clase Taxi.
     * Inicializa los valores específicos para un taxi, como el consumo por kilómetro,
     * los factores de consumo adicional, la capacidad de pasajeros, el precio del pasaje y del peaje.
     */
    public Taxi() {
        super(
                1 / 50f,
                new float[]{0.05f, 0.07f, 0.07f}, 4, 4, 25000, 15500, new float[]{0, 0, 0});
    }
}
