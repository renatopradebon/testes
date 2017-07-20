package controller;

import repository.ListaRepository;
import py.com.angularjpa.Lista;
import static java.util.Collections.singletonMap;
import java.util.List;
import javax.inject.Inject;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.valid4j.matchers.http.HttpResponseMatchers.hasContentType;
import static org.valid4j.matchers.http.HttpResponseMatchers.hasStatus;

/**
 * Test class for the ListaController REST controller.
 *
 */
public class ListaControllerTest extends ApplicationTest {

    private static final String DEFAULT_NOME = "A";
    private static final String UPDATED_NOME = "B";
    private static final String RESOURCE_PATH = "api/lista";

    @Deployment
    public static WebArchive createDeployment() {
        return buildApplication().addClass(Lista.class).addClass(ListaRepository.class).addClass(ListaController.class);
    }

    private static Lista lista;

    @Inject
    private ListaRepository listaRepository;

    @Test
    @InSequence(1)
    public void createLista() throws Exception {

        int databaseSizeBeforeCreate = listaRepository.findAll().size();

        // Create the Lista
        lista = new Lista();
        lista.setNome(DEFAULT_NOME);
        Response response = target(RESOURCE_PATH).post(json(lista));
        assertThat(response, hasStatus(Status.CREATED));
        lista = response.readEntity(Lista.class);

        // Validate the Lista in the database
        List<Lista> listas = listaRepository.findAll();
        assertThat(listas.size(), is(databaseSizeBeforeCreate + 1));
        Lista testLista = listas.get(listas.size() - 1);
        assertThat(testLista.getNome(), is(DEFAULT_NOME));
    }

    @Test
    @InSequence(2)
    public void getAllListas() throws Exception {

        int databaseSize = listaRepository.findAll().size();
        // Get all the listas
        Response response = target(RESOURCE_PATH).get();
        assertThat(response, hasStatus(Status.OK));
        assertThat(response, hasContentType(MediaType.APPLICATION_JSON_TYPE));

        List<Lista> listas = response.readEntity(List.class);
        assertThat(listas.size(), is(databaseSize));
    }

    @Test
    @InSequence(3)
    public void getLista() throws Exception {

        // Get the lista
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", lista.getId())).get();
        Lista testLista = response.readEntity(Lista.class);
        assertThat(response, hasStatus(Status.OK));
        assertThat(response, hasContentType(MediaType.APPLICATION_JSON_TYPE));
        assertThat(testLista.getId(), is(lista.getId()));
        assertThat(testLista.getNome(), is(DEFAULT_NOME));
    }

    @Test
    @InSequence(4)
    public void getNonExistingLista() throws Exception {

        // Get the lista
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", Long.MAX_VALUE)).get();
        assertThat(response, hasStatus(Status.NOT_FOUND));
    }

    @Test
    @InSequence(5)
    public void updateLista() throws Exception {

        int databaseSizeBeforeUpdate = listaRepository.findAll().size();

        // Update the lista
        Lista updatedLista = new Lista();
        updatedLista.setId(lista.getId());
        updatedLista.setNome(UPDATED_NOME);

        Response response = target(RESOURCE_PATH).put(json(updatedLista));
        assertThat(response, hasStatus(Status.OK));

        // Validate the Lista in the database
        List<Lista> listas = listaRepository.findAll();
        assertThat(listas.size(), is(databaseSizeBeforeUpdate));
        Lista testLista = listas.get(listas.size() - 1);
        assertThat(testLista.getNome(), is(UPDATED_NOME));
    }

    @Test
    @InSequence(6)
    public void removeLista() throws Exception {

        int databaseSizeBeforeDelete = listaRepository.findAll().size();

        // Delete the lista
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", lista.getId())).delete();
        assertThat(response, hasStatus(Status.OK));

        // Validate the database is empty
        List<Lista> listas = listaRepository.findAll();
        assertThat(listas.size(), is(databaseSizeBeforeDelete - 1));
    }

}
