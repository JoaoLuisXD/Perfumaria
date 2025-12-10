package src.model.dao;

import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.model.entities.Marca;
import java.util.List;

public interface MarcaDAO {
    void insert(Marca obj)throws EntidadeJaExisteException, DBException;
    void update(Marca obj);
    void deleteByCnpj(String cnpj)throws DBIntegrityException;
    Marca findByCnpj(String cnpj);
    List<Marca> findAll();
}
