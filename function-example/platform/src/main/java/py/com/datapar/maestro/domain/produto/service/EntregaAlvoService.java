package py.com.datapar.maestro.domain.produto.service;


import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;
import py.com.datapar.maestro.domain.entrega.repository.EntregaAlvoRepository;
import py.com.datapar.maestro.domain.generico.Repository;
import py.com.datapar.maestro.domain.generico.ServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
@RequestScoped
public class EntregaAlvoService extends ServiceImpl<EntregaAlvoRepository, EntregaAlvo> {

    @Inject
    private EntregaAlvoRepository repository;

    @Override
    public Repository<EntregaAlvo> getRepository() {
        return repository;
    }

    public EntregaAlvoService() {
    }
}
