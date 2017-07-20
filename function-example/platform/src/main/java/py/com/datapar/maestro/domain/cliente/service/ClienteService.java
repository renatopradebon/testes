package py.com.datapar.maestro.domain.cliente.service;

import org.jinq.jpa.JPAJinqStream;
import py.com.datapar.maestro.domain.cliente.model.Cliente;
import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.cliente.repository.ClienteRepository;
import py.com.datapar.maestro.domain.cliente.repository.EmpresaRepository;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
@RequestScoped
public class ClienteService extends ServiceImpl<ClienteRepository, Cliente> {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private EmpresaRepository empresaRepository;

    @Override
    public Repository<Cliente> getRepository() {
        return clienteRepository;
    }

    @Override
    public JPAJinqStream<Cliente> getQuery() {
        return super.getQuery();
//                .where( c-> c.getSituation() != Situation.INATIVO );
    }

    public ClienteService() {
        ruleBuilder()
                .addRule((client -> client.getCodigo() != 0), "Código deve ser preenchido")
        ;

    }

    public List<Cliente> getTodosClientes() {

        return this
                .getQuery()//busca querie pré filtrada
                //.where(c -> c.getId().trim() != "" ) // add filtros ( todo AspectJ
                .toList()
                ;
    }

    public List<Empresa> getTodasEmpresas() {
        return empresaRepository
                .getQuery()
                .toList();
    }
}
