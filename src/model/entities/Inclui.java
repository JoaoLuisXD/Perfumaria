package src.model.entities;

import java.util.Date;

public class Inclui {

    private int idPedido;
    private int idPerfume;
    private int qtd_itens;
    private Date data;

    public Inclui() {}

    public Inclui(int idPedido, int idPerfume, int qtd_itens, Date data){
        this.idPedido = idPedido;
        this.idPerfume = idPerfume;
        this.qtd_itens = qtd_itens;
        this.data = data;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPerfume() {
        return idPerfume;
    }
    public void setIdPerfume(int idPerfume) {
        this.idPerfume = idPerfume;
    }

    public int getQtd_itens() {
        return qtd_itens;
    }
    public void setQtd_itens(int qtd_itens) {
        this.qtd_itens = qtd_itens;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Inclui [Pedido: " + idPedido +
               ", Perfume: " + idPerfume +
               ", Quantidade: " + qtd_itens +
               ", Data: " + data + "]";
    }
}
