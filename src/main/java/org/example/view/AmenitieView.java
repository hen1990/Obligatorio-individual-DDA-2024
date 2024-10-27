package org.example.view;
import org.example.controller.AmenitieController;
import org.example.model.Amenitie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmenitieView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    Scanner scanner = new Scanner(System.in);
    AmenitieController amenitieController = new AmenitieController();

    public void getAmenitieById() {
        try {
            System.out.println(reset + "Por favor, ingrese el Id del Amenitie: ");
            int idAmenitie = Integer.parseInt(scanner.nextLine());
            Amenitie amenitie = amenitieController.getAmenitieById(idAmenitie);

            if (amenitie == null) {
                System.out.println(rojo + "No se encontró Amenitie.");
            } else {
                System.out.println(amenitie.getNombre());
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getAmenitieById();
        }
    }

    public void getAllAmenitie() {
        System.out.println(reset + "Amenitie");
        List<Amenitie> amenitieList = this.amenitieController.getAllAmenitie();

        for (Amenitie amenitie : amenitieList) {
            System.out.println("- " + amenitie.getNombre());
        }
    }

    public List<Amenitie> seleccionarAmenitie() {
        System.out.println(reset + "Seleccionar Amenitie");
        List<Amenitie> amenitieSeleccionadoList = new ArrayList<>();

        Amenitie amenitie = null;
        boolean salir = false;

        while (!salir) {
            System.out.println(azul + "Ingrese Amenitie: ");
            List<Amenitie> amenitieList = this.amenitieController.getAllAmenitie();
            for (int i = 0; i < amenitieList.size(); i++) {
                System.out.println((i + 1) + " - " + amenitieList.get(i).getNombre());
            }
            System.out.println("0 - Salir");

            int idAmenitie = Integer.parseInt(scanner.nextLine()) - 1;

            if (idAmenitie == 0) {
                salir = true;
            } else {
                for (int i = 0; i < amenitieList.size(); i++) {
                    if (idAmenitie == i) {
                        amenitie = amenitieController.getAmenitieById(amenitieList.get(i).getIdAmenitie());
                        if (amenitie != null) {
                            amenitieSeleccionadoList.add(amenitie);
                        }
                    }
                }
                if (amenitie == null) {
                    System.out.println(rojo + "No se encontró Amenitie.");
                }
            }
        }
        return amenitieSeleccionadoList;
    }
}
