package src.model.dao;

import src.model.entities.Perfume;
import java.util.List;

public interface PerfumeDAO {
    void insert(Perfume obj);
    void update(Perfume obj);
    void deleteById(Integer id);
    Perfume findById(Integer id);
    List<Perfume> findAll();
}
