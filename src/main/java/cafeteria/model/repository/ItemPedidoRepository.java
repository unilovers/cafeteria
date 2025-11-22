package cafeteria.model.repository;

import cafeteria.model.entities.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Integer>{
    //busca itens de um pedido específico
    List<ItemPedido> findByPedidoId(Integer pedidoId);
}
