public class Vehiculo {

    float consumoPorKm;
    float[] consumoAdicionalRuta;
    int capacidad;
    int pasajeros;
    float valorPasaje;
    float valorPeaje;
    boolean subiendo = false;
    final float PRECIO_GASOLINA = 16000;

    //Se guarda cuantos kilometros ando en cada cambio, [0]1era, [1]2da y [2]3era
    float cambioKm[];
    Ruta ruta;

    public Vehiculo(float consumoPorKm, float[] consumoAdicionalRuta, int capacidad, int pasajeros, float valorPasaje, float valorPeaje, float[] cambioKm) {
        this.consumoPorKm = consumoPorKm;
        this.consumoAdicionalRuta = consumoAdicionalRuta;
        this.capacidad = capacidad;
        this.pasajeros = pasajeros;
        this.valorPasaje = valorPasaje;
        this.valorPeaje = valorPeaje;
        this.cambioKm = cambioKm;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public void setCambioKm(float[] cambioKm) {
        this.cambioKm = cambioKm;
    }

    public float calcularGastosPeaje() {
        return (valorPeaje * 2);
    }

    public float calcularIngresosPasajes() {
        return pasajeros * valorPasaje;
    }

    public float calcularConsumoBase() {
        float distancia = ruta.distancia - (cambioKm[0] + cambioKm[1] + cambioKm[2]);
        return distancia * consumoPorKm;
    }

    public float calcularGasolinaConsumidaTotal() {
        float consumoBase = calcularConsumoBase();
        float consumoAdicional = ruta.calcularConsumoCambios(cambioKm, consumoPorKm);
        float consumoPasajeros = calcularConsumoPasajerosRuta();

        return (consumoBase + consumoAdicional + consumoPasajeros) * PRECIO_GASOLINA;
    }

    //en galones
    public float calcularConsumoPasajerosRuta() {
        float consumoPasajeros = (consumoPorKm * ruta.distancia) * (consumoAdicionalRuta[idRuta()] * 4);
        return consumoPasajeros;
    }

    public float calcularRentabilidad() {
        float gastos = calcularGasolinaConsumidaTotal() + calcularGastosPeaje();
        float ingresos = calcularIngresosPasajes();
        return ingresos - gastos;
    }

    public int idRuta() {
        if (ruta.nombreRuta.equals("Autopista")) return 0;
        if (ruta.nombreRuta.equals("Santa Elena")) return 1;
        if (ruta.nombreRuta.equals("Las Palmas")) return 2;
        return -1;
    }

    public void setPasajerosKm(float[] pasajerosKm) {
    }
}
