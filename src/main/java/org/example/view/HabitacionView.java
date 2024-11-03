package org.example.view;

import org.example.controller.AmenitieController;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.TipoHabitacionController;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HabitacionView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    Scanner scanner = new Scanner(System.in);
    HabitacionController habitacionController = new HabitacionController();
    TipoHabitacionController tipoHabitacionController = new TipoHabitacionController();
    HotelController hotelController = new HotelController();
    HotelView hotelView = new HotelView();
    AmenitieController amenitieController = new AmenitieController();

    public void Habitacion() {
        boolean salir = false;

        while (!salir) {
            System.out.println(azul + "HABITACIÓN:");
            System.out.println("1 - Ingresar una Habitación.");
            System.out.println("2 - Actualizar una Habitación.");
            System.out.println("3 - Eliminar una Habitación.");
            System.out.println("4 - Habitaciones Ocupadas.");
            System.out.println("5 - Habitaciones Desocupadas.");
            System.out.println("6 - Cambiar Estado de Habitaciones.");
            System.out.println("7 - Habitaciones con reservas.");
            System.out.println("8 - Habitaciones con reservas.");
            System.out.println("9 - Ver una Habitación por ID.");
            System.out.println("10 - Ver todas las Habitaciones.");

            System.out.println("0 - Salir.");

            String opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    ingresarHabitacion();
                    esperarEnter();
                    break;
                case "2":
                    actualizarHabitacion();
                    esperarEnter();
                    break;
                case "3":
                    eliminarHabitacion();
                    esperarEnter();
                    break;
                case "4":
                    getHabitacionOcupada();
                    esperarEnter();
                    break;
                case "5":
                    getHabitacionDesocupada();
                    esperarEnter();
                    break;
                case "6":
                    cambiarEstadoHabitacionComo();
                    esperarEnter();
                    break;
                case "7":
                    getHabitacionConReservas();
                    esperarEnter();
                    break;
                case "8":
                    getHabitacionSinReservas();
                    esperarEnter();
                    break;
                case "9":
                    getHabitacionById();
                    esperarEnter();
                    break;
                case "10":
                    getAllHabitacion();
                    esperarEnter();
                    break;
                case "0":
                    System.out.println(verde + "Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println(rojo + "Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    public void ingresarHabitacion() {
        try {
            System.out.println(reset + "Ingrese número de Habitación: ");
            int numHabitacion = Integer.parseInt(scanner.nextLine());
            System.out.println(reset + "Ingrese cantidad de camas individuales: ");
            int camaSimple = Integer.parseInt(scanner.nextLine());
            System.out.println(reset + "Ingrese cantidad de camas Dobles:");
            int camaDoble = Integer.parseInt(scanner.nextLine());

            TipoHabitacion tipoHabitacion = null;
            while (tipoHabitacion == null) {
                System.out.println(reset + "Seleccione Tipo d Habitación: ");
                List<TipoHabitacion> tipoHabitacionList = tipoHabitacionController.getAllTipoHabitacion();
                for (int i = 0; i < tipoHabitacionList.size(); i++) {
                    System.out.println((i + 1) + " - " + tipoHabitacionList.get(i).getTipo());
                    System.out.println("Tarifas disponibles: " + tipoHabitacionList.get(i).getTarifa().getMonto() + ", de " + tipoHabitacionList.get(i).getTarifa().getFechaFin() + " a " + tipoHabitacionList.get(i).getTarifa().getFechaFin());
                    System.out.println("________________________________________________________________________");
                }

                int idTipoHabitacion = Integer.parseInt(scanner.nextLine()) - 1;

                for (int i = 0; i < tipoHabitacionList.size(); i++) {
                    if (idTipoHabitacion == i) {
                        tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);
                    }
                }
                if (tipoHabitacion == null) {
                    System.out.println(rojo + "Tipo de habitación no encontrada.");
                    System.out.println(rojo + "Tipo de Habitación no puede estar vacío.");
                }
            }
            System.out.println(reset + "Seleccione Hotel: ");

            List<Hotel> hotelList = hotelController.getAllHotel();
            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
            }

            int idHotel = Integer.parseInt(scanner.nextLine()) - 1;

            Hotel hotel = null;
            for (int i = 0; i < hotelList.size(); i++) {
                if (idHotel == i) {
                    hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                }
            }

            boolean ocupada = false;

            Habitacion habitacion = new Habitacion(numHabitacion, camaSimple, camaDoble, ocupada, hotel, tipoHabitacion);

            boolean habitacionInserted = this.habitacionController.insertHabitacion(habitacion);

            if (habitacionInserted) {
                System.out.println(verde + "Habitación ingresada! ");
            } else {
                System.out.println(rojo + "Ocurrió un error al ingresar la Habitación, \nvuelva a intentarlo más tarde.");
            }

        } catch (
                Exception e) {
            System.out.println(rojo + "Algo salió mal.");
            System.out.println(azul + "1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    ingresarHabitacion();
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

    public void actualizarHabitacion() {
        try {
            System.out.println(azul + "Buscar Habitación a actualizar:");
            System.out.println(reset + "Elija un Hotel:");
            Hotel hotel = hotelView.getHotel();

            Habitacion habitacion = seleccionarHabitacionByHotel(hotel.getIdHotel());
            boolean salir = false;

            while (!salir) {
                System.out.println(azul + "¿Qué dato desea modificar?" + reset);
                System.out.println("1 - Número de Habitación: " + habitacion.getnumHabitacion());
                System.out.println("2 - Cantidad de Camas Simples: " + habitacion.getCamasSimple());
                System.out.println("3 - Cantidad de Camas Doble: " + habitacion.getCamasDoble());
                System.out.println("4 - Hotel: " + habitacion.getHotel().getNombre());
                System.out.println("5 - Tipo de Habitación/Tarifa: " + habitacion.getTipoHabitacion().getTipo() + ", " + habitacion.getTipoHabitacion().getTarifa().getMonto());
                System.out.println("0 - Salir.");
                String opcion = scanner.nextLine();
                boolean HabitacionActualizada = false;

                switch (opcion) {
                    case "1":
                        System.out.print("Nuevo Número de Habitación: ");
                        int numeroHabitacion = Integer.parseInt(scanner.nextLine());
                        HabitacionActualizada = habitacionController.actualizarNumeroHabitacion(numeroHabitacion, habitacion.getIdHabitacion());
                        if (HabitacionActualizada) habitacion.setnumHabitacion(numeroHabitacion);
                        break;

                    case "2":
                        System.out.print("Nueva Cantidad de Camas Simples: ");
                        int camasSimples = Integer.parseInt(scanner.nextLine());
                        HabitacionActualizada = habitacionController.actualizarCamasSimples(camasSimples, habitacion.getIdHabitacion());
                        if (HabitacionActualizada) habitacion.setCamasSimple(camasSimples);
                        break;

                    case "3":
                        System.out.print("Nueva Cantidad de Camas Doble: ");
                        int camasDobles = Integer.parseInt(scanner.nextLine());
                        HabitacionActualizada = habitacionController.actualizarCamasDobles(camasDobles, habitacion.getIdHabitacion());
                        if (HabitacionActualizada) habitacion.setCamasDoble(camasDobles);
                        break;

                    case "4":
                        System.out.print("Hotel Disponibles: ");
                        List<Hotel> hotelList = hotelController.getAllHotel();
                        for (int i = 0; i < hotelList.size(); i++) {
                            System.out.print((i + 1) + " - " + hotelList.get(i).getNombre());
                            System.out.print(", " + hotelList.get(i).getCiudad().getPais().getNombre());
                            System.out.println(", " + hotelList.get(i).getCiudad().getNombre());
                        }

                        System.out.print("Seleccione Nuevo Hotel: ");
                        int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;

                        for (int i = 0; i < hotelList.size(); i++) {
                            if (indiceHotel == i) {
                                Hotel nuevoHotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                                HabitacionActualizada = habitacionController.actualizarHotel(hotelList.get(i).getIdHotel(), habitacion.getIdHabitacion());
                                if (HabitacionActualizada) habitacion.setHotel(nuevoHotel);
                            }
                        }
                        break;

                    case "5":
                        List<TipoHabitacion> tipoHabitacionList = tipoHabitacionController.getAllTipoHabitacion();

                        for (int i = 0; i < tipoHabitacionList.size(); i++) {
                            System.out.print((i + 1) + " - " + tipoHabitacionList.get(i).getTipo());
                            System.out.println(". Tarifa: " + tipoHabitacionList.get(i).getTarifa().getMonto());
                        }

                        System.out.print("Nuevo Tipo de Habitación/Tarifa: ");
                        int idTipoHabitacion = Integer.parseInt(scanner.nextLine()) - 1;

                        for (int i = 0; i < tipoHabitacionList.size(); i++) {
                            if (idTipoHabitacion == i) {
                                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(tipoHabitacionList.get(i).getIdTipoHabitacion());
                                HabitacionActualizada = habitacionController.actualizarTipoHabitacion(tipoHabitacionList.get(i).getIdTipoHabitacion(), habitacion.getIdHabitacion());
                                if (HabitacionActualizada) habitacion.setTipoHabitacion(tipoHabitacion);
                            }
                        }
                        break;

                    case "0":
                        salir = true;
                        break;

                    default:
                        System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
                        break;

                }
                if (!salir && HabitacionActualizada) {
                    System.out.println(verde + "Dato actualizado.\n");
                } else if (!salir) {
                    System.out.println(rojo + "No se pudo actualizar los datos.");
                }
            }
            if (habitacion == null) {
                System.out.println(rojo + "No se encontró Habitación.");
                System.out.println(azul + "1 - Volver a buscar.");
                System.out.println("0 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        actualizarHabitacion();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.actualizarHabitacion();
        }
    }

    public void cambiarEstadoHabitacionComo() {
        try {
            System.out.println(azul + "Buscar Habitación a actualizar:");
            System.out.println(reset + "Elija un Hotel:");
            Hotel hotel = hotelView.getHotel();

            Habitacion habitacion = seleccionarHabitacionByHotel(hotel.getIdHotel());

            System.out.println("Habitación Nº" + habitacion.getnumHabitacion());
            System.out.print("Estado actual: ");
            if (habitacion.isOcupada()) {
                System.out.println("Ocupada");
            } else {
                System.out.println("Disponible");
            }

            System.out.print("Cambiar estado a: ");
            if (habitacion.isOcupada()) {
                System.out.println("Disponible? (y/n): º");
            } else {
                System.out.println("Ocupada? (y/n): ");
            }

            String opcion = scanner.nextLine();

            boolean salir = false;
            while (!salir) {
                switch (opcion) {
                    case "y":
                        boolean actualizarDisponibilidad = habitacionController.actualizarDisponibilidad(!habitacion.isOcupada(), habitacion.getIdHabitacion());
                        if (actualizarDisponibilidad) {
                            System.out.println(verde + "Dato actualizado.");
                        } else {
                            System.out.println(rojo + "No se pudo actualizar.");
                        }
                        salir = true;
                        break;
                    case "n":
                        salir = true;
                        break;
                    default:
                        System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
                        break;
                }
            }


        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.cambiarEstadoHabitacionComo();
        }

    }

    public void getHabitacionConReservas() {

    }

    public void getHabitacionSinReservas() {

    }

    public void getHabitacionById() {
        try {
            System.out.println(reset + "Por favor, ingrese el Número de Habitación: ");
            int idHabitacion = Integer.parseInt(scanner.nextLine());
            Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);

            if (habitacion == null) {
                System.out.println(rojo + "No se encontró Habitacion.");
            } else {
                System.out.println(verde + "Habitación " + habitacion.getTipoHabitacion().getTipo() + ", Nº: " + habitacion.getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + habitacion.getCamasDoble());
                System.out.println("Camas Simple: " + habitacion.getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacion.getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + habitacion.getTipoHabitacion().getTarifa().getMonto());
                if (habitacion.isOcupada()) {
                    System.out.println(rojo + "OCUPADA");
                } else {
                    System.out.println(verde + "DISPONIBLE");
                }
                System.out.println(reset + "_______________________________________________________________");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getHabitacionById();
        }
    }

    public void getHabitacionOcupada() {
        System.out.println(reset + "Habitaciones Ocupadas");
        List<Habitacion> habitacionList = this.habitacionController.getAllHabitacionOcupadas(true);

        if (!habitacionList.isEmpty()) {
            for (Habitacion habitacion : habitacionList) {
                System.out.println(verde + "Hotel: " + habitacion.getHotel().getNombre() + ", " + habitacion.getHotel().getCiudad().getNombre());
                System.out.println(verde + "Habitación " + habitacion.getTipoHabitacion().getTipo() + ", Nº: " + habitacion.getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + habitacion.getCamasDoble());
                System.out.println("Camas Simple: " + habitacion.getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacion.getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + habitacion.getTipoHabitacion().getTarifa().getMonto());
                if (habitacion.isOcupada()) {
                    System.out.println(rojo + "OCUPADA");
                } else {
                    System.out.println(verde + "DISPONIBLE");
                }
                System.out.println(reset + "_______________________________________________________________");
            }
        }
    }

    public void getHabitacionDesocupada() {
        System.out.println(reset + "Habitaciones Desocupadas");
        List<Habitacion> habitacionList = this.habitacionController.getAllHabitacionOcupadas(false);

        if (!habitacionList.isEmpty()) {
            for (Habitacion habitacion : habitacionList) {
                System.out.println(verde + "Habitación " + habitacion.getTipoHabitacion().getTipo() + ", Nº: " + habitacion.getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + habitacion.getCamasDoble());
                System.out.println("Camas Simple: " + habitacion.getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacion.getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + habitacion.getTipoHabitacion().getTarifa().getMonto());
                if (habitacion.isOcupada()) {
                    System.out.println(rojo + "OCUPADA");
                } else {
                    System.out.println(verde + "DISPONIBLE");
                }
                System.out.println(reset + "_______________________________________________________________");
            }
        }
    }

    public void getAllHabitacion() {
        System.out.println(reset + "Habitaciones");
        List<Habitacion> habitacionList = this.habitacionController.getAllHabitacion();

        if (!habitacionList.isEmpty()) {
            for (Habitacion habitacion : habitacionList) {
                System.out.println(verde + "Habitación " + habitacion.getTipoHabitacion().getTipo() + ", Nº: " + habitacion.getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + habitacion.getCamasDoble());
                System.out.println("Camas Simple: " + habitacion.getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacion.getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + habitacion.getTipoHabitacion().getTarifa().getMonto());
                if (habitacion.isOcupada()) {
                    System.out.println(rojo + "OCUPADA");
                } else {
                    System.out.println(verde + "DISPONIBLE");
                }
                System.out.println(reset + "_______________________________________________________________");
            }
        }
    }

    public Habitacion seleccionarHabitacionByHotel(int idHotel) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorHotel(idHotel);

        Habitacion habitacion = null;
        boolean habitacionExiste = false;

        while (!habitacionExiste) {
            for (Habitacion value : habitacionList) {
                System.out.println(verde + "Habitación " + value.getTipoHabitacion().getTipo() + ", Nº: " + value.getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + value.getCamasDoble());
                System.out.println("Camas Simple: " + value.getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : value.getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + value.getTipoHabitacion().getTarifa().getMonto());
                if (value.isOcupada()) {
                    System.out.println(rojo + "OCUPADA");
                } else {
                    System.out.println(verde + "DISPONIBLE");
                }


                System.out.println(reset + "_______________________________________________________________");
            }

            System.out.println(reset + "Ingrese Número de Habitación: ");
            int numeroHabitacion = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < habitacionList.size(); i++) {
                if (numeroHabitacion == habitacionList.get(i).getnumHabitacion()) {
                    habitacion = habitacionController.getHabitacionById(habitacionList.get(i).getIdHabitacion());
                    if (habitacion != null) {
                        habitacionExiste = true;
                    }
                }
            }

            if (!habitacionExiste) {
                System.out.println(rojo + "No se encontró Habitación.");
                System.out.println(azul + "1 - Volver a buscar.");
                System.out.println("2 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        break;
                    case "2":
                        habitacionExiste = true;
                        break;
                    default:
                        System.out.println(rojo + "Opción no válida, ingrese una opción válida.");
                        break;
                }
            }
        }
        return habitacion;
    }

    public List<Habitacion> seleccionarHabitacionByHotelFecha(int idHotel, String fechaInicio, String fechaFin) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorHotelFecha(idHotel, fechaInicio, fechaFin);
        List<Habitacion> habitacionListSeleccionadas = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            Habitacion habitacion = null;
            boolean habitacionExiste = false;

            while (!habitacionExiste) {
                for (Habitacion value : habitacionList) {
                    System.out.println(verde + "Habitación " + value.getTipoHabitacion().getTipo() + ", Nº: " + value.getnumHabitacion());
                    System.out.println(reset + "Camas Doble: " + value.getCamasDoble());
                    System.out.println("Camas Simple: " + value.getCamasSimple());
                    System.out.println("Amenities: ");
                    for (Amenitie amenitie : value.getAmenitieList()) {
                        System.out.println(verde + " - " + reset + amenitie.getNombre());
                    }
                    System.out.println(reset + "Precio: $" + value.getTipoHabitacion().getTarifa().getMonto());
                    if (habitacion != null) {
                        if (habitacion.isOcupada()) {
                            System.out.println(rojo + "OCUPADA");
                        } else {
                            System.out.println(verde + "DISPONIBLE");
                        }
                    }
                    System.out.println(reset + "_______________________________________________________________");
                }

                System.out.println(reset + "Ingrese Número de Habitación, ingrese 0 para volver: ");
                int numeroHabitacion = Integer.parseInt(scanner.nextLine());

                if (numeroHabitacion == 0) {
                    habitacionExiste = true;
                    salir = true;
                } else {
                    for (int i = 0; i < habitacionList.size(); i++) {
                        if (numeroHabitacion == habitacionList.get(i).getnumHabitacion()) {
                            habitacion = habitacionController.getHabitacionById(habitacionList.get(i).getIdHabitacion());
                            if (habitacion != null) {
                                habitacionListSeleccionadas.add(habitacion);
                                Habitacion finalHabitacion = habitacion;
                                habitacionList.removeIf(h -> h.getIdHabitacion() == finalHabitacion.getIdHabitacion());
                                habitacionExiste = true;
                            }
                        }
                    }

                    if (!habitacionExiste) {
                        System.out.println(rojo + "No se encontró Habitación.");
                        System.out.println(azul + "1 - Volver a buscar.");
                        System.out.println("2 - Salir.");
                        String opcion = scanner.nextLine();

                        switch (opcion) {
                            case "1":
                                break;
                            case "2":
                                habitacionExiste = true;
                                break;
                            default:
                                System.out.println(rojo + "Opción no válida, ingrese una opción válida.");
                                break;
                        }
                    } else {
                        System.out.println(verde + "Habitación agregada.");
                        System.out.println(reset + "Selecciona otra habitación, digita 0 para volver.");
                    }
                }
            }
        }
        return habitacionListSeleccionadas;
    }

    public List<Habitacion> habitacionDisponiblePorCiudad(int idCiudad, String fechaInicio, String fechaFin) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorCiudad(idCiudad, fechaInicio, fechaFin);
        List<Habitacion> habitacionListSeleccionadas = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            Habitacion habitacion = null;
            boolean habitacionExiste = false;

            while (!habitacionExiste) {
                for (Habitacion value : habitacionList) {
                    System.out.println(verde + "Habitación " + value.getTipoHabitacion().getTipo() + ", Nº: " + value.getnumHabitacion());
                    System.out.println(reset + "Camas Doble: " + value.getCamasDoble());
                    System.out.println("Camas Simple: " + value.getCamasSimple());
                    System.out.println("Amenities: ");
                    for (Amenitie amenitie : value.getAmenitieList()) {
                        System.out.println(verde + " - " + reset + amenitie.getNombre());
                    }
                    System.out.println(reset + "Precio: $" + value.getTipoHabitacion().getTarifa().getMonto());
                    if (value.isOcupada()) {
                        System.out.println(rojo + "OCUPADA");
                    } else {
                        System.out.println(verde + "DISPONIBLE");
                    }
                    System.out.println(reset + "_______________________________________________________________");
                }

                System.out.println(reset + "Ingrese Número de Habitación, ingrese 0 para volver: ");
                int numeroHabitacion = Integer.parseInt(scanner.nextLine());

                if (numeroHabitacion == 0) {
                    habitacionExiste = true;
                    salir = true;
                } else {
                    for (int i = 0; i < habitacionList.size(); i++) {
                        if (numeroHabitacion == habitacionList.get(i).getnumHabitacion()) {
                            habitacion = habitacionController.getHabitacionById(habitacionList.get(i).getIdHabitacion());
                            if (habitacion != null) {
                                habitacionListSeleccionadas.add(habitacion);
                                habitacionExiste = true;
                            }
                        }
                    }

                    if (!habitacionExiste) {
                        System.out.println(rojo + "No se encontró Habitación.");
                        System.out.println(azul + "1 - Volver a buscar.");
                        System.out.println("2 - Salir.");
                        String opcion = scanner.nextLine();

                        switch (opcion) {
                            case "1":
                                break;
                            case "2":
                                habitacionExiste = true;
                                break;
                            default:
                                System.out.println(rojo + "Opción no válida, ingrese una opción válida.");
                                break;
                        }
                    } else {
                        System.out.println(verde + "Habitación agregada.");
                        System.out.println(reset + "Selecciona otra habitación, digita 0 para volver.");
                    }
                }
            }
        }
        return habitacionListSeleccionadas;
    }

    public List<Habitacion> seleccionarHabitacionByFecha(String fechaInicio, String fechaFin) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorFecha(fechaInicio, fechaFin);
        List<Habitacion> habitacionListSeleccionadas = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            Habitacion habitacion = null;
            boolean habitacionExiste = false;

            while (!habitacionExiste) {
                for (Habitacion value : habitacionList) {
                    System.out.println(verde + "Habitación " + value.getTipoHabitacion().getTipo() + ", Nº: " + value.getnumHabitacion());
                    System.out.println(verde + "Hotel: " + value.getHotel().getNombre());
                    System.out.println(reset + "Camas Doble: " + value.getCamasDoble());
                    System.out.println("Camas Simple: " + value.getCamasSimple());
                    System.out.println("Amenities: ");
                    for (Amenitie amenitie : value.getAmenitieList()) {
                        System.out.println(verde + " - " + reset + amenitie.getNombre());
                    }
                    System.out.println(reset + "Precio: $" + value.getTipoHabitacion().getTarifa().getMonto());
                    if (habitacion != null) {
                        if (habitacion.isOcupada()) {
                            System.out.println(rojo + "OCUPADA");
                        } else {
                            System.out.println(verde + "DISPONIBLE");
                        }
                    }
                    System.out.println(reset + "_______________________________________________________________");
                }

                System.out.println(reset + "Ingrese Número de Habitación, ingrese 0 para volver: ");
                int numeroHabitacion = Integer.parseInt(scanner.nextLine());

                if (numeroHabitacion == 0) {
                    habitacionExiste = true;
                    salir = true;
                } else {
                    for (int i = 0; i < habitacionList.size(); i++) {
                        if (numeroHabitacion == habitacionList.get(i).getnumHabitacion()) {
                            habitacion = habitacionController.getHabitacionById(habitacionList.get(i).getIdHabitacion());
                            if (habitacion != null) {
                                habitacionListSeleccionadas.add(habitacion);
                                Habitacion finalHabitacion = habitacion;
                                habitacionList.removeIf(h -> h.getIdHabitacion() == finalHabitacion.getIdHabitacion());
                                habitacionExiste = true;
                            }
                        }
                    }

                    if (!habitacionExiste) {
                        System.out.println(rojo + "No se encontró Habitación.");
                        System.out.println(azul + "1 - Volver a buscar.");
                        System.out.println("2 - Salir.");
                        String opcion = scanner.nextLine();

                        switch (opcion) {
                            case "1":
                                break;
                            case "2":
                                habitacionExiste = true;
                                break;
                            default:
                                System.out.println(rojo + "Opción no válida, ingrese una opción válida.");
                                break;
                        }
                    } else {
                        System.out.println(verde + "Habitación agregada.");
                        System.out.println(reset + "Selecciona otra habitación, digita 0 para volver.");
                    }
                }
            }
        }
        return habitacionListSeleccionadas;
    }

    public void eliminarHabitacion() {

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
