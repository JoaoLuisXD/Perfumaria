package src.model.entities;

public class Perfume {
    private Integer id;
    private String nome;
    private double preco;
    private Integer estoque;

    private Marca marca;

    public Perfume() {}

    public Perfume(Integer id, String nome, Marca marca, double preco, Integer estoque){
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
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
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


