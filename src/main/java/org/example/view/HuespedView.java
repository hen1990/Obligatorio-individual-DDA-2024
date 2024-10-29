package org.example.view;
import org.example.controller.HuespedController;
import org.example.controller.PaisController;
import org.example.controller.TipoDocumentoController;
import org.example.model.*;
import java.util.List;
import java.util.Scanner;

public class HuespedView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";
    private final Scanner scanner = new Scanner(System.in);
    private final PaisController paisController = new PaisController();
    private final TipoDocumentoController tipoDocumentoController = new TipoDocumentoController();
    private final HuespedController huespedController = new HuespedController();

    public void huesped() {
        boolean volver = false;

        while (!volver) {
            System.out.println(azul + "MENU HUESPED");
            System.out.println("1 - Ingresar un Huesped.");
            System.out.println("2 - Eliminar un Huesped.");
            System.out.println("3 - Modificar un Huesped.");
            System.out.println("4 - Ver un Huesped.");
            System.out.println("5 - Ver Todos los Huespedes.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertHuesped();
                    esperarEnter();
                    break;
                case "2":
                    deleteHuespedById();
                    esperarEnter();
                    break;
                case "3":
                    actualizarHuesped();
                    esperarEnter();
                    break;
                case "4":
                    getHuesped();
                    esperarEnter();
                    break;
                case "5":
                    getAllHuesped();
                    esperarEnter();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println(rojo + "Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    public void insertHuesped() {
        try {
            System.out.println(reset + "Ingrese los datos del Huesped a continuación:");
            System.out.print("Ingrese el Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese apellido Paterno: ");
            String apPaterno = scanner.nextLine();
            System.out.print("Ingrese apellido Materno: ");
            String apMaterno = scanner.nextLine();
            System.out.print("Ingrese número de Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Ingrese fecha de Nacimiento(aaaa-mm-dd): ");
            String fechaNac = scanner.nextLine();
            System.out.print("Ingrese número de Documento: ");
            String numeroDocumento = scanner.nextLine();

            TipoDocumento tipoDocumento = null;
            boolean tipoDocumentoExiste = false;

            while (!tipoDocumentoExiste) {
                System.out.println(reset + "Ingrese tipo de documento: ");
                List<TipoDocumento> tipoDocumentoList = this.tipoDocumentoController.getAllTipoDocumento();;
                for (int i = 0; i < tipoDocumentoList.size(); i++) {
                    System.out.println((i + 1) + " - " + tipoDocumentoList.get(i).getNombre());
                }

                int idTipoDocumento = Integer.parseInt(scanner.nextLine()) - 1;

                for (int i = 0; i < tipoDocumentoList.size(); i++) {
                    if (idTipoDocumento == i) {
                        tipoDocumento = tipoDocumentoController.getTipoDocumentoById(tipoDocumentoList.get(i).getIdTipoDocumento());
                        if (tipoDocumento != null) {
                            tipoDocumentoExiste = true;
                        }
                    }
                }
                if (!tipoDocumentoExiste) {
                    System.out.println(rojo + "No se encontró Tipo de Documento.");
                    System.out.println(azul + "1 - Volver a buscar.");
                    System.out.println("2 - Continual sin Tipo de Documento.");
                    String opcion = scanner.nextLine();

                    switch (opcion) {
                        case "1":
                            break;
                        case "2":
                            tipoDocumentoExiste = true;
                            break;
                        default:
                            System.out.println("Opción no válida, ingrese una opción válida.");
                            break;
                    }
                }
            }

            Pais pais = null;
            boolean paisExiste = false;

            while (!paisExiste) {
                System.out.println(reset + "Ingrese Pais: ");
                List<Pais> paisList = this.paisController.getAllPais();;
                for (int i = 0; i < paisList.size(); i++) {
                    System.out.println((i + 1) + " - " + paisList.get(i).getNombre());
                }

                int idPais = Integer.parseInt(scanner.nextLine()) - 1;

                for (int i = 0; i < paisList.size(); i++) {
                    if (idPais == i) {
                        pais = paisController.getPaisById(paisList.get(i).getIdPais());
                        if (pais != null) {
                            paisExiste = true;
                        }
                    }
                }
                if (!paisExiste) {
                    System.out.println(rojo + "No se encontró País.");
                    System.out.println(azul + "1 - Volver a buscar.");
                    System.out.println("2 - Continual sin País.");
                    String opcion = scanner.nextLine();

                    switch (opcion) {
                        case "1":
                            break;
                        case "2":
                            paisExiste = true;
                            break;
                        default:
                            System.out.println(rojo + "Opción no válida, ingrese una opción válida.");
                            break;
                    }
                }
            }

            Huesped huesped = new Huesped(nombre, apPaterno, apMaterno, numeroDocumento, fechaNac, telefono, pais, tipoDocumento);
            boolean HuespedInserted = this.huespedController.insertHuesped(huesped);

            if (HuespedInserted) {
                System.out.println(verde + "Huesped ingresado!");
            } else {
                System.out.println(rojo + "Ocurrió un error al ingresar el Huesped, \nvuelva a intentarlo más tarde.");
            }

        } catch (
                Exception e) {
            System.out.println(rojo + "Opción no válida.");
            System.out.println(azul + "1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertHuesped();
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

    public  void getHuesped() {
        System.out.println(azul + "BUSCAR HUESPED");
        System.out.println("1 - Buscar Huesped por ID.");
        System.out.println("2 - Buscar Huesped por Nombre.");
        System.out.println("0 - Volver.");

        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                getHuespedById();
                break;
            case "2":
                getHuespedByNombre();
                break;
            case "0":
                break;
            default:
                System.out.println(rojo + "Opción lo valida.");
                esperarEnter();
                break;
        }
    }
    public void getHuespedById() {
        try {
            System.out.println(reset + "Por favor, ingrese el Id del Huesped: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            Huesped huesped = huespedController.getHuespedById(opcion);

            if (huesped == null) {
                System.out.println(rojo + "No se encontró Huesped.");
            } else {
                System.out.println(reset + "Nombre Completo: " + huesped.getNombre() + " " + huesped.getApPaterno() + " " + huesped.getApMaterno());
                System.out.println(huesped.getTipoDocumento().getNombre() + ": " + huesped.getNumDocumento());
                System.out.println("Teléfono: " + huesped.getTelefono());
                System.out.println("Fecha de nacimiento: " + huesped.getFechaNacimiento());
                System.out.println("País: " + huesped.getPais().getNombre());
                System.out.println("__________________________________________");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getHuespedById();
        }
    }

    public void getHuespedByNombre() {
        try {
            System.out.println(reset + "Por favor, ingrese Nombre o Apellido del Huesped: ");
            String opcion = scanner.nextLine();
            String nombreABuscar = "%" + opcion + "%";
            List<Huesped>  huespedList = huespedController.getHuespedByNombre(nombreABuscar);

            if (huespedList.isEmpty()) {
                System.out.println(rojo + "No se encontró Huesped.");
            } else {
                System.out.println("Huespedes Encontrados: ");

                for (Huesped huesped : huespedList) {
                    System.out.println(reset + "Nombre Completo: " + huesped.getNombre() + " " + huesped.getApPaterno() + " " + huesped.getApMaterno());
                    System.out.println(huesped.getTipoDocumento().getNombre() + ": " + huesped.getNumDocumento());
                    System.out.println("Teléfono: " + huesped.getTelefono());
                    System.out.println("Fecha de nacimiento: " + huesped.getFechaNacimiento());
                    System.out.println("País: " + huesped.getPais().getNombre());
                    System.out.println("__________________________________________");
                }
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getHuespedById();
        }
    }

    public void getAllHuesped() {
        List<Huesped> huespedList = this.huespedController.getAllHuesped();

        for (Huesped h : huespedList) {
            System.out.println(reset + "Nombre Completo: " + h.getNombre() + " " + h.getApPaterno() + " " + h.getApMaterno());
            System.out.println(h.getTipoDocumento().getNombre() + ": " + h.getNumDocumento());
            System.out.println("Teléfono: " + h.getTelefono());
            System.out.println("Fecha de nacimiento: " + h.getFechaNacimiento());
            System.out.println("País: " + h.getPais().getNombre());
            System.out.println("__________________________________________");
        }
    }

    public Huesped seleccionarHuesped() {
        System.out.println(reset + "Seleccione un Huesped:");
        List<Huesped> huespedList = this.huespedController.getAllHuesped();
        Huesped huesped = null;

        if (!huespedList.isEmpty()) {
            for (int i = 0; i < huespedList.size(); i++) {
                System.out.println((i + 1) + " - Huesped: " + huespedList.get(i).getNombre() + " " + huespedList.get(i).getApPaterno() + " " + huespedList.get(i).getApMaterno());
                System.out.println(huespedList.get(i).getTipoDocumento().getNombre() + ": " + huespedList.get(i).getNumDocumento());
            }
            int idHuesped = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < huespedList.size(); i++) {
                if (idHuesped == i) {
                    huesped = huespedController.getHuespedById(huespedList.get(i).getIdHuesped());
                }
            }

            if(huesped == null) {
                System.out.println(rojo + "Mo se encontró Huesped.");
            }
        } else {
            System.out.println(reset + "No hay huesped para seleccionar.");
        }

        return huesped;
    }

    public void deleteHuespedById() {
        try {
            System.out.println(reset + "Ingrese el Huesped a eliminar:");
            List<Huesped> huespedList = huespedController.getAllHuesped();

            for (int i = 0; i < huespedList.size(); i++) {
                System.out.println((i+1) + " - " + huespedList.get(i).getNombre() + " " + huespedList.get(i).getApPaterno() + " " + huespedList.get(i).getApMaterno());
            }

            int indiceHuesped = Integer.parseInt(scanner.nextLine()) - 1;
            boolean huespedEliminado = false;

            for (int i = 0; i < huespedList.size(); i++) {
                if (indiceHuesped == i) {
                    huespedEliminado = this.huespedController.deleteHuesped(huespedList.get(i).getIdHuesped());
                }
            }

            if (huespedEliminado) {
                System.out.println(verde + "Huesped eliminado!");
            } else {
                System.out.println(rojo + "Ocurrió un error al eliminar el Huesped");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.deleteHuespedById();
        }
    }

    public void actualizarHuesped() {
        try {
            System.out.println(azul + "Ingrese el Huesped a actualizar:" + reset);
            List<Huesped> huespedList = huespedController.getAllHuesped();

            for (int i = 0; i < huespedList.size(); i++) {
                System.out.println((i+1) + " - " + huespedList.get(i).getNombre() + " " + huespedList.get(i).getApPaterno() + " " + huespedList.get(i).getApMaterno());
            }

            int indiceHuesped = Integer.parseInt(scanner.nextLine()) - 1;
            boolean HuespedExiste = false;

            for (int i = 0; i < huespedList.size(); i++) {
                if (indiceHuesped == i) {
                    HuespedExiste = true;
                    boolean salir = false;
                    while (!salir) {
                        System.out.println(azul + "¿Qué dato desea modificar?" + reset);
                        System.out.println("1 - Nombre: " + huespedList.get(i).getNombre());
                        System.out.println("2 - Apellido Paterno: " + huespedList.get(i).getApPaterno());
                        System.out.println("3 - Apellido Materno: " + huespedList.get(i).getApMaterno());
                        System.out.println("4 - Número de Documento: " + huespedList.get(i).getNumDocumento());
                        System.out.println("5 - Fecha de Nacimiento: " + huespedList.get(i).getFechaNacimiento());
                        System.out.println("6 - Teléfono: " + huespedList.get(i).getTelefono());
                        System.out.println("7 - País: " + huespedList.get(i).getPais().getNombre());
                        System.out.println("8 - Tipo de Documento: " + huespedList.get(i).getTipoDocumento().getNombre());
                        System.out.println("0 - Salir.");
                        String opcion = scanner.nextLine();
                        boolean HuespedActualizado = false;

                        switch (opcion) {
                            case "1":
                                System.out.print("Nuevo nombre: ");
                                String nombre = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarNombreHuesped(nombre, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setNombre(nombre);
                                break;

                            case "2":
                                System.out.print("Nuevo apellido Paterno: ");
                                String apPaterno = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarApPaternoHuesped(apPaterno, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setApPaterno(apPaterno);
                                break;

                            case "3":
                                System.out.print("Nuevo apellido Materno: ");
                                String apMaterno = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarApMaternoHuesped(apMaterno, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setApMaterno(apMaterno);
                                break;

                            case "4":
                                System.out.print("Nuevo número de documento: ");
                                String numDocumento = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarNumDocumentoHuesped(numDocumento, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setNumDocumento(numDocumento);
                                break;

                            case "5":
                                System.out.print("Nueva fecha de Nacimiento()aaaa/mm/dd: ");
                                String fechaNac = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarFechaNacHuesped(fechaNac, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setFechaNacimiento(fechaNac);
                                break;

                            case "6":
                                System.out.print("Nuevo teléfono: ");
                                String telefono = scanner.nextLine();
                                HuespedActualizado = huespedController.actualizarTelefonoHuesped(telefono, huespedList.get(i).getIdHuesped());
                                if (HuespedActualizado) huespedList.get(i).setTelefono(telefono);
                                break;

                            case "7":
                                List<Pais> paisList = paisController.getAllPais();
                                for (int j = 0; j < paisList.size(); j++) {
                                    System.out.println((j + 1) + " - " + paisList.get(j).getNombre());
                                }

                                System.out.print("Nuevo País: ");
                                int indicePais = Integer.parseInt(scanner.nextLine()) - 1;

                                for (int j = 0; j < paisList.size(); j++) {
                                    if (indicePais== j) {
                                        int idPais = paisList.get(j).getIdPais();
                                        Pais pais = paisController.getPaisById(idPais);
                                        HuespedActualizado = huespedController.actualizarPaisHuesped(idPais, huespedList.get(i).getIdHuesped());
                                        if (HuespedActualizado) huespedList.get(i).setPais(pais);
                                    }
                                }
                                break;

                            case "8":
                                List<TipoDocumento> tipoDocumentoList = tipoDocumentoController.getAllTipoDocumento();
                                for (int j = 0; j < tipoDocumentoList.size(); j++) {
                                    System.out.println((j + 1) + " - " + tipoDocumentoList.get(j).getNombre());
                                }

                                System.out.print("Nuevo Tipo de Documento: ");
                                int indiceTipoDocumento = Integer.parseInt(scanner.nextLine()) - 1;

                                for (int j = 0; j < tipoDocumentoList.size(); j++) {
                                    if (indiceTipoDocumento == j) {
                                        int idTipoDocumento = tipoDocumentoList.get(j).getIdTipoDocumento();
                                        TipoDocumento tipoDocumento = tipoDocumentoController.getTipoDocumentoById(idTipoDocumento);
                                        HuespedActualizado = huespedController.actualizarTipoDocumentoHuesped(idTipoDocumento, huespedList.get(i).getIdHuesped());
                                        if (HuespedActualizado) huespedList.get(i).setTipoDocumento(tipoDocumento);
                                    }
                                }
                                break;

                            case "0":
                                salir = true;
                                break;

                            default:
                                System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
                                break;

                        }
                        if (!salir && HuespedActualizado) {
                            System.out.println(verde + "Dato actualizado.\n");
                        } else if (!salir) {
                            System.out.println(rojo + "No se pudo actualizar los datos.");
                        }
                    }
                }
            }
            if (!HuespedExiste) {
                System.out.println(rojo + "No se encontró Huesped.");
                System.out.println(azul + "1 - Volver a buscar.");
                System.out.println("0 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        actualizarHuesped();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.deleteHuespedById();
        }
    }

    //Esperar Enter_____________________________________________________________________________________________________
    public void esperarEnter() {
        System.out.println(verde + "Presiona Enter para continuar...");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
