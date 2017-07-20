package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.entrega.model.ConteudoDb;

@Stateless
@Named("conteudoDb")
public class ConteudoDbFacade extends AbstractFacade<ConteudoDb, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConteudoDbFacade() {
        super(ConteudoDb.class);
    }

}
