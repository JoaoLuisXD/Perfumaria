package src.model.dao;

import src.model.entities.Entrega;
import src.model.exceptions.DBException;

import java.util.List;

public interface EntregaDAO {
    void insert(Entrega obj)throws DBException;
    void update(Entrega obj)throws DBException;
    void deleteById(Integer id)throws DBException;
    Entrega findById(Integer id)throws DBException;
    List<Entrega> findAll()throws DBException;
}
