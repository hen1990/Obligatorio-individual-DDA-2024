package org.example.controller;
import org.example.DAO.TipoDocumentoDAO;
import org.example.model.TipoDocumento;
import java.util.List;

public class TipoDocumentoController {
    private final TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();

    public TipoDocumento getTipoDocumentoById(int idTipoDocumento) {
        return tipoDocumentoDAO.getTipoDocumentoById(idTipoDocumento);
    }

    public List<TipoDocumento> getAllTipoDocumento(){

        return this.tipoDocumentoDAO.getAllTipoDocumento();
    }
}
