package cafeteria.model.service;

import cafeteria.model.entities.ItemPedido;
import cafeteria.model.entities.Pedido;
import cafeteria.model.entities.Produto;
import cafeteria.model.repository.ItemPedidoRepository;
import cafeteria.model.repository.PedidoRepository;
import cafeteria.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemPedido adicionarItem(Integer pedidoId, Long produtoId, Integer quantidade) throws Exception {
        //pega pelo pedido
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new Exception("Pedido não encontrado com ID: "+pedidoId));

        //busca o produto
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(() -> new Exception("Produto não encontrado com ID: "+produtoId));

        //cria o objeto
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);

        //calcula o total e salva no banco
        item.calcularSubTotal();
        return itemRepository.save(item);
    }

    public List<ItemPedido> listarItensDoPedido(Integer pedidoId) {
        return itemRepository.findByPedidoId(pedidoId);
    }
}
