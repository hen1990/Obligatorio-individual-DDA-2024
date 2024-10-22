package org.example.view;
import org.example.controller.CiudadController;
import org.example.model.Ciudad;
import java.util.List;
import java.util.Scanner;

public class CiudadView {
    Scanner scanner = new Scanner(System.in);
    CiudadController ciudadController = new CiudadController();

    public void getCiudadById() {
        try {
            System.out.println("Por favor, ingrese el Id de la Ciudad: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            Ciudad ciudad = ciudadController.getCiudadById(opcion);

            if (ciudad == null) {
                System.out.println("No se encontró ciudad.");
            } else {
                System.out.println(" *" + ciudad.getNombre() + "*");
            }
        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.getCiudadById();
        }
    }

    public void getAllCiudad() {
        System.out.println("CIUDADES");
        List<Ciudad> ciudadList = this.ciudadController.getAllCiudad();

        for (Ciudad c : ciudadList) {
            System.out.println("- " + c.getNombre());
        }
    }

    public Ciudad seleccionarCiudad() {
        System.out.println("CIUDADES");
        Ciudad ciudad = null;
        boolean ciudadExiste = false;

        while (!ciudadExiste) {
            System.out.println("Ingrese Ciudad: ");
            List<Ciudad> ciudadList = this.ciudadController.getAllCiudad();
            for (int i = 0; i < ciudadList.size(); i++) {
                System.out.println((i + 1) + " - " + ciudadList.get(i).getNombre());
            }

            int idCiudad = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < ciudadList.size(); i++) {
                if (idCiudad == i) {
                    ciudad = ciudadController.getCiudadById(ciudadList.get(i).getIdCiudad());
                    if (ciudad != null) {
                        ciudadExiste = true;
                    }
                }
            }
            if (!ciudadExiste) {
                System.out.println("No se encontró ciudad.");
                System.out.println("1 - Volver a buscar.");
                System.out.println("2 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        break;
                    case "2":
                        ciudadExiste = true;
                        break;
                    default:
                        System.out.println("Opción no válida, ingrese una opción válida.");
                        break;
                }
            }
        }
        return ciudad;
    }
}
