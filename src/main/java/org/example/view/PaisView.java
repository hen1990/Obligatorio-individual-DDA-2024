package org.example.view;
import org.example.controller.PaisController;
import org.example.model.Pais;
import java.util.List;
import java.util.Scanner;

public class PaisView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    Scanner scanner = new Scanner(System.in);
    PaisController paisController = new PaisController();

    public void getPaisById() {
        try {
            System.out.println(azul + "Por favor, ingrese el Id del Pais: " + reset);
            int opcion = Integer.parseInt(scanner.nextLine());
            Pais pais = paisController.getPaisById(opcion);

            if (pais == null) {
                System.out.println(rojo + "No se encontró pais.");
            } else {
                System.out.println(reset + " - " + pais.getNombre());
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getPaisById();
        }
    }

    public void getAllPais(){
        System.out.println(azul + "PAICES");
        List<Pais> paisList = this.paisController.getAllPais();

        for (Pais p : paisList) {
            System.out.println(reset +  " - " + p.getNombre());
        }
    }
}
