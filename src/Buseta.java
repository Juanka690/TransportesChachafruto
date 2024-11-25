public class Buseta extends Vehiculo {

    float[] pasajerosKm;

    public Buseta() {
        super(1 / 30f, new float[]{0.12f, 0.15f, 0.15f}, 15, 0, 12000, 25000, new float[]{0, 0, 0});
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
        return (consumoPorKm * ruta.distancia) * (consumoAdicionalRuta[idRuta()] * pasajerosIniciales) + consumoPasajeros;
    }
}