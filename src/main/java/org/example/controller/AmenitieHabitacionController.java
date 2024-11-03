package org.example.controller;


import org.example.DAO.AmenitieHabitacionDAO;
import org.example.model.Amenitie;

import java.util.List;

public class AmenitieHabitacionController {
    private final AmenitieHabitacionDAO amenitieHabitacionDAO = new AmenitieHabitacionDAO();

    public boolean insertAmenitieHabitacion(int idAmenitie, int idHabitacion) {
        return this.amenitieHabitacionDAO.insertAmenitieHabitacion(idAmenitie, idHabitacion);
    }

    public boolean deleteAmenitieHabitacion(int idAmenitie, int idHabitacion) {
        return this.amenitieHabitacionDAO.deleteAmenitieHabitacion(idAmenitie, idHabitacion);
    }

    public boolean deleteAllAmenitieEnHabitacion(int idHabitacion) {
        return this.amenitieHabitacionDAO.deleteAllAmenitieEnHabitacion(idHabitacion);
    }

    public List<Amenitie> getAmenitieNotInHabitacion(int idHabitacion) {
        return this.amenitieHabitacionDAO.getAmenitieNotInHabitacion(idHabitacion);
    }
}
