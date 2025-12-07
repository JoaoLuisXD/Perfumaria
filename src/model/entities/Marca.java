package src.model.entities;

public class Marca {
    private String cnpj;
    private String nome;
    private String origem;
    private int anoCriacao;

    public Marca(String cnpj, String nome, String origem, int anoCriacao){
        this.cnpj = cnpj;
        this.nome = nome;
        this.origem = origem;
        this.anoCriacao = anoCriacao;
    }

    public Marca(){
        
    };

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getAnoCriacao() {
        return anoCriacao;
    }
    public void setAnoCriacao(int anoCriacao) {
        this.anoCriacao = anoCriacao;
    }

    @Override
    public String toString() {
        return "Marca["+
        "CNPJ: "+ cnpj
        +" Nome: "+nome
        +" Origem: "+origem
        +" Ano de criação: "+anoCriacao
        + "]";
    }
}
