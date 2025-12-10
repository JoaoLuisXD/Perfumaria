package src.model.entities;

import src.exceptions.CampoObrigatorioException;

public class Cliente {
    private String cpf;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;

    public Cliente(String cpf,String nome, String email, String endereco, String telefone){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    };

    public Cliente(){

    };

    public String getCpf() {
        return cpf;

    }
    public void setCpf(String cpf) throws CampoObrigatorioException{

        if (cpf == null || cpf.trim().isEmpty()){
            throw new CampoObrigatorioException("CPF é obrigatório");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) throws CampoObrigatorioException{
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("Nome é obrigatório");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente[ CPF: " + cpf
        + " Nome: "+ nome
        + " E-mail:  "+ email
        + " Endereço:  "+ endereco
        + "Telefone: " + telefone
        +" ]";
    }
}
