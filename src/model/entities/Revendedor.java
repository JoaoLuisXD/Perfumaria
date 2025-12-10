package src.model.entities;

import src.exceptions.CampoObrigatorioException;

public class Revendedor {
    private String cpf;
    private String nome;
    private String email;
    private Float salario;
    private String telefone;


    public Revendedor(String cpf, String nome,String email, Float salario,String telefone){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.salario = salario;
        this.telefone = telefone;
    };

    public Revendedor(){};

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) throws CampoObrigatorioException{

        if (cpf == null) {
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

    public Float getSalario() {
        return salario;
    }
    public void setSalario(Float salario) {
        this.salario = salario;
    }
    
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Revendedor[ CPF: " + cpf
        + " Nome: "+ nome
        + " E-mail:  "+ email
        + " Salário:  "+ salario
        + " Telefone: " + telefone
        +" ]";
    }

}
