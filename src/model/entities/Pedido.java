package src.model.entities;

public class Pedido {
    private int id;
    private double valor;
    private double comissao;

    private String cpfCliente;
    private String cpfRevendedor;
  

    public Pedido(int id, double valor, String cpfCliente, String cpfRevendedor){
        this.id = id;
        this.valor = valor;
        comissao = valor *0.5;
        this.cpfCliente = cpfCliente;
        this.cpfRevendedor = cpfRevendedor;
        
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

    public String getCliente() {
        return cpfCliente;
    }
    public void setCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getRevendedor() {
        return cpfRevendedor;
    }
    public void setRevendedor(String revendedor) {
        this.cpfRevendedor = cpfRevendedor;
    }

    @Override
    public String toString() {
        return "Pedido[ Id: "+ id
        + " Valor: R$ "+ valor
        + " Comissão: R$ "+ comissao
        + " Cliente: "+ cpfCliente
        + " Revendedor:  "+ cpfRevendedor;
    }
}
