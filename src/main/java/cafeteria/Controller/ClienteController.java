package cafeteria.Controller;


import cafeteria.model.entities.Cliente;
import cafeteria.model.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //lista total clientes

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return ResponseEntity.status(200).body(clientes);

    }


    //consultas
    @GetMapping("/consulta")
        public ResponseEntity<?> consultaCliente(@RequestParam(value = "nome") String nome) {

        try{
            List<Cliente> clientesEncontrados;
            clientesEncontrados = clienteService.consultaClienteNome(nome);
            return ResponseEntity.ok(clientesEncontrados);

        } catch (Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/consulta/end")
    public ResponseEntity<?> consultaClienteEndereco(@RequestParam(value = "endereco") String endereco) {
        try{
            List<Cliente> clientesEncontrados;
            clientesEncontrados = clienteService.consultaClienteEndereco(endereco);
            return ResponseEntity.ok(clientesEncontrados);

        } catch (Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @GetMapping("/consulta/cpf")
    public ResponseEntity<?> consultaClienteCpf(@RequestParam(value = "cpf") String cpf) {
        try{
            Optional<Cliente> clientesEncontrados;
            clientesEncontrados = clienteService.consultaClienteCpf(cpf);
            return ResponseEntity.ok(clientesEncontrados);

        } catch (Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    //Delete
    @DeleteMapping("/apagar/{id}")
    public  ResponseEntity<?> apagarClienteId(@PathVariable("id") Integer id){
        try{
            clienteService.apagarClienteId(id);
            return ResponseEntity.ok(true);
        }
        catch (Exception e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar")
    public  ResponseEntity<?> apagarClienteCpf(@RequestParam("cpf") String cpf){
        try{
            clienteService.apagarClienteCpf(cpf);
            return ResponseEntity.ok(true);
        }
        catch (Exception e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //Atualizar
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarCliente (@RequestBody Cliente cliente){
        try {
            clienteService.atualizarCliente(cliente);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    //create
    @PostMapping("/novo")
    public ResponseEntity<?> novoCliente(@RequestBody Cliente cliente){
        try{
            clienteService.novoCliente(cliente);

            return ResponseEntity.status(201).body(true);

        } catch (Exception e)
        {
            return ResponseEntity.status(400).body("Ocorreu um erro:\n"+e.getMessage());
        }
    }
}
