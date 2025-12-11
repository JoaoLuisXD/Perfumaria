package src.model.dao;

import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBIntegrityException;
import src.exceptions.DBException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.entities.Perfume;
import java.util.List;

public interface PerfumeDAO {
    void insert(Perfume obj)throws CampoObrigatorioException;
    void update(Perfume obj);
    void deleteById(Integer id) throws DBIntegrityException;
    Perfume findById(Integer id)throws CampoObrigatorioException, DBException, EntidadeNaoEncontradaException;
    List<Perfume> findAll()throws CampoObrigatorioException;
}
