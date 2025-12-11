package src.model.dao;

import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.CampoObrigatorioException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.entities.Entrega;

import java.util.List;

public interface EntregaDAO {
    void insert(Entrega obj)throws DBException, CampoObrigatorioException;
    void update(Entrega obj)throws DBException;
    void deleteById(Integer id)throws DBIntegrityException;
    Entrega findById(Integer id)throws DBException, EntidadeNaoEncontradaException, CampoObrigatorioException;
    Entrega findByPedidoId(int pedidoId)throws DBException; 
    List<Entrega> findAll()throws DBException;
}
