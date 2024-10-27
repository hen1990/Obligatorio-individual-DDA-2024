package org.example.controller;
import org.example.DAO.TarifaDAO;
import org.example.model.Tarifa;
import java.util.List;

public class TarifaController {
    private final TarifaDAO tarifaDAO = new TarifaDAO();

    public Tarifa getTarifaById(int idTarifa) {
        return tarifaDAO.getTarifaById(idTarifa);
    }

    public List<Tarifa> getAllTarifa(){

        return this.tarifaDAO.getAllTarifa();
    }

    public Tarifa getTarifaByHabitacion(int idHabitacion){

        return this.tarifaDAO.getTarifaByHabitacion(idHabitacion);
    }
}
