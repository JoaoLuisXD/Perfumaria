package src.model.entities;

import java.util.Date;

public class EntregaRapida extends Entrega {

    public EntregaRapida(Integer id, String endereco, double valorEntrega, Date data, String status, Integer idPedido) {
        super(id, endereco, valorEntrega, data, status, idPedido);
    }

    @Override
    public double calcularValorFinal() {
        return getValorEntrega() * 1.5; 
    }

    @Override
    public String toString() {
        return "Entrega Rapida -> " + super.toString() +
               ", Valor final: " + calcularValorFinal();
    }
}
