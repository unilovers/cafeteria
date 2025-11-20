package cafeteria.model.service;

import cafeteria.model.repository.ClienteRepository;
import cafeteria.model.entities.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService  {

    @Autowired
    private ClienteRepository clienteRepository;

    //Métodos de Crud.

    //create
    public boolean novoCliente(Cliente cliente) throws Exception{
        if (cliente.getNome()==null|| cliente.getNome().isEmpty()){
            throw new Exception("O nome precisa ser preenchido");

        }

        if (cliente.getEndereco()==null|| cliente.getEndereco().isEmpty()){
            throw new Exception("O endereço precisa ser preenchido");

        }
        if (cliente.getCpf()==null|| cliente.getCpf().isEmpty()){
            throw new Exception("O cpf precisa ser preenchido");

        }
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent() ){
            throw new Exception("Este CPF já foi cadastrado");

        }
        clienteRepository.save(cliente);
        return true;
    }


    //list total clientes
    public List<Cliente> getClientes(){
        Iterable<Cliente> clientes = clienteRepository.findAll();
        return (List<Cliente>) clientes;
    }


    //consultas
    public List<Cliente> consultaClienteNome(String nome) throws Exception {
        List<Cliente> clientesEncontrados = clienteRepository.findByNomeContainingIgnoreCase(nome);
        if (clientesEncontrados.isEmpty()) {
            throw new Exception("Nenhum cliente encontrado com as letras: " + nome);
        }
        return clientesEncontrados;
    }

    public List<Cliente> consultaClienteEndereco(String endereco) throws Exception {
        List<Cliente> clientesEncontrados = clienteRepository.findByEnderecoContainingIgnoreCase(endereco);
        if (clientesEncontrados.isEmpty()) {
            throw new Exception("Nenhum cliente encontrado no endereço: " + endereco);
        }
        return clientesEncontrados;
    }

    public Optional<Cliente> consultaClienteCpf(String cpf) throws Exception{
        Optional<Cliente> clienteEncontrado = clienteRepository.findByCpf(cpf);
        if (clienteEncontrado.isEmpty()) {
            throw new Exception("Nenhum cliente com esse cpf: "+cpf);
        }
        return clienteEncontrado;
    }

    //Delete
    public void apagarClienteId(Integer id) throws Exception {
        if (clienteRepository.findById(id).isEmpty()){
            throw new Exception("Cliente com o id " + id + " não encontrado");
        }
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("ID não existente");
        }
    }

    @Transactional
    public void apagarClienteCpf(String cpf) throws Exception {
        if (clienteRepository.findByCpf(cpf).isEmpty()){
            throw new Exception("Cliente com o cpf " + cpf + " não encontrado");
        }
        try {
            clienteRepository.deleteByCpf(cpf);
        } catch (Exception e) {
            throw new Exception("CPF não existente");
        }
    }
    //atualizar

    public void atualizarCliente(Cliente cliente) throws Exception{
        Optional<Cliente> clienteBanco = clienteRepository.findById(cliente.getId());
        if (clienteBanco.isPresent()) {

            Cliente clienteAtualizar = clienteBanco.get();
            if (cliente.getCpf().isEmpty()&&cliente.getNome().isEmpty()&&cliente.getEndereco().isEmpty()){
                throw new Exception("Campos vazios.");
            }

            if (cliente.getCpf()==null||cliente.getCpf().isEmpty()){
                cliente.setCpf(clienteAtualizar.getCpf());
            }
            if (cliente.getNome() == null||cliente.getNome().isEmpty()){
                cliente.setNome(clienteAtualizar.getNome());
            }
            if (cliente.getEndereco()==null||cliente.getEndereco().isEmpty()){
                cliente.setEndereco(clienteAtualizar.getEndereco());
            }

            try {
                clienteRepository.save(cliente);
            } catch (Exception e) {
                throw new Exception("Erro ao atualizar o cliente: " + cliente.getNome());
            }
        } else {
            throw new Exception("Cliente não existe no banco");
        }
    }


    //Métodos Service
    public boolean exiteClienteId (Cliente cliente) {
        return clienteRepository.findById(cliente.getId()).isPresent();

    }
    public boolean exiteClienteCpf (Cliente cliente) {
        return clienteRepository.findByCpf(cliente.getCpf()).isPresent();

    }

    public void fazerPedido() {}
    //precisa Ter um pedido
    public void cancelarPedido() {}
    //precisa estar finalizando a compra
    public void darGorjeta(){}
    //precisa ter um pedido
    public void pagar(){}
}
