package src.model.entities;

public class Pedido {
    private int id;
    private double valor;
    private double comissao;

    private Cliente cliente;
    private Revendedor revendedor;

    public Pedido(int id, double valor, Cliente cliente, Revendedor revendedor){
        this.id = id;
        this.valor = valor;
        comissao = valor *0.5;
        this.cliente = cliente;
        this.revendedor = revendedor;
    };

    public Pedido(){};

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Revendedor getRevendedor() {
        return revendedor;
    }
    public void setRevendedor(Revendedor revendedor) {
        this.revendedor = revendedor;
    }

    @Override
    public String toString() {
        return "Pedido[ Id: "+ id
        + " Valor: R$ "+ valor
        + " Comissão: R$ "+ comissao
        + " Cliente: "+ cliente
        + " Revendedor:  "+ revendedor;
    }
}
