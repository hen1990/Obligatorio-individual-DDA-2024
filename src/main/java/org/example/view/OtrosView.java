package org.example.view;
import org.example.controller.TarifaController;
import org.example.model.Tarifa;
import java.util.List;
import java.util.Scanner;

public class OtrosView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    Scanner scanner = new Scanner(System.in);
    HabitacionView habitacionView = new HabitacionView();
    AmenitieView amenitieView = new AmenitieView();
    TarifaController tarifaController = new TarifaController();
    PaisView paisView = new PaisView();
    CiudadView ciudadView = new CiudadView();

    public void MenuOtros() {

        boolean salir = false;

        while (!salir) {
            System.out.println(azul + "OTROS");
            System.out.println("1 - Buscar Amenitie.");
            System.out.println("2 - Ver Todos los Amenities.");
            System.out.println("3 - Buscar una Tarifa.");
            System.out.println("4 - Ver todas las Tarifas.");
            System.out.println("5 - Buscar Pais.");
            System.out.println("6 - Ver Todos los Paices.");
            System.out.println("7 - Buscar una Ciudad.");
            System.out.println("8 - Ver todas las Ciudades.");
            System.out.println("9 - Gestionar Habitación.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    getAmenitieById();
                    break;
                case "2":
                    getAllAmenitie();
                    break;
                case "3":
                    getTarifaById();
                    break;
                case "4":
                    getAllTarifa();
                    break;
                case "5":
                    getPaisById();
                    break;
                case "6":
                    getAllPais();
                    break;
                case "7":
                    getCiudadById();
                    break;
                case "8":
                    getAllCiudad();
                    break;
                case "9":
                    gestionarHabitacion();
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

    //Amenities_________________________________________________________________________________________________________
    private void getAmenitieById() {
        limpiarConsola();
        this.amenitieView.getAmenitieById();
        esperarEnter();
    }

    private void getAllAmenitie() {
        limpiarConsola();
        this.amenitieView.getAllAmenitie();
        esperarEnter();
    }

    //Tarifas___________________________________________________________________________________________________________
    private void getTarifaById() {
        limpiarConsola();
        System.out.println(azul + "Id de Tarifa");
        int idTarifa = Integer.parseInt(scanner.nextLine());
        Tarifa tarifa = tarifaController.getTarifaById(idTarifa);

        System.out.println(reset + "Fecha Inicio: " + tarifa.getFechaInicio());
        System.out.println("Fecha Fin: " + tarifa.getFechaFin());
        System.out.println("Monto: " + tarifa.getMonto());
        esperarEnter();
    }

    private void getAllTarifa() {
        limpiarConsola();
        List<Tarifa> tarifaList = tarifaController.getAllTarifa();
        for (Tarifa tarifa : tarifaList) {
            System.out.println(reset + "Id Tarifa: " + tarifa.getIdTarifa());
            System.out.println("Fecha Inicio: " + tarifa.getFechaInicio());
            System.out.println("Fecha Fin: " + tarifa.getFechaFin());
            System.out.println("Monto: " + tarifa.getMonto());
            System.out.println("______________________________________________");
        }
        esperarEnter();
    }

    //Pais______________________________________________________________________________________________________________
    private void getAllPais() {
        paisView.getAllPais();
        esperarEnter();
    }

    private void getPaisById() {
        paisView.getPaisById();
        esperarEnter();
    }

    //Ciudad____________________________________________________________________________________________________________
    private void getAllCiudad() {
        ciudadView.getAllCiudad();
        esperarEnter();
    }

    private void getCiudadById() {
        ciudadView.getCiudadById();
        esperarEnter();
    }

    //Habitacion________________________________________________________________________________________________________
    private void gestionarHabitacion() {
        boolean salir = false;

        while (!salir) {
            System.out.println(azul + "Gestionar Habitación:");
            System.out.println("1 - Ingresar una Habitación.");
            System.out.println("2 - Actualizar una Habitación.");

            System.out.println("0 - Salir.");

            String opcion = scanner.nextLine();


            switch (opcion) {
                case "1":
                    habitacionView.ingresarHabitacion();
                    break;
                case "2":
                    //habitacionView.actualizarHabitacion();
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

    //Esperar Enter_____________________________________________________________________________________________________
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
