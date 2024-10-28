package org.example.view;
import org.example.controller.ReservaController;
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
    TarifaController tarifaController = new TarifaController();
    HotelView hotelView = new HotelView();
    HabitacionView habitacionView = new HabitacionView();
    CiudadView ciudadView = new CiudadView();
    HuespedView huespedView = new HuespedView();

    public void Reserva() {
        boolean volver = false;

        while (!volver) {
            System.out.println(azul + "MENU RESERVAS");
            System.out.println("1 - Hacer una Reserva..");
            System.out.println("2 - Buscar Reservas.");
            System.out.println("3 - Modificar un Hotel.");

            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertReserva();
                    esperarEnter();
                    break;
                case "2":
                    buscarReserva();
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
        } catch (Exception e) {
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

    public void buscarReserva() {
        boolean volver = false;

        while (!volver) {
            System.out.println("1 - Buscar Reserva por ID.");
            System.out.println("2 - Buscar Reserva por Huesped.");
            System.out.println("3 - Buscar Reserva por Hotel.");
            System.out.println("4 - Ver todas las Reservas.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    getReservaById();
                    esperarEnter();
                    break;
                case "2":
                    getReservaByHuesped();
                    esperarEnter();
                    break;
                case "3":
                    getReservaByHotel();
                    esperarEnter();
                    break;
                case "4":
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

    public void  detallesReserva (int idReserva){
        try {
            Reserva reserva = this.reservaController.getReservaById(idReserva);

            System.out.println(verde + "Hotel: " + reserva.getHabitacion().getHotel().getNombre());
            System.out.print(reset + reserva.getHabitacion().getHotel().getDireccion());
            System.out.print(", " + reserva.getHabitacion().getHotel().getCiudad().getNombre());
            System.out.println(", " + reserva.getHabitacion().getHotel().getCiudad().getPais().getNombre());
            System.out.print("Estrellas : ");
            for (int i = 0; i < reserva.getHabitacion().getHotel().getEstrellas(); i++) {
                System.out.println("⭐");
            }
            System.out.println(" ");
            System.out.println(verde + "Numero de Habitación : " + reserva.getHabitacion().getnumHabitacion());
            System.out.println(reset + "Capacidad: " + (reserva.getHabitacion().getCamasSimple() + reserva.getHabitacion().getCamasDoble()) + " personas.");
            System.out.println(" - Camas Simples: " + reserva.getHabitacion().getCamasSimple());
            System.out.println(" - Camas Dobles: " + reserva.getHabitacion().getCamasDoble());
            System.out.println("Amenities: ");
            for (Amenitie amenitie : reserva.getHabitacion().getAmenitieList()) {
                System.out.println(" - " + amenitie);
            }
            System.out.print(verde + "Huesped: " + reset + reserva.getHuesped().getNombre());
            System.out.print(" " + reserva.getHuesped().getApPaterno());
            System.out.println(" " + reserva.getHuesped().getApMaterno());
            System.out.println("Cantidad de Personas: " + reserva.getCantidadPersonas());
            System.out.println("Desde " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
            System.out.println("Reserva realizada el " + reserva.getFechaReserva());
            System.out.println("Estado actual: ");
            if (reserva.getHabitacion().isOcupada()) {
                System.out.println(rojo + "Ocupada.");
            } else {
                System.out.println(verde + "Desocupada.");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Ocurrió un error al obtener la reserva. ");
        }
    }
    public void getReservaById() {
        try {
            System.out.println(reset + "Ingrese ID de la Reserva: ");
            int idReserva = Integer.parseInt(scanner.nextLine());
            detallesReserva(idReserva);

        } catch (NumberFormatException e) {
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

    public void getReservaByHuesped() {
        Hotel hotel = hotelView.getHotel();

        List<Reserva> reservaList = this.reservaController.getReservaByHotel(hotel.getIdHotel());

        if (reservaList.isEmpty()) {
            System.out.println("No se encontró Reservas.");
        } else {
            for (Reserva reserva : reservaList) {
                detallesReserva(reserva.getIdReserva());
            }
        }
    }

    public void getReservaByHotel() {
        Hotel hotel = hotelView.getHotel();

        List<Reserva> reservaList = this.reservaController.getReservaByHotel(hotel.getIdHotel());

        if (reservaList.isEmpty()) {
            System.out.println("No se encontró Reservas.");
        } else {
            for (Reserva reserva : reservaList) {
                detallesReserva(reserva.getIdReserva());
            }
        }
    }

    public void getAllReserva() {
        List<Reserva> reservaList = this.reservaController.getAllReserva();

        if (reservaList.isEmpty()) {
            System.out.println("No se encontró Reservas.");
        } else {
            for (Reserva reserva : reservaList) {
                detallesReserva(reserva.getIdReserva());
            }
        }
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
