package src.model.dao;

import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.model.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    void insert(Cliente obj) throws DBException, EntidadeJaExisteException;
    void update(Cliente obj);
    void deleteByCpf(String cpf) throws DBIntegrityException;
    Cliente findByCpf(String cpf);
    List<Cliente> findAll();
}
