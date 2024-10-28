package org.example.view;
import org.example.controller.HotelController;
import org.example.model.Ciudad;
import org.example.model.Hotel;
import java.util.List;
import java.util.Scanner;

public class HotelView {
    String rojo = "\u001B[31m";
    String azul = "\u001B[34m";
    String verde = "\u001B[32m";
    String reset = "\u001B[0m";

    private final Scanner scanner = new Scanner(System.in);
    private final HotelController hotelController = new HotelController();
    private final CiudadView ciudadView = new CiudadView();

    public void hotel() {
        boolean volver = false;

        while (!volver) {
            System.out.println(azul + "MENU HOTELES");
            System.out.println("1 - Ingresar un Hotel.");
            System.out.println("2 - Eliminar un Hotel.");
            System.out.println("3 - Modificar un Hotel.");
            System.out.println("4 - Ver Hoteles.");
            System.out.println("5 - Ver un Hotel.");
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
                    getAllHotel();
                    esperarEnter();
                    break;
                case "5":
                    getHotelById();
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

    public void insertHotel() {
        try {
            System.out.println(reset + "Ingrese los datos del Hotel a continuación:");
            System.out.print(reset + "Ingrese el nombre: ");
            String nombre = scanner.nextLine();
            System.out.print(reset + "Ingrese dirección: ");
            String direccion = scanner.nextLine();
            System.out.print(reset + "Ingrese cantidad de estrellas (entre 1 y 5): ");
            int estrellas = Integer.parseInt(scanner.nextLine());

            Ciudad ciudad = ciudadView.seleccionarCiudad();

            Hotel hotel = new Hotel(nombre, ciudad, direccion, estrellas);
            boolean HotelInserted = this.hotelController.insertHotel(hotel);

            if (HotelInserted) {
                System.out.println(verde + "Hotel ingresado! ");
            } else {
                System.out.println(rojo + "Ocurrió un error al ingresar el Hotel, \nvuelva a intentarlo más tarde.");
            }

        } catch (
                Exception e) {
            System.out.println(rojo + "Algo salió mal.");
            System.out.println(azul + "1 - Volver a ingresar los datos.");
            System.out.println("2 - Volver al Inicio.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    insertHotel();
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

    public void getHotelById() {
        try {
            System.out.println(reset + "Por favor, ingrese el Id del Hotel: ");
            int opcion = Integer.parseInt(scanner.nextLine());
            Hotel hotel = hotelController.getHotelById(opcion);

            if (hotel == null) {
                System.out.println(rojo + "No se encontró Hotel.");
            } else {
                System.out.println(reset + "Nombre: " + hotel.getNombre());
                System.out.println(reset + "Hubicaión: " +
                        hotel.getCiudad().getPais().getNombre() + ", " +
                        hotel.getCiudad().getNombre() + ", " +
                        hotel.getDireccion());
                System.out.print("Estrellas: " );
                for (int j = 0; j < hotel.getEstrellas(); j++) {
                    System.out.print("⭐");
                };
                System.out.println(" ");
                System.out.println(reset + "__________________________________________");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.getHotelById();
        }
    }

    public void getAllHotel() {
        List<Hotel> userList = this.hotelController.getAllHotel();

        for (Hotel h : userList) {
            System.out.println(reset + "Nombre: " + h.getNombre());
            System.out.println(reset + "Hubicaión: " +
                    h.getCiudad().getPais().getNombre() + ", " +
                    h.getCiudad().getNombre() + ", " +
                    h.getDireccion());
            System.out.print("Estrellas: " );
            for (int j = 0; j < h.getEstrellas(); j++) {
                System.out.print("⭐");
            };
            System.out.println(" ");
            System.out.println(reset + "__________________________________________");
        }
    }

    public void deleteHotelById() {
        try {
            System.out.println(reset + "Ingrese el Hotel a eliminar:");
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
                System.out.println(verde + "Hotel eliminado!");
            } else {
                System.out.println(rojo + "Ocurrió un error al eliminar el Hotel");
            }
        } catch (Exception e) {
            System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
            this.deleteHotelById();
        }
    }

    public void actualizarHotel() {
        try {
            System.out.println(azul + "Ingrese el Hotel a actualizar: " + reset);
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
                        System.out.println(azul + "¿Qué dato desea modificar?");
                        System.out.println(reset + "1 - Nombre: " + hotelList.get(i).getNombre());
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
                                System.out.println(rojo + "Opción inválida, ingrese una opción válida.");
                                break;

                        }
                        if (!salir && hotelActualizado) {
                            System.out.println(verde + "Dato actualizado.\n");
                        } else if (!salir) {
                            System.out.println(rojo + "No se pudo actualizar los datos.");
                        }
                    }
                }
            }
            if (!hotelExiste) {
                System.out.println(rojo + "No se encontró Hotel.");
                System.out.println(azul + "1 - Volver a buscar.");
                System.out.println("0 - Salir.");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        actualizarHotel();
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
            this.deleteHotelById();
        }
    }

    //Consultas_________________________________________________________________________________________________________
    public Hotel getHotel() {
        boolean volver = false;
        Hotel hotel = null;

        while (!volver) {
            System.out.println(azul + "1 - Buscar Hotel por Nombre.");
            System.out.println("2 - Buscar Hotel por Ciudad.");
            System.out.println("3 - Buscar Hotel por Estrellas.");
            //System.out.println("4 - Buscar Hotel por Pais.");
            System.out.println("0 - Volver.");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    hotel = buscarHotelPorNombre();
                    if (hotel != null) {volver = true;}
                    esperarEnter();
                    break;
                case "2":
                    hotel = buscarHotelPorCiudad();
                    if (hotel != null) {volver = true;}
                    esperarEnter();
                    break;
                case "3":
                    hotel = buscarHotelPorEstrellas();
                    if (hotel != null) {volver = true;}
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
        return hotel;
    }

    public Hotel buscarHotelPorNombre() {
        Hotel hotel = null;

        System.out.println(reset + "Ingrese nombre del Hotel: ");
        String nombre = "%" + scanner.nextLine() + "%";
        List<Hotel> hotelList = hotelController.getHotelByNombre(nombre);

        if(!hotelList.isEmpty()) {
            System.out.println(reset + "Hoteles encontrados. Seleccione uno.");
            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre()  + ", " + hotelList.get(i).getCiudad().getPais().getNombre()+ ", " + hotelList.get(i).getCiudad().getNombre());
                System.out.print("Estrellas: " );
                for (int j = 0; j < hotelList.get(i).getEstrellas(); j++) {
                    System.out.println("⭐");
                };
            }

            int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < hotelList.size(); i++) {
                if (indiceHotel == i) {
                    hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                }
            }
        }

        if (hotel == null) {
            System.out.println(rojo + "No se encontró Hotel.");
            System.out.println(azul + "1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorNombre();
                    break;
                case "0":
                    break;
                default:
                    System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                    break;
            }
        }
        return hotel;
    }

