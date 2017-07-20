package py.com.datapar.maestro.domain.cliente.service;

import py.com.datapar.maestro.domain.cliente.model.Empresa;
import py.com.datapar.maestro.domain.cliente.repository.EmpresaRepository;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@RequestScoped
public class ClienteEmpresaService extends ServiceImpl<EmpresaRepository, Empresa> {

    @Inject
    private EmpresaRepository empresaRepository;

    @Override
    public Repository<Empresa> getRepository() {
        return empresaRepository;
    }
}
