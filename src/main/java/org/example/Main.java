package org.example;
import org.example.view.CiudadView;
import org.example.view.HotelView;
import org.example.view.HuespedView;
import org.example.view.PaisView;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static PaisView paisView = new PaisView();
    public static CiudadView ciudadView = new CiudadView();
    public static HotelView hotelView = new HotelView();
    public static HuespedView huespedView = new HuespedView();

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {
            System.out.println("INICIO");
            System.out.println("1 - Gestionar Hotel.");
            System.out.println("2 - Gestionar Huesped.");
            System.out.println("3 - Modificar un Hotel.");

            System.out.println("11 - Ver Paices.");
            System.out.println("12 - Ver un Pais.");
            System.out.println("13 - Ver Ciudades.");
            System.out.println("14 - Ver una Ciudad.");

            System.out.println("0 - Salir.");

            String opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    gestionarHotel();
                    break;
                case "2":
                    gestionarHuesped();
                    break;
                case "3":

                    break;
                case "11":
                    getAllPais();
                    break;
                case "12":
                    getPaisById();
                    break;
                case "13":
                    getAllCiudad();
                    break;
                case "14":
                    getCiudadById();
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    //Pais_____________________________________________________________________________________________________
    private static void getAllPais() {
        paisView.getAllPais();
        esperarEnter();
    }

    private static void getPaisById() {
        paisView.getPaisById();
        esperarEnter();
    }

    //Ciudad_____________________________________________________________________________________________________
    private static void getAllCiudad() {
        ciudadView.getAllCiudad();
        esperarEnter();
    }

    private static void getCiudadById() {
        ciudadView.getCiudadById();
        esperarEnter();
    }

    //Hotel_____________________________________________________________________________________________________
    private static void gestionarHotel() {
        limpiarConsola();
        hotelView.hotel();
        esperarEnter();
    }

    //Hotel_____________________________________________________________________________________________________
    private static void gestionarHuesped() {
        limpiarConsola();
        huespedView.huesped();
        esperarEnter();
    }

    //Esperar Enter_____________________________________________________________________________________________________
    public static void esperarEnter() {
        System.out.println("Presiona Enter para continuar...");

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