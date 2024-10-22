package org.example.view;

import org.example.controller.CiudadController;
import org.example.controller.HotelController;
import org.example.model.Ciudad;
import org.example.model.Hotel;

import java.util.List;
import java.util.Scanner;

public class HotelView {
    private final Scanner scanner = new Scanner(System.in);
    private final HotelController hotelController = new HotelController();
    private final CiudadController ciudadController = new CiudadController();
    private final CiudadView ciudadView = new CiudadView();

    public void hotel() {
        boolean volver = false;

        while (!volver) {
            System.out.println("MENU HOTELES");
            System.out.println("1 - Ingresar un Hotel.");
            System.out.println("2 - Eliminar un Hotel.");
            System.out.println("3 - Modificar un Hotel.");

            System.out.println("4 - Consultar Hoteles.");
            System.out.println("5 - Ver Hoteles.");
            System.out.println("6 - Ver un Hotel.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertHotel();
                    esperarEnter();
                    break;
                case "2":
                    deleteHotelById();
                    esperarEnter();
                    break;
                case "3":
                    actualizarHotel();
                    esperarEnter();
                    break;
                case "4":
                    consultarHotel();
                    esperarEnter();
                    break;
                case "5":
                    getAllHotel();
                    esperarEnter();
                    break;
                case "6":
                    getHotelById();
                    esperarEnter();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    public void insertHotel() {
        try {
            System.out.println("Ingrese los datos del Hotel a continuación:");
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese dirección: ");
            String direccion = scanner.nextLine();
            System.out.print("Ingrese cantidad de estrellas (entre 1 y 5): ");
            int estrellas = Integer.parseInt(scanner.nextLine());

            Ciudad ciudad = ciudadView.seleccionarCiudad();

            Hotel hotel = new Hotel(nombre, ciudad, direccion, estrellas);
            boolean HotelInserted = this.hotelController.insertHotel(hotel);

            if (HotelInserted) {
                System.out.println("Hotel ingresado! ");
            } else {
                System.out.println("Ocurrió un error al ingresar el Hotel, \nvuelva a intentarlo más tarde.");
            }

        } catch (
                Exception e) {
            System.out.println("Algo salió mal.");
            System.out.println("1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertHotel();
                    break;
                case "2":
                    System.out.println("volviendo...");
                    break;
                default:
                    System.out.println("Opción no válida, volviendo al Inicio.");
                    break;
            }
        }
    }

    public void getHotelById() {
        try {
            System.out.println("Por favor, ingrese el Id del Hotel: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            Hotel hotel = hotelController.getHotelById(opcion);

            if (hotel == null) {
                System.out.println("No se encontró Hotel.");
            } else {
                System.out.println("Nombre: " + hotel.getNombre());
                System.out.println("Hubicaión: " +
                        hotel.getCiudad().getPais().getNombre() + ", " +
                        hotel.getCiudad().getNombre() + ", " +
                        hotel.getDireccion());
                System.out.println("Estrellas: " + hotel.getEstrellas());
                System.out.println("__________________________________________");
            }
        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.getHotelById();
        }
    }

    public void getAllHotel() {
        List<Hotel> userList = this.hotelController.getAllHotel();

        for (Hotel h : userList) {
            System.out.println("Nombre: " + h.getNombre());
            System.out.println("Hubicaión: " +
                    h.getCiudad().getPais().getNombre() + ", " +
                    h.getCiudad().getNombre() + ", " +
                    h.getDireccion());
            System.out.println("Estrellas: " + h.getEstrellas());
            System.out.println("__________________________________________");
        }
    }

    public void deleteHotelById() {
        try {
            System.out.println("Ingrese el Hotel a eliminar:");
            List<Hotel> hotelList = hotelController.getAllHotel();

            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
            }

            int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;
            boolean hotelEliminado = false;

            for (int i = 0; i < hotelList.size(); i++) {
                if (indiceHotel == i) {
                    hotelEliminado = this.hotelController.deleteHotel(hotelList.get(i).getIdHotel());
                }
            }

            if (hotelEliminado) {
                System.out.println("Hotel eliminado!");
            } else {
                System.out.println("Ocurrió un error al eliminar el Hotel");
            }
        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.deleteHotelById();
        }
    }

    public void actualizarHotel() {
        try {
            System.out.println("Ingrese el Hotel a actualizar:");
            List<Hotel> hotelList = hotelController.getAllHotel();

            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
            }

            int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;
            boolean hotelExiste = false;

            for (int i = 0; i < hotelList.size(); i++) {
                if (indiceHotel == i) {
                    hotelExiste = true;
                    boolean salir = false;
                    while (!salir) {
                        System.out.println("¿Qué dato desea modificar?");
                        System.out.println("1 - Nombre: " + hotelList.get(i).getNombre());
                        System.out.println("2 - Ciudad: " + hotelList.get(i).getCiudad().getNombre());
                        System.out.println("3 - Dirección: " + hotelList.get(i).getDireccion());
                        System.out.println("4 - Estrellas: " + hotelList.get(i).getEstrellas());
                        System.out.println("0 - Salir.");
                        String opcion = scanner.nextLine();
                        boolean hotelActualizado = false;

                        switch (opcion) {
                            case "1":
                                System.out.print("Nuevo nombre: ");
                                String nombre = scanner.nextLine();
                                hotelActualizado = hotelController.actualizarNombreHotel(nombre, hotelList.get(i).getIdHotel());
                                if (hotelActualizado) hotelList.get(i).setNombre(nombre);
                                break;

                            case "2":
                                System.out.print("Nueva Ciudad: ");
                                Ciudad ciudad = ciudadView.seleccionarCiudad();
                                hotelActualizado = hotelController.actualizarCiudadHotel(ciudad.getIdCiudad(), hotelList.get(i).getIdHotel());
                                if (hotelActualizado) hotelList.get(i).setCiudad(ciudad);
                                break;

                            case "3":
                                System.out.print("Nueva Dirección: ");
                                String direccion = scanner.nextLine();
                                hotelActualizado = hotelController.actualizarDireccionHotel(direccion, hotelList.get(i).getIdHotel());
                                if (hotelActualizado) hotelList.get(i).setDireccion(direccion);
                                break;

                            case "4":
                                System.out.print("Nuevo valor de estrellas: ");
                                int estrellas = Integer.parseInt(scanner.nextLine());
                                hotelActualizado = hotelController.actualizarEstrellasHotel(estrellas, hotelList.get(i).getIdHotel());
                                if (hotelActualizado) hotelList.get(i).setEstrellas(estrellas);
                                break;

                            case "0":
                                salir = true;
                                break;

                            default:
                                System.out.println("Opción inválida, ingrese una opción válida.");
                                break;

                        }
                        if (!salir && hotelActualizado) {
                            System.out.println("Dato actualizado.\n");
                        } else if (!salir) {
                            System.out.println("No se pudo actualizar los datos.");
                        }
                    }
                }
            }
            if (!hotelExiste) {
                System.out.println("No se encontró Hotel.");
                System.out.println("1 - Volver a buscar.");
                System.out.println("0 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        actualizarHotel();
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Opción no válida, volviendo al Inicio.");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Opción inválida, ingrese una opción válida.");
            this.deleteHotelById();
        }
    }

    //Consultas_________________________________________________________________________________________________________
    public void consultarHotel() {
        boolean volver = false;

        while (!volver) {
            System.out.println("CONSULTAS");
            System.out.println("1 - Buscar Hotel por Nombre.");
            System.out.println("2 - Buscar Hotel por Ciudad.");
            System.out.println("3 - Buscar Hotel por Pais.");
            System.out.println("4 - Buscar Hotel por Estrellas.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorNombre();
                    esperarEnter();
                    break;
                case "2":
                    buscarHotelPorCiudad();
                    esperarEnter();
                    break;
                case "3":
                    buscarHotelPorEstrellas();
                    esperarEnter();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción lo valida.");
                    esperarEnter();
                    break;
            }
        }
    }

    public void buscarHotelPorNombre() {
        System.out.println("Ingrese nombre del Hotel: ");
        String nombre = "%" + scanner.nextLine() + "%";
        List<Hotel> hotelList = hotelController.getHotelByNombre(nombre);

        System.out.println("Hoteles encontrados. Seleccione uno.");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
        }

        int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;
        boolean hotelExiste = false;

        for (int i = 0; i < hotelList.size(); i++) {
            if (indiceHotel == i) {
                Hotel hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                hotelExiste = true;
            }
        }

        if (!hotelExiste) {
            System.out.println("No se encontró Hotel.");
            System.out.println("1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorNombre();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción no válida, volviendo al Inicio.");
                    break;
            }
        } else {
            System.out.println("Haga su reserva.");
            System.out.println("1 - Para hoy.");
            System.out.println("2 - Ingresar fecha.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHabitacionDisponible();
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    public void buscarHotelPorCiudad() {
        Ciudad ciudad = ciudadView.seleccionarCiudad();
        List<Hotel> hotelList = hotelController.getHotelByCiudad(ciudad.getIdCiudad());
        System.out.println("Hoteles encontrados. Seleccione uno.");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
        }

        int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;
        boolean hotelExiste = false;

        for (int i = 0; i < hotelList.size(); i++) {
            if (indiceHotel == i) {
                Hotel hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                hotelExiste = true;
            }
        }

        if (!hotelExiste) {
            System.out.println("No se encontró Hotel.");
            System.out.println("1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorNombre();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción no válida, volviendo al Inicio.");
                    break;
            }
        } else {
            System.out.println("Haga su reserva.");
            System.out.println("1 - Para hoy.");
            System.out.println("2 - Ingresar fecha.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHabitacionDisponible();
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }


    }

    public void buscarHotelPorEstrellas() {
        System.out.println("Ingrese cantidad de estrellas: ");
        int estrellas = Integer.parseInt(scanner.nextLine());
        List<Hotel> hotelList = hotelController.getHotelByEstrellas(estrellas);

        System.out.println("Hoteles con " + estrellas + " estrellas + más. Seleccione uno.");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
        }

        int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;
        boolean hotelExiste = false;

        for (int i = 0; i < hotelList.size(); i++) {
            if (indiceHotel == i) {
                Hotel hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                hotelExiste = true;
            }
        }

        if (!hotelExiste) {
            System.out.println("No se encontró Hotel.");
            System.out.println("1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorNombre();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción no válida, volviendo al Inicio.");
                    break;
            }
        } else {
            System.out.println("Haga su reserva.");
            System.out.println("1 - Para hoy.");
            System.out.println("2 - Ingresar fecha.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHabitacionDisponible();
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    //Esperar Enter_____________________________________________________________________________________________________
    public void esperarEnter() {
        System.out.println("Presiona Enter para continuar...");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
