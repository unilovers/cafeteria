package cafeteria.model.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false, precision = 6, scale = 2 )
    private BigDecimal valor;

    @Column(name = "datahora", nullable = false)
    private LocalDateTime dataHora;

    //ForeignKey para Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    //ForeignKey para Atendente
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Atendente atendente;

    @PrePersist
    public void aoCriar() {
        this.dataHora = LocalDateTime.now();
    }

    public Pedido (){}

    public Pedido(BigDecimal valor, Cliente cliente, Atendente atendente) {
        //this.id =
        this.valor = valor;
        //this.dataHora = método "aoCriar"
        this.cliente = cliente;
        this.atendente = atendente;
    }

    /*
    Getters and
     Setters
    */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDatahora(LocalDateTime datahora) {
        this.dataHora = datahora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }


}
