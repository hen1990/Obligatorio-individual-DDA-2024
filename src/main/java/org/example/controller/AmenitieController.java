package org.example.controller;
import org.example.DAO.AmenitieDAO;
import org.example.model.Amenitie;
import java.util.List;

public class AmenitieController {
    private final AmenitieDAO amenitieDAO = new AmenitieDAO();

    public Amenitie getAmenitieById(int idAmenitie) {
        return  this.amenitieDAO.getTipoAmenitieById(idAmenitie);
    }

    public List<Amenitie> getAllAmenitie(){
        return this.amenitieDAO.getAllAmenitie();
    }

    public List<Amenitie> getAmenitieByHabitacion(int idHabitacion){
        return this.amenitieDAO.getAmenitieByHabitacion(idHabitacion);
    }
}
