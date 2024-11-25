public class Vehiculo {

    // Consumo de gasolina en kilómetros por litro en condiciones normales
    float consumoPorKm;

    // Consumo adicional de gasolina dependiendo de la ruta, por cada pasajero
    float[] consumoAdicionalRuta;

    // Capacidad máxima del vehículo en número de pasajeros
    int capacidad;

    // Número actual de pasajeros en el vehículo
    int pasajeros;

    // Valor del pasaje por pasajero
    float valorPasaje;

    // Costo por cada peaje en la ruta
    float valorPeaje;

    // Indica si el vehículo está subiendo una pendiente
    boolean subiendo = false;

    // Precio por galón de gasolina (Esta es una constante)
    final float PRECIO_GASOLINA = 16000;

    // Distancia recorrida en cada marcha: [0] primera, [1] segunda, [2] tercera
    float[] cambioKm;

    // Ruta asignada al vehículo
    Ruta ruta;

    /**
     * Constructor para inicializar un vehículo con sus características principales.
     * @param consumoPorKm consumo de gasolina por kilómetro.
     * @param consumoAdicionalRuta consumo adicional por pasajero según la ruta.
     * @param capacidad capacidad máxima del vehículo.
     * @param pasajeros número actual de pasajeros.
     * @param valorPasaje costo del pasaje por pasajero.
     * @param valorPeaje costo del peaje por ruta.
     * @param cambioKm kilómetros recorridos en cada marcha.
     */
    public Vehiculo(float consumoPorKm, float[] consumoAdicionalRuta, int capacidad, int pasajeros, float valorPasaje, float valorPeaje, float[] cambioKm) {
        this.consumoPorKm = consumoPorKm;
        this.consumoAdicionalRuta = consumoAdicionalRuta;
        this.capacidad = capacidad;
        this.pasajeros = pasajeros;
        this.valorPasaje = valorPasaje;
        this.valorPeaje = valorPeaje;
        this.cambioKm = cambioKm;
    }

    /**
     * Asigna una ruta al vehículo.
     * @param ruta objeto Ruta que representa la ruta asignada.
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    /**
     * Asigna los kilómetros recorridos en cada marcha.
     * @param cambioKm array con kilómetros recorridos en cada marcha.
     */
    public void setCambioKm(float[] cambioKm) {
        this.cambioKm = cambioKm;
    }

    /**
     * Calcula el gasto total en peajes considerando ida y vuelta.
     * @return el costo total de los peajes.
     */
    public float calcularGastosPeaje() {
        return (valorPeaje * 2);
    }

    /**
     * Calcula los ingresos totales por la venta de pasajes.
     * @return el ingreso total obtenido por los pasajeros.
     */
    public float calcularIngresosPasajes() {
        return pasajeros * valorPasaje;
    }

    /**
     * Calcula el consumo de gasolina base sin considerar cambios de marcha ni pasajeros.
     * @return el consumo base en litros.
     */
    public float calcularConsumoBase() {
        float distancia = ruta.distancia - (cambioKm[0] + cambioKm[1] + cambioKm[2]);
        return distancia * consumoPorKm;
    }

    /**
     * Calcula el consumo total de gasolina considerando consumo base, consumo por cambios y pasajeros.
     * @return el costo total de la gasolina consumida en la ruta.
     */
    public float calcularGasolinaConsumidaTotal() {
        float consumoBase = calcularConsumoBase();
        float consumoAdicional = ruta.calcularConsumoCambios(cambioKm, consumoPorKm);
        float consumoPasajeros = calcularConsumoPasajerosRuta();

        return (consumoBase + consumoAdicional + consumoPasajeros) * PRECIO_GASOLINA;
    }

    /**
     * Calcula el consumo adicional de gasolina debido al peso de los pasajeros.
     * @return el consumo total adicional por los pasajeros en galones.
     */
    public float calcularConsumoPasajerosRuta() {
        return (consumoPorKm * ruta.distancia) * (consumoAdicionalRuta[idRuta()] * 4);
    }

    /**
     * Calcula la rentabilidad del vehículo considerando ingresos y gastos.
     * @return la diferencia entre ingresos por pasajes y gastos totales.
     */
    public float calcularRentabilidad() {
        float gastos = calcularGasolinaConsumidaTotal() + calcularGastosPeaje();
        float ingresos = calcularIngresosPasajes();
        return ingresos - gastos;
    }

    /**
     * Obtiene el índice de la ruta actual para identificar consumos adicionales.
     * @return índice de la ruta (0: Autopista, 1: Santa Elena, 2: Las Palmas).
     */
    public int idRuta() {
        return switch (ruta.nombreRuta) {
            case "Autopista" -> 0;
            case "Santa Elena" -> 1;
            case "Las Palmas" -> 2;
            default -> -1;
        };
    }

    /**
     * Asigna un arreglo con la cantidad de pasajeros en diferentes segmentos de la ruta.
     * @param pasajerosKm array que representa pasajeros en segmentos de la ruta.
     */
    public void setPasajerosKm(float[] pasajerosKm) {
        // Se sobreescribe en las clases hijas (Buseta y Van)
    }
}