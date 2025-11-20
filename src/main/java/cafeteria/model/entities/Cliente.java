package cafeteria.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 30)
    private String nome;
    @Column(name="cpf", nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(name="endereco", length = 100, nullable = false)
    private String endereco;

    public Cliente() {}
    public Cliente(String nome, String cpf, String endereco) {
        //this.id =
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    /* Gett e Sett
         */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
