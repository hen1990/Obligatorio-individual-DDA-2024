package org.example.controller;
import org.example.DAO.ReservaDAO;
import org.example.model.Reserva;
import java.util.List;

public class ReservaController {
    private final ReservaDAO reservaDAO = new ReservaDAO();

    public boolean insertReserva(Reserva reserva) {
        return  reservaDAO.insertReserva(reserva);
    }
    public Reserva getReservaById(int idReserva) {
        return reservaDAO.getReservaById(idReserva);
    }

    public List<Reserva> getAllReserva(){

        return this.reservaDAO.getAllReserva();
    }

    public boolean deleteReserva(Reserva reserva) {
        return  reservaDAO.deleteReserva(reserva);
    }
}
