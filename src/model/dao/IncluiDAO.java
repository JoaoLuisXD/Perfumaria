package src.model.dao;

import src.model.entities.Inclui;
import java.util.List;

public interface IncluiDAO {
    void insert(Inclui obj);
    void update(Inclui obj);
    void deleteById(Integer id);
    Inclui findById(Integer id);
    List<Inclui> findAll();
}
