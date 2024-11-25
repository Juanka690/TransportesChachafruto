import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehiculo vehiculo = null;
        Ruta rutaSeleccionada = null;


        while (true) {
            System.out.println("\nBienvenido al sistema de liquidación de consumo de combustible y rentabilidad");
            System.out.println("Seleccione el tipo de vehículo:");
            System.out.println("1. Taxi");
            System.out.println("2. Van");
            System.out.println("3. Buseta");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcionVehiculo = scanner.nextInt();

            if (opcionVehiculo == 4) {
                System.out.println("Saliendo del programa...");
                break;
            }

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

            System.out.println("\nSeleccione la ruta:");
            System.out.println("1. Autopista (90 km ida y vuelta)");
            System.out.println("2. Santa Elena (100 km ida y vuelta)");
            System.out.println("3. Las Palmas (110 km ida y vuelta)");
            System.out.print("Opción: ");
            int opcionRuta = scanner.nextInt();

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
            int viaje = 1;

            float pasajes = 0, peajes = 0, gasolinaGalones = 0, gasolinaTotal = 0, rentabilidad = 0;
            float pasajerosCo = 0;

            while (viaje <= 2) {
                if (opcionVehiculo == 2 || opcionVehiculo == 3) {
                    System.out.println("Ingrese la cantidad de pasajeros con la que inicio el trayecto: ");
                    int pasajerosIniciales = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de pasajeros que se subieron despues de iniciado el viaje: ");
                    int pasajeros = scanner.nextInt();
                    vehiculo.pasajeros = pasajeros + pasajerosIniciales;

                    if (vehiculo.pasajeros > vehiculo.capacidad) {
                        System.out.println("La cantidad de pasajeros supera la capacidad del vehículo. Intente de nuevo.");
                        break;
                    }

                    float[] pasajerosKm = new float[pasajeros];
                    for (int i = 0; i < pasajeros; i++) {
                        System.out.print("KM del Pasajero " + (i + 1) + ": ");
                        pasajerosKm[i] = scanner.nextFloat();
                    }
                    vehiculo.setPasajerosKm(pasajerosKm);
                }
                if (vehiculo.subiendo) {
                    System.out.println("\nIngrese los kilómetros recorridos en cada cambio:");
                    System.out.print("Kilómetros en 1ra: ");
                    float km1 = scanner.nextFloat();
                    System.out.print("Kilómetros en 2da: ");
                    float km2 = scanner.nextFloat();
                    System.out.print("Kilómetros en 3ra: ");
                    float km3 = scanner.nextFloat();

                    if (km1 + km2 + km3 == rutaSeleccionada.distancia) {
                        vehiculo.setCambioKm(new float[]{km1, km2, km3});
                    } else {
                        System.out.println("La suma de los kilómetros recorridos en cada cambio no coincide con la distancia de la ruta. Intente de nuevo.");
                        break;
                    }

                    vehiculo.subiendo = false;

                } else {
                    vehiculo.subiendo = true;
                }

                viaje++;
                pasajes += vehiculo.calcularIngresosPasajes();
                peajes = vehiculo.calcularGastosPeaje();
                gasolinaGalones += vehiculo.calcularGasolinaConsumidaTotal() / vehiculo.PRECIO_GASOLINA;
                gasolinaTotal += vehiculo.calcularGasolinaConsumidaTotal();
                rentabilidad += vehiculo.calcularRentabilidad();

                pasajerosCo += vehiculo.calcularConsumoBase();

            }
            // Mostrar resultados
            tablaRentabilidad(pasajes, peajes, gasolinaGalones, gasolinaTotal, rentabilidad, pasajerosCo);
        }
    }

    public static void tablaRentabilidad(float pasajes, float peajes, float gasolinaGalones, float gasolinaTotal, float rentabilidad , float pasajerosCo) {
        System.out.println("\n\nResultados del viaje:");
        System.out.println("Ingresos por pasajes: $" + pasajes);
        System.out.println("Gastos por peajes: $" + peajes);
        System.out.println("Consumo de gasolina: " + gasolinaGalones + " galones");
        System.out.println("Gasto total en gasolina: $" + gasolinaTotal);
        System.out.println("Rentabilidad del viaje: $" + rentabilidad);
        System.out.println("Pasajeros CO: " + pasajerosCo);
    }

}
