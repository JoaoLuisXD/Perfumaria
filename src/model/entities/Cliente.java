package src.model.entities;

import src.exceptions.CampoObrigatorioException;
import src.exceptions.FormatoInvalidoException;

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
        if (!cpf.matches("\\d{11}")) {
            throw new FormatoInvalidoException("CPF deve ter 11 dígitos numéricos");
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
        if (!email.contains("@") || !email.contains(".")) {
            throw new FormatoInvalidoException("Formato de e-mail inválido");
    }
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
        if (telefone != null && !telefone.matches("\\d{9,11}")) {
            throw new FormatoInvalidoException("Telefone deve conter entre 9 e 11 dígitos numéricos");
    }
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
