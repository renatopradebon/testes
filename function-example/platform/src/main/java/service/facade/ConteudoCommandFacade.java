package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.ConteudoCommand;

@Stateless
@Named("conteudoCommand")
public class ConteudoCommandFacade extends AbstractFacade<ConteudoCommand, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConteudoCommandFacade() {
        super(ConteudoCommand.class);
    }

}
