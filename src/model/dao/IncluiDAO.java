package src.model.dao;

import java.util.List;
import src.model.entities.Inclui;

public interface IncluiDAO {
    void insert(Inclui obj);
    void update(Inclui obj);
    void delete(int idPedido, int idPerfume);
    Inclui findById(int idPedido, int idPerfume);
    List<Inclui> findAll();
}
