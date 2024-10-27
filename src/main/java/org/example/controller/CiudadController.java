package org.example.controller;
import org.example.DAO.CiudadDAO;
import org.example.model.Ciudad;
import java.util.List;

public class CiudadController {
    private final CiudadDAO ciudadDAO = new CiudadDAO();

    public Ciudad getCiudadById(int idCiudad) {
        return  this.ciudadDAO.getCiudadById(idCiudad);
    }

    public List<Ciudad> getAllCiudad(){
        return this.ciudadDAO.getAllCiudad();
    }
}