    public Hotel buscarHotelPorCiudad() {
        Hotel hotel = null;

        Ciudad ciudad = ciudadView.seleccionarCiudad();
        List<Hotel> hotelList = hotelController.getHotelByCiudad(ciudad.getIdCiudad());

        if(!hotelList.isEmpty()) {
            System.out.println(reset + "Hoteles encontrados. Seleccione uno.");
            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
            }

            int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < hotelList.size(); i++) {
                if (indiceHotel == i) {
                    hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                }
            }
        }

        if (hotel == null) {
            System.out.println(rojo + "No se encontró Hotel.");
            System.out.println(azul + "1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorCiudad();
                    break;
                case "0":
                    break;
                default:
                    System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                    break;
            }
        }
        return hotel;
    }

    public Hotel buscarHotelPorEstrellas() {
        Hotel hotel = null;

        System.out.println(reset + "Ingrese cantidad de estrellas: ");
        int estrellas = Integer.parseInt(scanner.nextLine());
        List<Hotel> hotelList = hotelController.getHotelByEstrellas(estrellas);

        if(!hotelList.isEmpty()) {
            System.out.println(reset + "Hoteles con " + estrellas + " estrellas o más. Seleccione uno.");
            for (int i = 0; i < hotelList.size(); i++) {
                System.out.println((i + 1) + " - " + hotelList.get(i).getNombre() + ", " + hotelList.get(i).getCiudad().getNombre());
            }

            int indiceHotel = Integer.parseInt(scanner.nextLine()) - 1;

            for (int i = 0; i < hotelList.size(); i++) {
                if (indiceHotel == i) {
                    hotel = hotelController.getHotelById(hotelList.get(i).getIdHotel());
                }
            }
        }

        if (hotel == null) {
            System.out.println(rojo + "No se encontró Hotel.");
            System.out.println(azul + "1 - Volver a buscar.");
            System.out.println("0 - Salir.");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    buscarHotelPorEstrellas();
                    break;
                case "0":
                    break;
                default:
                    System.out.println(rojo + "Opción no válida, volviendo al Inicio.");
                    break;
            }
        }
        return hotel;
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
