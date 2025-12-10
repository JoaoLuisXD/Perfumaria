package src.model.dao;

import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.model.entities.Entrega;

import java.util.List;

public interface EntregaDAO {
    void insert(Entrega obj)throws DBException;
    void update(Entrega obj)throws DBException;
    void deleteById(Integer id)throws DBIntegrityException;
    Entrega findById(Integer id)throws DBException;
    Entrega findByPedidoId(int pedidoId)throws DBException; 
    List<Entrega> findAll()throws DBException;
}
