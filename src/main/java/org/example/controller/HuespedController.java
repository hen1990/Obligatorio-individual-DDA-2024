package org.example.controller;
import org.example.DAO.HuespedDAO;
import org.example.model.Huesped;
import java.util.List;

public class HuespedController {
    private final HuespedDAO huespedDAO = new HuespedDAO();

    public boolean insertHuesped(Huesped huesped){
        return this.huespedDAO.insertHuesped(huesped);
    }

    public Huesped getHuespedById(int idHuesped) {
        return this.huespedDAO.getHuespedById(idHuesped);
    }

    public List<Huesped> getAllHuesped(){
        return this.huespedDAO.getAllHuesped();
    }

    public boolean deleteHuesped(int idHuesped){
        return this.huespedDAO.deleteHuesped(idHuesped);
    }

    public boolean actualizarNombreHuesped(String nombre, int idHuesped){
        return this.huespedDAO.actualizarNombreHuesped(nombre, idHuesped);
    }

    public boolean actualizarApPaternoHuesped(String apPaterno, int idHuesped){
        return this.huespedDAO.actualizarApPaternoHuesped(apPaterno, idHuesped);
    }

    public boolean actualizarApMaternoHuesped(String apMaterno, int idHuesped){
        return this.huespedDAO.actualizarApMaternoHuesped(apMaterno, idHuesped);
    }

    public boolean actualizarNumDocumentoHuesped(String numDocumento, int idHuesped){
        return this.huespedDAO.actualizarNumDocumentoHuesped(numDocumento, idHuesped);
    }

    public boolean actualizarFechaNacHuesped(String fechaNac, int idHuesped){
        return this.huespedDAO.actualizarFechaNacHuesped(fechaNac, idHuesped);
    }

    public boolean actualizarTelefonoHuesped(String telefono, int idHuesped){
        return this.huespedDAO.actualizarTelefonoHuesped(telefono, idHuesped);
    }

    public boolean actualizarPaisHuesped(int idPais, int idHuesped){
        return this.huespedDAO.actualizarPaisHuesped(idPais, idHuesped);
    }

    public boolean actualizarTipoDocumentoHuesped(int idTipoDocumento, int idHuesped){
        return this.huespedDAO.actualizarTipoDocumentoHuesped(idTipoDocumento, idHuesped);
    }
}
