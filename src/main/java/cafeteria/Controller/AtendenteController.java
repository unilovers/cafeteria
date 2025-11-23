package cafeteria.Controller;

import cafeteria.model.entities.Atendente;
import cafeteria.model.service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    private AtendenteService atendenteService;

    @Autowired
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    // Lista total de atendentes
    @GetMapping
    public ResponseEntity<List<Atendente>> getAtendentes() {
        List<Atendente> atendentes = atendenteService.getAtendentes();
        return ResponseEntity.status(200).body(atendentes);
    }

    // Consultas por nome
    @GetMapping("/consulta")
    public ResponseEntity<?> consultaAtendente(@RequestParam(value = "nome") String nome) {
        try {
            List<Atendente> atendentesEncontrados;
            atendentesEncontrados = atendenteService.consultaAtendenteNome(nome);
            return ResponseEntity.ok(atendentesEncontrados);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Consultas por CPF
    @GetMapping("/consulta/cpf")
    public ResponseEntity<?> consultaAtendenteCpf(@RequestParam(value = "cpf") String cpf) {
        try {
            // Assumindo que retorna Optional como no ClienteController
            Optional<Atendente> atendenteEncontrado; 
            atendenteEncontrado = atendenteService.consultaAtendenteCpf(cpf);
            return ResponseEntity.ok(atendenteEncontrado);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Delete por ID
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> apagarAtendenteId(@PathVariable("id") Integer id) {
        try {
            atendenteService.apagarAtendenteId(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Atualizar
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarAtendente(@RequestBody Atendente atendente) {
        try {
            atendenteService.atualizarAtendente(atendente);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Create (Novo Atendente)
    @PostMapping("/novo")
    public ResponseEntity<?> novoAtendente(@RequestBody Atendente atendente) {
        try {
            atendenteService.novoAtendente(atendente);
            return ResponseEntity.status(201).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Ocorreu um erro:\n" + e.getMessage());
        }
    }
}
