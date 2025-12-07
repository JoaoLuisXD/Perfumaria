package src.model.dao;

import src.model.entities.Marca;
import java.util.List;

public interface MarcaDAO {
    void insert(Marca obj);
    void update(Marca obj);
    void deleteByCnpj(String cnpj);
    Marca findByCnpj(String cnpj);
    List<Marca> findAll();
}
