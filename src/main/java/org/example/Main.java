package org.example;
import org.example.view.*;
import java.util.Scanner;

public class Main {
    public static String rojo = "\u001B[31m";
    public static String azul = "\u001B[34m";
    public static String verde = "\u001B[32m";
    public static String reset = "\u001B[0m";

    public static Scanner scanner = new Scanner(System.in);
    public static HotelView hotelView = new HotelView();
    public static HuespedView huespedView = new HuespedView();
    public static OtrosView otrosView = new OtrosView();
    public static ReservaView reservaView = new ReservaView();
    public static HabitacionView habitacionView = new HabitacionView();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println(azul + "INICIO");
            System.out.println("1 - Gestionar Hotel.");
            System.out.println("2 - Gestionar Huesped.");
            System.out.println("3 - Gestionar Reserva.");
            System.out.println("4 - Gestionar Habitación.");
            System.out.println("-------");
            System.out.println("5 - Otros.");
            System.out.println("0 - Salir." + reset);

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    gestionarHotel();
                    break;
                case "2":
                    gestionarHuesped();
                    break;
                case "3":
                    Reserva();
                    break;
                case "4":
                    Habitacion();
                    break;
                case "5":
                    Otros();
                    break;
                case "0":
                    System.out.println(reset + "Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println(rojo + "Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    //Hotel_____________________________________________________________________________________________________________
    private static void gestionarHotel() {
        limpiarConsola();
        hotelView.hotel();
        limpiarConsola();
    }

    //Huesped___________________________________________________________________________________________________________
    private static void gestionarHuesped() {
        limpiarConsola();
        huespedView.huesped();
        limpiarConsola();
    }

    //Reservas__________________________________________________________________________________________________________
    private static void Reserva() {
        limpiarConsola();
        reservaView.Reserva();
        limpiarConsola();
    }

    //Habitacion________________________________________________________________________________________________________
    private static void Habitacion() {
        limpiarConsola();
        habitacionView.Habitacion();
        limpiarConsola();
    }

    //Otros_____________________________________________________________________________________________________________
    private static void Otros() {
        limpiarConsola();
        otrosView.MenuOtros();
        limpiarConsola();
    }

    //Esperar Enter_____________________________________________________________________________________________________
    public static void esperarEnter() {
        System.out.println(verde + "Presiona Enter para continuar...");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        limpiarConsola();

    }

    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}