package src.model.dao;

import src.model.entities.Revendedor;
import java.util.List;

public interface RevendedorDAO {
    void insert(Revendedor obj);
    void update(Revendedor obj);
    void deleteByCpf(String cpf);
    Revendedor findByCpf(String cpf);
    List<Revendedor> findAll();
}
