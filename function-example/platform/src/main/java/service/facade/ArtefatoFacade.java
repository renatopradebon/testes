package service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.inject.Inject;
import py.com.datapar.maestro.domain.produto.model.Artefato;


@Stateless
@Named("artefato")
public class ArtefatoFacade extends AbstractFacade<Artefato, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtefatoFacade() {
        super(Artefato.class);
    }

}
