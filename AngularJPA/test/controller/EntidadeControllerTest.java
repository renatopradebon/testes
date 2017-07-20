package controller;

import repository.EntidadeRepository;
import py.com.angularjpa.Entidade;
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
 * Test class for the EntidadeController REST controller.
 *
 */
public class EntidadeControllerTest extends ApplicationTest {

    private static final String DEFAULT_NOME = "A";
    private static final String UPDATED_NOME = "B";
    private static final String RESOURCE_PATH = "api/entidade";

    @Deployment
    public static WebArchive createDeployment() {
        return buildApplication().addClass(Entidade.class).addClass(Lista.class).addClass(EntidadeRepository.class).addClass(EntidadeController.class);
    }

    private static Entidade entidade;

    @Inject
    private EntidadeRepository entidadeRepository;

    @Test
    @InSequence(1)
    public void createEntidade() throws Exception {

        int databaseSizeBeforeCreate = entidadeRepository.findAll().size();

        // Create the Entidade
        entidade = new Entidade();
        entidade.setNome(DEFAULT_NOME);
        Response response = target(RESOURCE_PATH).post(json(entidade));
        assertThat(response, hasStatus(Status.CREATED));
        entidade = response.readEntity(Entidade.class);

        // Validate the Entidade in the database
        List<Entidade> entidades = entidadeRepository.findAll();
        assertThat(entidades.size(), is(databaseSizeBeforeCreate + 1));
        Entidade testEntidade = entidades.get(entidades.size() - 1);
        assertThat(testEntidade.getNome(), is(DEFAULT_NOME));
    }

    @Test
    @InSequence(2)
    public void getAllEntidades() throws Exception {

        int databaseSize = entidadeRepository.findAll().size();
        // Get all the entidades
        Response response = target(RESOURCE_PATH).get();
        assertThat(response, hasStatus(Status.OK));
        assertThat(response, hasContentType(MediaType.APPLICATION_JSON_TYPE));

        List<Entidade> entidades = response.readEntity(List.class);
        assertThat(entidades.size(), is(databaseSize));
    }

    @Test
    @InSequence(3)
    public void getEntidade() throws Exception {

        // Get the entidade
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", entidade.getId())).get();
        Entidade testEntidade = response.readEntity(Entidade.class);
        assertThat(response, hasStatus(Status.OK));
        assertThat(response, hasContentType(MediaType.APPLICATION_JSON_TYPE));
        assertThat(testEntidade.getId(), is(entidade.getId()));
        assertThat(testEntidade.getNome(), is(DEFAULT_NOME));
    }

    @Test
    @InSequence(4)
    public void getNonExistingEntidade() throws Exception {

        // Get the entidade
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", Long.MAX_VALUE)).get();
        assertThat(response, hasStatus(Status.NOT_FOUND));
    }

    @Test
    @InSequence(5)
    public void updateEntidade() throws Exception {

        int databaseSizeBeforeUpdate = entidadeRepository.findAll().size();

        // Update the entidade
        Entidade updatedEntidade = new Entidade();
        updatedEntidade.setId(entidade.getId());
        updatedEntidade.setNome(UPDATED_NOME);

        Response response = target(RESOURCE_PATH).put(json(updatedEntidade));
        assertThat(response, hasStatus(Status.OK));

        // Validate the Entidade in the database
        List<Entidade> entidades = entidadeRepository.findAll();
        assertThat(entidades.size(), is(databaseSizeBeforeUpdate));
        Entidade testEntidade = entidades.get(entidades.size() - 1);
        assertThat(testEntidade.getNome(), is(UPDATED_NOME));
    }

    @Test
    @InSequence(6)
    public void removeEntidade() throws Exception {

        int databaseSizeBeforeDelete = entidadeRepository.findAll().size();

        // Delete the entidade
        Response response = target(RESOURCE_PATH + "/{id}", singletonMap("id", entidade.getId())).delete();
        assertThat(response, hasStatus(Status.OK));

        // Validate the database is empty
        List<Entidade> entidades = entidadeRepository.findAll();
        assertThat(entidades.size(), is(databaseSizeBeforeDelete - 1));
    }

}
