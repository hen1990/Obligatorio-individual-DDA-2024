package org.example.view;

import org.example.controller.PaisController;
import org.example.model.Pais;
import java.util.List;
import java.util.Scanner;

public class PaisView {
    Scanner scanner = new Scanner(System.in);
    PaisController paisController = new PaisController();

    public void getPaisById() {
        try {
            System.out.println("Por favor, ingrese el Id del Pais: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            Pais pais = paisController.getPaisById(opcion);

            if (pais == null) {
                System.out.println("No se encontró pais.");
            } else {
                System.out.println(" *" + pais.getNombre() + "*");
            }
        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.getPaisById();
        }
    }

    public void getAllPais(){
        System.out.println("PAICES");
        List<Pais> paisList = this.paisController.getAllPais();

        for (Pais p : paisList) {
            System.out.println("- " + p.getNombre());
        }
    }
}
