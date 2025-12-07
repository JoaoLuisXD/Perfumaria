package src.model.entities;
import java.util.Date;

public class Inclui {
    private int qtd_itens;
    private Date data;

    public Inclui(int qtd_itens, Date data){
        this.qtd_itens = qtd_itens;
        this.data = data;

    };

    public Inclui(){};

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public int getQtd_itens() {
        return qtd_itens;
    }
    public void setQtd_itens(int qtd_itens) {
        this.qtd_itens = qtd_itens;
    }

    @Override
    public String toString() {
        return "Quantidade de itens: " + qtd_itens + ", Data: " + data;
    }


}