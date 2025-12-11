package src.model.dao;

import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBIntegrityException;
import src.exceptions.DBException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.entities.Pedido;
import java.util.List;

public interface PedidoDAO {
    void insert(Pedido obj)throws CampoObrigatorioException;
    void update(Pedido obj);
    void deleteById(Integer id)throws DBIntegrityException;
    Pedido findById(Integer id)throws CampoObrigatorioException, EntidadeNaoEncontradaException, DBException;
    List<Pedido> findAll()throws CampoObrigatorioException;
}
