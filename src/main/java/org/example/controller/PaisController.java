package org.example.controller;
import org.example.DAO.PaisDAO;
import org.example.model.Pais;
import java.util.List;

public class PaisController {
    private final PaisDAO paisDAO = new PaisDAO();

    public Pais getPaisById(int idPais) {
        return  this.paisDAO.getPaisById(idPais);
    }

    public List<Pais> getAllPais(){

        return this.paisDAO.getAllPais();
    }

}
