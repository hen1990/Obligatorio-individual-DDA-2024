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
    Scanner scanner = new Scanner(System.in);
    HabitacionController habitacionController = new HabitacionController();
    TipoHabitacionController tipoHabitacionController = new TipoHabitacionController();
    HotelController hotelController = new HotelController();
    AmenitieController amenitieController = new AmenitieController();

    public void ingresarHabitacion() {
        System.out.println("Ingrese número de Habitación: ");
        int numHabitacion = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese cantidad de camas individuales: ");
        int camaSimple = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese cantidad de camas Dobles:");
        int camaDoble = Integer.parseInt(scanner.nextLine());
        System.out.println("Seleccione Tipo d Habitación: ");

        List<TipoHabitacion> tipoHabitacionList = tipoHabitacionController.getAllTipoHabitacion();
        for (int i = 0; i < tipoHabitacionList.size(); i++) {
            System.out.println((i+1) + tipoHabitacionList.get(i).getTipo());
        }

        int idTipoHabitacion = Integer.parseInt(scanner.nextLine());
        TipoHabitacion tipoHabitacion = null;

        for (int i = 0; i < tipoHabitacionList.size(); i++) {
            if (idTipoHabitacion == i) {
                tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHabitacion);
            }
        }

        System.out.println("Seleccione Hotel: ");

        List<Hotel> hotelList = hotelController.getAllHotel();
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i+1) + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
        }

        int idHotel = Integer.parseInt(scanner.nextLine()) -1;

        Hotel hotel = null;
        for (int i = 0; i < hotelList.size(); i++) {
            if (idHotel == i) {
                hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
            }
        }

        System.out.println("Seleccione Amenities: ");
        List<Amenitie> amenitieList = amenitieController.getAllAmenitie();

    }

    public void getHabitacionById() {
        try {
            System.out.println("Por favor, ingrese el Número de Habitación: ");
            int idHabitacion = Integer.parseInt(scanner.nextLine());
            Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);

            if (habitacion == null) {
                System.out.println("No se encontró Habitacion.");
            } else {
                System.out.println("Nº de Habitación: " + habitacion.getIdHabitacion());
                System.out.println(habitacion.getTipoHabitacion().getTipo());
                System.out.println(habitacion.getTipoHabitacion().getTarifa().getMonto());
            }
        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.getHabitacionById();
        }
    }

    public void getAllHabitacion() {
        System.out.println("Habitaciones");
        List<Habitacion> habitacionList = this.habitacionController.getAllHabitacion();

        for (Habitacion habitacion : habitacionList) {
            System.out.println("Nº de Habitación: " + habitacion.getIdHabitacion());
            System.out.println(habitacion.getTipoHabitacion().getTipo());
            System.out.println(habitacion.getTipoHabitacion().getTarifa().getMonto());
        }
    }

    public Habitacion seleccionarHabitacion() {
        System.out.println("Habitaciones");
        Habitacion habitacion = null;
        boolean habitacionExiste = false;

        while (!habitacionExiste) {
            System.out.println("Ingrese Ciudad: ");
            List<Habitacion> habitacionList = this.habitacionController.getAllHabitacion();
            for (int i = 0; i < habitacionList.size(); i++) {
                System.out.println("Nº de Habitación: " + habitacionList.get(i).getIdHabitacion());
                System.out.println(habitacionList.get(i).getTipoHabitacion().getTipo());
                System.out.println(habitacionList.get(i).getTipoHabitacion().getTarifa().getMonto());
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
                System.out.println("No se encontró Habitación.");
                System.out.println("1 - Volver a buscar.");
                System.out.println("2 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        break;
                    case "2":
                        habitacionExiste = true;
                        break;
                    default:
                        System.out.println("Opción no válida, ingrese una opción válida.");
                        break;
                }
            }
        }
        return habitacion;
    }
}
