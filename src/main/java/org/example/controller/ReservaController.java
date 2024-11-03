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

    public List<Reserva> getReservaByHuesped(int idHuesped){
        return this.reservaDAO.getReservaByHuesped(idHuesped);
    }

    public List<Reserva> getReservaByHabitacion(int idHabitacion){
        return this.reservaDAO.getReservaByHabitacion(idHabitacion);
    }

    public List<Reserva> getReservaByFecha(String fechaInicio, String fechaFin){
        return this.reservaDAO.getReservaByFecha(fechaInicio, fechaFin);
    }

    public Reserva getUltimaReserva() {
        return reservaDAO.getUltimaReserva();
    }

    public boolean deleteReserva(int idReserva) {
        return  reservaDAO.deleteReserva(idReserva);
    }

    public boolean actualizarCantidadPersonas(int cantidadPersonas, int idReserva) {
        return  reservaDAO.actualizarCantidadPersonas(cantidadPersonas, idReserva);
    }

    public boolean actualizarFechaInicio(String fechaInicio, int idReserva) {
        return  reservaDAO.actualizarFechaInicio(fechaInicio, idReserva);
    }

    public boolean actualizarFechaFin(String fechaFin, int idReserva) {
        return  reservaDAO.actualizarFechaFin(fechaFin, idReserva);
    }

    public boolean actualizarTarifa(double tarifa, int idReserva) {
        return  reservaDAO.actualizarTarifa(tarifa, idReserva);
    }

    public boolean actualizarHuesped(int idHuesped, int idReserva) {
        return  reservaDAO.actualizarHuesped(idHuesped, idReserva);
    }
}
