package src.model.dao;

import src.model.entities.Pedido;
import java.util.List;

public interface PedidoDAO {
    void insert(Pedido obj);
    void update(Pedido obj);
    void deleteById(Integer id);
    Pedido findById(Integer id);
    List<Pedido> findAll();
}
