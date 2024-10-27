package org.example.view;
import org.example.controller.ReservaController;
import org.example.controller.ReservaHabitacionController;
import org.example.controller.TarifaController;
import org.example.model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservaView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    Scanner scanner = new Scanner(System.in);
    ReservaController reservaController = new ReservaController();
    ReservaHabitacionController reservaHabitacionController = new ReservaHabitacionController();
    TarifaController tarifaController = new TarifaController();
    HotelView hotelView = new HotelView();
    HabitacionView habitacionView = new HabitacionView();
    CiudadView ciudadView = new CiudadView();
    HuespedView huespedView = new HuespedView();

    public void Reservs() {
        boolean volver = false;

        while (!volver) {
            System.out.println(azul + "MENU HOTELES");
            System.out.println("1 - Ingresar un Hotel.");
            System.out.println("2 - Eliminar un Hotel.");
            System.out.println("3 - Modificar un Hotel.");

            System.out.println("4 - Consultar Hoteles.");
            System.out.println("5 - Ver Hoteles.");
            System.out.println("6 - Ver un Hotel.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertReserva();
                    esperarEnter();
                    break;
                case "2":
                    getReservaById();
                    esperarEnter();
                    break;
                case "3":
                    getAllReserva();
                    esperarEnter();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println(rojo + "Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    public void insertReserva() {
        try {
            System.out.println(azul + "Registrar reserva:");
            System.out.println(reset + "Huesped responsable:");
            Huesped huesped = huespedView.seleccionarHuesped();
            if (huesped != null) {
                System.out.println(reset + "Cantidad de personas(incluir al responsable):");
                int cantidadPersonas = Integer.parseInt(scanner.nextLine());

                String fechaInicio = "";
                String fechaFin = "";

                while (fechaInicio.isEmpty()) {
                    System.out.println("Fecha de ingreso: ");
                    fechaInicio = scanner.nextLine();
                }
                while (fechaFin.isEmpty()) {
                    System.out.println("Fecha de salida: ");
                    fechaFin = scanner.nextLine();
                }

                System.out.println(azul + "Ingresar habitación.");
                System.out.println(reset + "Buscar Habitación por: ");
                System.out.println("1 - Hotel.");
                System.out.println("2 - Ciudad.");
                String opcion = scanner.nextLine();

                List<Habitacion> habitacionList = new ArrayList<>();
                Hotel hotel = null;
                Ciudad ciudad = null;
                Habitacion habitacion = null;

                switch (opcion) {
                    case "1":
                        System.out.println(reset + "Seleccione Hotel: ");
                        hotel = hotelView.getHotel();
                        if (hotel != null) {
                            habitacion = this.habitacionView.seleccionarHabitacionByHotel(hotel.getIdHotel(), fechaInicio, fechaFin);
                        }
                        break;

                    case "2":
                        System.out.println(reset + "Seleccione Ciudad: ");
                        ciudad = ciudadView.seleccionarCiudad();
                        if (ciudad != null) {
                            habitacion = this.habitacionView.habitacionDisponiblePorCiudad(ciudad.getIdCiudad(), fechaInicio, fechaFin);
                        }
                        break;

                    default:
                        System.out.println(rojo + "Opción no válida.");
                        break;

                }

                if (habitacion == null) {
                    System.out.println(rojo + "No se pudo obtener Habitación.");

                } else {

                    Tarifa tarifa = tarifaController.getTarifaByHabitacion(habitacion.getIdHabitacion());

                    LocalDate localDate = LocalDate.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String fechaActual = localDate.format(dateTimeFormatter);

                    Reserva reserva = new Reserva(cantidadPersonas, fechaActual, tarifa.getMonto(),
                            fechaInicio, fechaFin, habitacion, huesped);

                    boolean ReservaInserted = this.reservaController.insertReserva(reserva);

                    if (ReservaInserted) {
                        System.out.println(verde + "Reserva ingresada!");
                    } else {
                         System.out.println(rojo + "Ocurrió un error al ingresar la Reserva, \nvuelva a intentarlo más tarde.");
                    }
                }
            }
        } catch (
                Exception e) {
            System.out.println(rojo + "Algo salió mal.");
            System.out.println(azul + "1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertReserva();
                    break;
                case "2":
                    System.out.println(verde + "volviendo...");
                    break;
                default:
                    System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                    break;
            }
        }
    }

    public void getReservaById() {

    }

    public void getAllReserva() {

    }

    public void esperarEnter() {
        System.out.println(verde + "Presiona Enter para continuar...");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        limpiarConsola();
    }

    public void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
