package src.model.dao;

import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.entities.Revendedor;
import java.util.List;

public interface RevendedorDAO {
    void insert(Revendedor obj)throws DBException, EntidadeJaExisteException;
    void update(Revendedor obj);
    void deleteByCpf(String cpf) throws DBIntegrityException;
    Revendedor findByCpf(String cpf)throws CampoObrigatorioException, DBException, EntidadeNaoEncontradaException;
    List<Revendedor> findAll()throws CampoObrigatorioException;
}
