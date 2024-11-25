import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Para leer datos de entrada del usuario
        Vehiculo vehiculo = null; // Almacena el vehículo seleccionado
        Ruta rutaSeleccionada = null; // Almacena la ruta seleccionada

        while (true) {
            // Menú principal para seleccionar tipo de vehículo o salir del programa
            System.out.println("\nBienvenido al sistema de liquidación de consumo de combustible y rentabilidad");
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("1. Taxi");
            System.out.println("2. Van");
            System.out.println("3. Buseta");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcionVehiculo = scanner.nextInt();

            if (opcionVehiculo == 4) { // Opción para salir del programa
                System.out.println("Saliendo del programa...");
                break;
            }

            // Selección del tipo de vehículo
            switch (opcionVehiculo) {
                case 1:
                    vehiculo = new Taxi();
                    break;
                case 2:
                    vehiculo = new Van();
                    break;
                case 3:
                    vehiculo = new Buseta();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }

            // Menú para seleccionar una ruta
            System.out.println("\nSeleccione la ruta:");
            System.out.println("1. Autopista (90 km ida y vuelta)");
            System.out.println("2. Santa Elena (100 km ida y vuelta)");
            System.out.println("3. Las Palmas (110 km ida y vuelta)");
            System.out.print("Opción: ");
            int opcionRuta = scanner.nextInt();

            // Selección de la ruta según la opción del usuario
            switch (opcionRuta) {
                case 1:
                    rutaSeleccionada = Ruta.crearRutaAutopista();
                    break;
                case 2:
                    rutaSeleccionada = Ruta.crearRutaSantaElena();
                    break;
                case 3:
                    rutaSeleccionada = Ruta.crearRutaLasPalmas();
                    break;
                default:
                    System.out.println("Opción de ruta no válida. Intente de nuevo.");
                    continue;
            }

            // Asignar la ruta seleccionada al vehículo
            vehiculo.setRuta(rutaSeleccionada);

            int viaje = 1; // Contador de viajes ida y vuelta
            float pasajes = 0, peajes = 0, gasolinaGalones = 0, gasolinaTotal = 0, rentabilidad = 0, pasajerosCo = 0;

            while (viaje <= 2) { // Iteración para el viaje de ida y vuelta
                if (opcionVehiculo == 2 || opcionVehiculo == 3) { // Si es Van o Buseta
                    System.out.println("Ingrese la cantidad de pasajeros con la que inició el trayecto: ");
                    int pasajerosIniciales = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de pasajeros que se subieron después de iniciado el viaje: ");
                    int pasajeros = scanner.nextInt();
                    vehiculo.pasajeros = pasajeros + pasajerosIniciales;

                    if (vehiculo.pasajeros > vehiculo.capacidad) { // Validación de capacidad del vehículo
                        System.out.println("La cantidad de pasajeros supera la capacidad del vehículo. Intente de nuevo.");
                        break;
                    }

                    // Registrar los kilómetros en los que se montan los pasajeros
                    float[] pasajerosKm = new float[pasajeros];
                    for (int i = 0; i < pasajeros; i++) {
                        System.out.print("KM del Pasajero " + (i + 1) + ": ");
                        pasajerosKm[i] = scanner.nextFloat();
                    }
                    vehiculo.setPasajerosKm(pasajerosKm);
                }

                if (vehiculo.subiendo) { // Si el viaje es de subida
                    System.out.println("\nIngrese los kilómetros recorridos en cada cambio:");
                    System.out.print("Kilómetros en 1ra: ");
                    float km1 = scanner.nextFloat();
                    System.out.print("Kilómetros en 2da: ");
                    float km2 = scanner.nextFloat();
                    System.out.print("Kilómetros en 3ra: ");
                    float km3 = scanner.nextFloat();

                    // Validación de que los kilómetros totales coinciden con la distancia de la ruta
                    if (km1 + km2 + km3 == rutaSeleccionada.distancia) {
                        vehiculo.setCambioKm(new float[]{km1, km2, km3});
                    } else {
                        System.out.println("La suma de los kilómetros recorridos en cada cambio no coincide con la distancia de la ruta. Intente de nuevo.");
                        break;
                    }

                    vehiculo.subiendo = false; // Cambia a viaje de bajada
                } else {
                    vehiculo.subiendo = true; // Cambia a viaje de subida
                }

                viaje++;

                // Acumular los resultados del viaje
                pasajes += vehiculo.calcularIngresosPasajes();
                peajes = vehiculo.calcularGastosPeaje();
                gasolinaGalones += vehiculo.calcularGasolinaConsumidaTotal() / vehiculo.PRECIO_GASOLINA;
                gasolinaTotal += vehiculo.calcularGasolinaConsumidaTotal();
                rentabilidad += vehiculo.calcularRentabilidad();
                pasajerosCo += vehiculo.calcularConsumoBase();
            }

            // Mostrar los resultados en una tabla
            tablaRentabilidad(pasajes, peajes, gasolinaGalones, gasolinaTotal, rentabilidad, pasajerosCo);
        }
    }

    /**
     * Método para mostrar los resultados del cálculo de rentabilidad del viaje en formato de tabla.
     * @param pasajes ingresos por pasajes vendidos.
     * @param peajes gastos por peajes en la ruta.
     * @param gasolinaGalones consumo total de gasolina en galones.
     * @param gasolinaTotal costo total de la gasolina consumida.
     * @param rentabilidad diferencia entre ingresos y gastos del viaje.
     * @param pasajerosCo consumo base relacionado con los pasajeros.
     */
    public static void tablaRentabilidad(float pasajes, float peajes, float gasolinaGalones, float gasolinaTotal, float rentabilidad, float pasajerosCo) {
        System.out.println("\n\nResultados del viaje:");
        System.out.println("Ingresos por pasajes: $" + pasajes);
        System.out.println("Gastos por peajes: $" + peajes);
        System.out.println("Consumo de gasolina: " + gasolinaGalones + " galones");
        System.out.println("Gasto total en gasolina: $" + gasolinaTotal);
        System.out.println("Rentabilidad del viaje: $" + rentabilidad);
        System.out.println("Consumo base por pasajeros: " + pasajerosCo + " galones");
    }
}
