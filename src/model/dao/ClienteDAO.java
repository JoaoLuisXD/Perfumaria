package src.model.dao;

import src.model.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    void insert(Cliente obj);
    void update(Cliente obj);
    void deleteByCpf(String cpf);
    Cliente findByCpf(String cpf);
    List<Cliente> findAll();
}
