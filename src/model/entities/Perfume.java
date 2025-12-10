package src.model.entities;

import src.exceptions.CampoObrigatorioException;

public class Perfume {
    private Integer id;
    private String nome;
    private double preco;
    private Integer estoque;

    private String cnpj_marca;

    public Perfume() {}

    public Perfume(Integer id, String nome, String cnpj_marca, double preco, Integer estoque){
        this.id = id;
        this.nome = nome;
        this.cnpj_marca = cnpj_marca;
        this.preco = preco;
        this.estoque = estoque;
        
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) throws CampoObrigatorioException{

        if (id == null) {
            throw new CampoObrigatorioException("ID é obrigatório");
        }

        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMarca() {
        return cnpj_marca;
    }
    public void setMarca(String marca) throws CampoObrigatorioException {
        if (id == null) {
            throw new CampoObrigatorioException("Marca é obrigatória");
        }
        this.cnpj_marca = cnpj_marca;
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
        return "Perfume [id: " + id + ", nome: " + nome + ", marca: " + cnpj_marca + ", preco: " + preco + ", estoque: " + estoque + "]";
    }
}


