package src.model.dao;

import src.exceptions.DBIntegrityException;
import src.model.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    void insert(Cliente obj);
    void update(Cliente obj);
    void deleteByCpf(String cpf) throws DBIntegrityException;
    Cliente findByCpf(String cpf);
    List<Cliente> findAll();
}
