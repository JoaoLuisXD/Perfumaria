package src.model.entities;

public class Perfume {
    private Integer id;
    private String nome;
    private String marca;
    private double preco;
    private Integer estoque;

    public Perfume() {}

    public Perfume(Integer id, String nome, String marca, double preco, Integer estoque){
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String toString(){
        return "Perfume [id: " + id + ", nome: " + nome + ", marca: " + marca + ", preco: " + preco + ", estoque: " + estoque + "]";
    }
}


