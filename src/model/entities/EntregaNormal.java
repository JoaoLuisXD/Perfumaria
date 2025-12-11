package src.model.entities;

import java.util.Date;

public class EntregaNormal extends Entrega {

    public EntregaNormal() { super(); }

    public EntregaNormal(Integer id, String endereco, double valorEntrega, Date data, String status, Integer idPedido) {
        super(id, endereco, valorEntrega, data, status, idPedido);
    }

    @Override
    public double calcularValorFinal() {
        return getValorEntrega()*1.1;
    }

    @Override
    public String toString() {
        return "Entrega Normal -> " + super.toString() +
               ", Valor final: " + calcularValorFinal();
    }
}
