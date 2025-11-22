package cafeteria.Model.service;

import cafeteria.Model.dto.PedidoDTO;
import cafeteria.Model.Repository.PedidoRepository;
import cafeteria.Model.entities.Atendente;
import cafeteria.Model.entities.Cliente;
import cafeteria.Model.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;

    //Métodos do Crud

    // CREATE (usando DTO)
    public Pedido novoPedido(PedidoDTO dto) {

        if (dto.valor() == null || dto.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pedido deve ser maior que zero.");
        }

        Cliente cliente = clienteRepository.findById(dto.cliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.cliente_id()));

        Atendente atendente = atendenteRepository.findById(dto.atendente_id())
                .orElseThrow(() -> new IllegalArgumentException("Atendente não encontrado: " + dto.atendente_id()));

        Pedido pedido = new Pedido(dto.valor(), cliente, atendente);

        return pedidoRepository.save(pedido);
    }

    //Lista todos os pedidos
    public List<Pedido> getPedidos() {
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        return (List<Pedido>) pedidos;
    }

    //consulta

    public List<Pedido> getPedidosPorNomeCliente(String nome) throws Exception {
        List<Pedido> pedidosEncontrados = pedidoRepository.findPedidoByCliente_Nome(nome);
        if (pedidosEncontrados.isEmpty()) {
            throw new Exception("Nenhum pedido encontrado relacionado ao cliente " + nome + ".");
        }

        return pedidosEncontrados;
    }

    public List<Pedido> getPedidoPorNomeAtendente(String nome) throws Exception {
        List<Pedido> pedidosEncontrados = pedidoRepository.findPedidoByAtendente_Nome(nome);
        if (pedidosEncontrados.isEmpty()) {
            throw new Exception("Nenhum pedido encontrado relacionado ao atendente " + nome + ".");
        }

        return pedidosEncontrados;
    }


    //delete
    public void apagarPedidoPorId(Long id) throws Exception {
        if (pedidoRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Pedido com ID " + id + " não foi encontrado.");
        }

        try{
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("ID não existe");
        }
    }

    //atualizar
    public void atualizarPedido(Long id, PedidoDTO dto) throws Exception {

        // Buscar pedido existente
        Pedido pedidoAtualizar = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));

        // Verifica se DTO veio vazio
        if (dto.valor() == null && dto.cliente_id() == null && dto.atendente_id() == null) {
            throw new IllegalArgumentException("Nenhum campo para atualizar foi enviado.");
        }

        //Atualizações parciais

        if (dto.valor() != null) {
            if (dto.valor().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Valor deve ser maior que zero.");
            }
            pedidoAtualizar.setValor(dto.valor());
        }

        if (dto.cliente_id() != null) {
            Cliente cliente = clienteRepository.findById(dto.cliente_id())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + dto.cliente_id()));
            pedidoAtualizar.setCliente(cliente);
        }

        if (dto.atendente_id() != null) {
            Atendente atendente = atendenteRepository.findById(dto.atendente_id())
                    .orElseThrow(() -> new IllegalArgumentException("Atendente não encontrado: " + dto.atendente_id()));
            pedidoAtualizar.setAtendente(atendente);
        }

        pedidoRepository.save(pedidoAtualizar);
    }


    public void exibirPedido(){}

    public void finalizarPedido(){}
}
