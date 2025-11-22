package cafeteria.Controller;

import cafeteria.dto.PedidoDTO;
import cafeteria.Model.entities.Pedido;
import cafeteria.Model.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //Criar
    @PostMapping("/novo")
    public ResponseEntity<?> novoPedido(@RequestBody PedidoDTO dto) {
        try {
            pedidoService.novoPedido(dto);
            return ResponseEntity.status(201).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Ocorreu um erro \n" + e.getMessage());
        }

    }


    //Lista todos os pedidos

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        List<Pedido> pedidos = pedidoService.getPedidos();
        return ResponseEntity.status(200).body(pedidos);
    }

    //Consultas
    @GetMapping("/consulta/cliente")
    public ResponseEntity<?> getPedidosPorNomeCliente(@RequestParam String nomeCliente) {

        try{
            List<Pedido> pedidosEncontrados;
            pedidosEncontrados = pedidoService.getPedidosPorNomeCliente(nomeCliente);
            return ResponseEntity.ok(pedidosEncontrados);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/consulta/atendente")
    public ResponseEntity<?> getPedidosPorNomeAtendente(@RequestParam String nomeAtendente) {

        try {
            List<Pedido> pedidosEncontrados;
            pedidosEncontrados = pedidoService.getPedidoPorNomeAtendente(nomeAtendente);
            return ResponseEntity.ok(pedidosEncontrados);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    //Deletar
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletePedidoPorId(@PathVariable("id") Long idPedido) {
        try{
            pedidoService.apagarPedidoPorId(idPedido);
            return ResponseEntity.status(200).body("Pedido apagado com sucesso");
        }
        catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    //Atualizar

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPedido(@PathVariable Long id ,@RequestBody PedidoDTO dto) {
        try{
            pedidoService.atualizarPedido(id, dto);
            return ResponseEntity.status(201).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
