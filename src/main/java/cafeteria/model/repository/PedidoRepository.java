package cafeteria.Model.Repository;

import cafeteria.Model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findById(long id);

    void deleteById(long id);

    List<Pedido> findPedidoByCliente_Nome(String clienteNome);

    List<Pedido> findPedidoByAtendente_Nome(String atendenteNome);

}
