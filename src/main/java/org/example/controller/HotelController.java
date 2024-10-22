package org.example.controller;
import org.example.DAO.HotelDAO;
import org.example.model.Hotel;
import java.util.List;

public class HotelController {
    private final HotelDAO hotelDAO = new HotelDAO();

    public boolean insertHotel(Hotel hotel){
        return this.hotelDAO.insertHotel(hotel);
    }

    public Hotel getHotelById(int idHotel) {
        return this.hotelDAO.getHotelById(idHotel);
    }

    public List<Hotel> getAllHotel(){
        return this.hotelDAO.getAllHotel();
    }

    public List<Hotel> getHotelByNombre(String nombre){
        return this.hotelDAO.getHotelByNombre(nombre);
    }

    public List<Hotel> getHotelByCiudad(int idCiudad){
        return this.hotelDAO.getHotelByCiudad(idCiudad);
    }

    public List<Hotel> getHotelByEstrellas(int estrellas){
        return this.hotelDAO.getHotelByEstrellas(estrellas);
    }

    public boolean deleteHotel(int idHotel){
        return this.hotelDAO.deleteHotel(idHotel);
    }

    public  boolean actualizarNombreHotel(String nombre, int idHotel) {
        return this.hotelDAO.actualizarNombreHotel(nombre, idHotel);
    }

    public  boolean actualizarCiudadHotel(int idCiudad, int idHotel) {
        return this.hotelDAO.actualizarCiudadHotel(idCiudad, idHotel);
    }

    public  boolean actualizarDireccionHotel(String direccion, int idHotel) {
        return this.hotelDAO.actualizarDireccionHotel(direccion, idHotel);
    }

    public  boolean actualizarEstrellasHotel(int estrellas, int idHotel) {
        return this.hotelDAO.actualizarEstrellasHotel(estrellas, idHotel);
    }
}
