package org.example.view;

import org.example.controller.HabitacionController;
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
    HabitacionController habitacionController = new HabitacionController();

    public void Reserva() {
        boolean volver = false;

        while (!volver) {
            System.out.println(azul + "MENU RESERVAS");
            System.out.println("1 - Hacer una Reserva..");
            System.out.println("2 - Consultar Reservas.");
            System.out.println("3 - Cancelar Reservas.");
            System.out.println("4 - Modificar Reservas.");

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
                    deleteReserva();
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
                            habitacion = this.habitacionView.seleccionarHabitacionByHotelFecha(hotel.getIdHotel(), fechaInicio, fechaFin);
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
                            fechaInicio, fechaFin, huesped);

                    boolean ReservaInserted = this.reservaController.insertReserva(reserva);
                    if(ReservaInserted) {
                        Reserva ultimaReserva = this.reservaController.getUltimaReserva();
                        ReservaInserted = this.reservaHabitacionController.insertReservaHabitacion(ultimaReserva.getIdReserva(), huesped.getIdHuesped());
                    }


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
            System.out.println(azul + "1 - Buscar Reserva por ID.");
            System.out.println("2 - Consultar Reserva por Huesped.");
            System.out.println("3 - Consultar Reserva por Hotel.");
            System.out.println("4 - Consultar Reserva por Fecha.");
            System.out.println("5 - Consultar todas las Reservas.");
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
                    getReservaByFecha();
                    esperarEnter();
                    break;
                case "5":
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

    public void detallesReserva(int idReserva) {
        try {
            Reserva reserva = this.reservaController.getReservaById(idReserva);
            List<Habitacion> habitacionList = this.habitacionController.habitacionPorReserva(reserva.getIdReserva());

            System.out.print(azul + "Huesped: " + reset + reserva.getHuesped().getNombre());
            System.out.print(" " + reserva.getHuesped().getApPaterno());
            System.out.println(" " + reserva.getHuesped().getApMaterno());
            System.out.println("Cantidad de Personas: " + reserva.getCantidadPersonas());
            System.out.println("Desde " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
            System.out.println("Reserva realizada el " + reserva.getFechaReserva());
            for (Habitacion habitacion : habitacionList) {
                System.out.println(azul + "Hotel: " + habitacion.getHotel().getNombre());
                System.out.print(reset + habitacion.getHotel().getDireccion());
                System.out.print(", " + habitacion.getHotel().getCiudad().getNombre());
                System.out.println(", " + habitacion.getHotel().getCiudad().getPais().getNombre());
                System.out.print("Estrellas : ");
                for (int i = 0; i < habitacion.getHotel().getEstrellas(); i++) {
                    System.out.print("⭐");
                }
                System.out.println(" ");
                System.out.println("Numero de Habitación : " + habitacion.getnumHabitacion());
                System.out.println("Capacidad: " + ((habitacion).getCamasSimple() + ((habitacion).getCamasDoble() * 2)) + " personas.");
                System.out.println(" - Camas Simples: " + habitacion.getCamasSimple());
                System.out.println(" - Camas Dobles: " + habitacion.getCamasDoble());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacion.getAmenitieList()) {
                    System.out.println(" - " + amenitie.getNombre());
                }
                System.out.println("Estado actual: ");
                if (habitacion.isOcupada()) {
                    System.out.println(rojo + "Ocupada.");
                } else {
                    System.out.println(verde + "Desocupada.");
                }
            }

            System.out.println(azul + "______________________________________________________________________________");
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
        Huesped huesped = huespedView.seleccionarHuesped();

        if (huesped == null) {
            System.out.println(rojo + "No se seleccionó Huesped.");
        } else {
            List<Reserva> reservaList = this.reservaController.getReservaByHuesped(huesped.getIdHuesped());

            if (reservaList.isEmpty()) {
                System.out.println("No se encontró Reservas.");
            } else {
                for (Reserva reserva : reservaList) {
                    detallesReserva(reserva.getIdReserva());
                }
            }
        }
    }

    public void getReservaByFecha() {
        try {
            System.out.println(reset + "Periodo de fecha: ");
            System.out.println(reset + "Desde(aaaa-mm-dd): ");
            String fechaInicio = scanner.nextLine();
            System.out.println(reset + "Hasta(aaaa-mm-dd): ");
            String fechaFin = scanner.nextLine();

            List<Reserva> reservaList = this.reservaController.getReservaByFecha(fechaInicio, fechaFin);
            for (Reserva reserva : reservaList) {
                detallesReserva(reserva.getIdReserva());
            }


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

    public void getReservaByHotel() {
        Hotel hotel = hotelView.getHotel();

        if (hotel == null) {
            System.out.println(rojo + "No se seleccionó Hotel.");
        } else {
            List<Reserva> reservaList = this.reservaController.getReservaByHotel(hotel.getIdHotel());

            if (reservaList.isEmpty()) {
                System.out.println("No se encontró Reservas.");
            } else {
                for (Reserva reserva : reservaList) {
                    detallesReserva(reserva.getIdReserva());
                }
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

    public void deleteReserva() {
        try {
            boolean volver = false;

            while (!volver) {
                System.out.println(azul + "CANCELAR RESERVA.");
                System.out.println("1 - Buscar Reserva por Huesped.");
                System.out.println("4 - Buscar Reserva por Fecha.");
                System.out.println("0 - Volver.");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        deleteReservaByHuesped();
                        esperarEnter();
                        break;
                    case "2":
                        deleteReservaByFecha();
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

        } catch (NumberFormatException e) {
            System.out.println(rojo + "Algo salió mal.");
            System.out.println(azul + "1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    deleteReserva();
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

    public void deleteReservaByHuesped() {
        try {
            Huesped huesped = huespedView.seleccionarHuesped();
            List<Reserva> reservaList = reservaController.getReservaByHuesped(huesped.getIdHuesped());

            for (Reserva reserva : reservaList) {
                List<Habitacion> habitacionList = this.habitacionController.habitacionPorReserva(reserva.getIdReserva());
                System.out.println(reset + "Número de Reserva: " + reserva.getIdReserva());
                System.out.println("Habitaciones:");
                for (Habitacion habitacion : habitacionList) {
                    System.out.println("Habitación: " + habitacion.getnumHabitacion());
                    System.out.println("Hotel: " + habitacion.getHotel().getNombre());
                }
                System.out.println("Periodo de Reserva: ");
                System.out.println("Desde: " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
                System.out.println("____________________________________________________________");
            }


            System.out.println("Ingrese número de reserva a Cancelar: ");
            int idReserva = Integer.parseInt(scanner.nextLine());
            boolean reservaDeleted = false;

            for (Reserva reserva : reservaList) {
                if (reserva.getIdReserva() == idReserva) {
                    reservaDeleted = this.reservaController.deleteReserva(reserva.getIdReserva());
                    break;
                }
            }

            if (reservaDeleted) {
                System.out.println(verde + "Reserva cancelada.");
            } else {
                System.out.println(rojo + "No se pudo cancelar la Reserva. Vuelva a intentarlo más tarde.");
            }

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

    public void deleteReservaByFecha() {
        try {
            System.out.println(reset + "Periodo de fecha: ");
            System.out.println(reset + "Desde(aaaa-mm-dd): ");
            String fechaInicio = scanner.nextLine();
            System.out.println(reset + "Hasta(aaaa-mm-dd): ");
            String fechaFin = scanner.nextLine();

            List<Reserva> reservaList = this.reservaController.getReservaByFecha(fechaInicio, fechaFin);

            for (Reserva reserva : reservaList) {
                List<Habitacion> habitacionList = this.habitacionController.habitacionPorReserva(reserva.getIdReserva());
                System.out.println(reset + "Número de Reserva: " + reserva.getIdReserva());
                System.out.println("Habitaciones:");
                for (Habitacion habitacion : habitacionList) {
                    System.out.println("Habitación: " + habitacion.getnumHabitacion());
                    System.out.println("Hotel: " + habitacion.getHotel().getNombre());
                }
                System.out.println("Periodo de Reserva: ");
                System.out.println("Desde: " + reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
                System.out.println("____________________________________________________________");
            }

            System.out.println("Ingrese número de reserva a Cancelar: ");
            int idReserva = Integer.parseInt(scanner.nextLine());
            boolean reservaDeleted = false;

            for (Reserva reserva : reservaList) {
                if (reserva.getIdReserva() == idReserva) {
                    this.reservaHabitacionController.deleteReservaHabitacion(reserva.getIdReserva());
                    reservaDeleted = this.reservaController.deleteReserva(reserva.getIdReserva());
                    break;
                }
            }

            if (reservaDeleted) {
                System.out.println(verde + "Reserva cancelada.");
            } else {
                System.out.println(rojo + "No se pudo cancelar la Reserva. Vuelva a intentarlo más tarde.");
            }

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
