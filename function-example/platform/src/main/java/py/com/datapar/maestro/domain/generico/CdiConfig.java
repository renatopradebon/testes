package py.com.datapar.maestro.domain.generico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static py.com.datapar.maestro.lib.helper.XMaestro.iiff;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
 * <p>
 * <p>
 * Example injection on a managed bean field:
 * </p>
 * <p>
 * <pre>
 * &#064;Inject
 * private EntityManager em;
 *
 * https://jaxenter.com/top-3-jpa-productivity-boosters-for-java-ee-developers-spring-data-116126.html
 * </pre>
 */

@ApplicationScoped
public class CdiConfig {

    @Produces
    @PersistenceContext(unitName = "primary")
    private EntityManager em;


    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {

        //injectionPoint.getMember().getDeclaringClass().getName();
        //Inject point nao conhece a classe quando injetado manualmente por CDI select
        Object o =
                iiff(injectionPoint, i -> iiff(i.getMember(), member -> iiff(member.getDeclaringClass(), clas -> clas.getName())));
        String className = (o == null) ? "py.com.datapar.maestro.Injected" : (String) o;
        Logger logger = LoggerFactory.getLogger(className);

        return logger;
    }
}
