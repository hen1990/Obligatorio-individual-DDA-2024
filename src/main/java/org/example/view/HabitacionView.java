package org.example.view;
import org.example.controller.AmenitieController;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.TipoHabitacionController;
import org.example.model.Amenitie;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.TipoHabitacion;

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
    AmenitieController amenitieController = new AmenitieController();


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
            }

            int idTipoHabitacion = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < tipoHabitacionList.size(); i++) {
                if (idTipoHabitacion == i) {
                    tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);
                }
            }
            if (tipoHabitacion == null) {
                System.out.println(rojo + "Tipo de habitación no encontrada.");
                System.out.println(rojo +"Tipo de Habitación no puede estar vacío.");
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

    public void getHabitacionById() {
        try {
            System.out.println(reset + "Por favor, ingrese el Número de Habitación: ");
            int idHabitacion = Integer.parseInt(scanner.nextLine());
            Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);

            if (habitacion == null) {
                System.out.println(rojo + "No se encontró Habitacion.");
            } else {
                System.out.println(reset + "Nº de Habitación: " + habitacion.getIdHabitacion());
                System.out.println(habitacion.getTipoHabitacion().getTipo());
                System.out.println(habitacion.getTipoHabitacion().getTarifa().getMonto());
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getHabitacionById();
        }
    }

    public void getAllHabitacion() {
        System.out.println(reset + "Habitaciones");
        List<Habitacion> habitacionList = this.habitacionController.getAllHabitacion();

        for (Habitacion habitacion : habitacionList) {
            System.out.println(reset + "Nº de Habitación: " + habitacion.getIdHabitacion());
            System.out.println(habitacion.getTipoHabitacion().getTipo());
            System.out.println(habitacion.getTipoHabitacion().getTarifa().getMonto());
        }
    }

    public Habitacion seleccionarHabitacionByHotel(int idHotel, String fechaInicio, String fechaFin) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorHotel(idHotel, fechaInicio, fechaFin);

        Habitacion habitacion = null;
        boolean habitacionExiste = false;

        while (!habitacionExiste) {
            for (int i = 0; i < habitacionList.size(); i++) {
                System.out.println(verde + "Habitación " + habitacionList.get(i).getTipoHabitacion().getTipo() + ", Nº: " + habitacionList.get(i).getnumHabitacion());
                System.out.println(reset + "Camas Doble: " + habitacionList.get(i).getCamasDoble());
                System.out.println("Camas Simple: " + habitacionList.get(i).getCamasSimple());
                System.out.println("Amenities: ");
                for (Amenitie amenitie : habitacionList.get(i).getAmenitieList()) {
                    System.out.println(verde + " - " + reset + amenitie.getNombre());
                }
                System.out.println(reset + "Precio: $" + habitacionList.get(i).getTipoHabitacion().getTarifa().getMonto());
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

    public Habitacion habitacionDisponiblePorCiudad(int idCiudad, String fechaInicio, String fechaFin) {
        System.out.println(reset + "Habitaciones Disponibles: ");

        List<Habitacion> habitacionList = habitacionController.habitacionDisponiblePorCiudad(idCiudad, fechaInicio, fechaFin);

        Habitacion habitacion = null;
        boolean habitacionExiste = false;

        while (!habitacionExiste) {
            for (int i = 0; i < habitacionList.size(); i++) {
                System.out.println(reset + "Nº de Habitación: " + habitacionList.get(i).getIdHabitacion());
                System.out.println(habitacionList.get(i).getTipoHabitacion().getTipo());
                System.out.println(habitacionList.get(i).getTipoHabitacion().getTarifa().getMonto());
                System.out.println(reset + "_______________________________________________________________");
            }

            int idHabitacion = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < habitacionList.size(); i++) {
                if (idHabitacion == i) {
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
}
