package src.model.entities;

public class Revendedor {
    private String cpf;
    private String nome;
    private String email;
    private Float salario;

    public Revendedor(String cpf, String nome,String email, Float salario){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.salario = salario;
    };

    public Revendedor(){};

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
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

}
