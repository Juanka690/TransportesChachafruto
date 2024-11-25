public class Van extends Vehiculo {

    float[] pasajerosKm;

    public Van() {
        super(1 / 40f, new float[]{0.08f, 0.11f, 0.11f}, 10, 0, 15000, 20500, new float[]{0, 0, 0});
    }

    public void setPasajerosKm(float[] pasajerosKm) {
        this.pasajerosKm = pasajerosKm;
    }

    @Override
    public float calcularConsumoPasajerosRuta() {
        float pasajerosIniciales = pasajeros - pasajerosKm.length;
        float consumoPasajeros = 0;
        for (int i = 0; i < pasajerosKm.length; i++) {
            consumoPasajeros += (((ruta.distancia) - pasajerosKm[i]) * consumoPorKm) * ( consumoAdicionalRuta[idRuta()]);
        }
        return (consumoPorKm * ruta.distancia) * (consumoAdicionalRuta[idRuta()] * pasajerosIniciales) + consumoPasajeros;    }
}
