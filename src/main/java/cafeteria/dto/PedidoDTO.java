package cafeteria.dto;

import java.math.BigDecimal;

public record PedidoDTO(
        BigDecimal valor,
        Long cliente_id,
        Long atendente_id
) {}
