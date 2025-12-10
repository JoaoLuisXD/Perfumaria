package src.model.entities;

import java.util.Date;

import src.exceptions.CampoObrigatorioException;

public abstract class Entrega {
    private Integer id;
    private String endereco;
    private double valorEntrega;
    private Date data;
    private String status; // ex: "PENDENTE", "EM_TRANSITO", "ENTREGUE"
    private Integer idPedido;


    public Entrega() {}

    public Entrega(Integer id, String endereco, double valorEntrega, Date data, String status, Integer idPedido) {
        this.id = id;
        this.endereco = endereco;
        this.valorEntrega = valorEntrega;
        this.data = data;
        this.status = status;
        this.idPedido = idPedido;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) throws CampoObrigatorioException {
        if (id == null) {
            throw new CampoObrigatorioException("ID é obrigatório");
        }
        this.id = id; 
    }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) throws CampoObrigatorioException{

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new CampoObrigatorioException("Endereco é obrigatório");
        }
        this.endereco = endereco; 
    }

    public double getValorEntrega() { return valorEntrega; }
    public void setValorEntrega(double valorEntrega) { this.valorEntrega = valorEntrega; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    // mantém o método abstrato que impõe polimorfismo nas subclasses
    public abstract double calcularValorFinal();

    @Override
    public String toString() {
        return "Entrega [id=" + id +
               ", endereco=" + endereco +
               ", valorEntrega=" + valorEntrega +
               ", data=" + data +
               ", status=" + status + "]";
    }
}
