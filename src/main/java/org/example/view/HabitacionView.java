package org.example.view;
import org.example.controller.HabitacionController;
import org.example.model.Habitacion;

import java.util.List;
import java.util.Scanner;

public class HabitacionView {
    Scanner scanner = new Scanner(System.in);
    HabitacionController habitacionController = new HabitacionController();


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
