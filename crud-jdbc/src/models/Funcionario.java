package models;

public class Funcionario {

    private int id_func; 
	private String nome; 
    private String email;
    private int idade;
    private int id_cargo; 

    public int getId_func() {
        return id_func;
    }
    public void setId_func(int id_func) {
        this.id_func = id_func;
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
    public int getIdade() {
        return idade;
    }
    public int getId_cargo() {
        return id_cargo;
    }
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    } 

    
}
