package controller;

import py.com.angularjpa.Entidade;
import repository.EntidadeRepository;
import controller.util.HeaderUtil;
import app.security.Secured;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST controller for managing Entidade.
 */
@Api(value = "/api/entidade", description = "Entidade Controller")
@Path("/api/entidade")
@Secured
public class EntidadeController {

    @Inject
    private Logger log;

    @Inject
    private EntidadeRepository entidadeRepository;

    /**
     * POST : Create a new entidade.
     *
     * @param entidade the entidade to create
     * @return the Response with status 201 (Created) and with body the new
     * entidade, or with status 400 (Bad Request) if the entidade has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new entidade", notes = "Create a new entidade")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createEntidade(Entidade entidade) throws URISyntaxException {
        log.debug("REST request to save Entidade : {}", entidade);
        entidadeRepository.create(entidade);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/entidade/" + entidade.getId())),
                "entidade", entidade.getId().toString())
                .entity(entidade).build();
    }

    /**
     * PUT : Updates an existing entidade.
     *
     * @param entidade the entidade to update
     * @return the Response with status 200 (OK) and with body the updated
     * entidade, or with status 400 (Bad Request) if the entidade is not valid,
     * or with status 500 (Internal Server Error) if the entidade couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update entidade", notes = "Updates an existing entidade")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateEntidade(Entidade entidade) throws URISyntaxException {
        log.debug("REST request to update Entidade : {}", entidade);
        entidadeRepository.edit(entidade);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "entidade", entidade.getId().toString())
                .entity(entidade).build();
    }

    /**
     * GET : get all the entidades.
     *
     * @return the Response with status 200 (OK) and the list of entidades in
     * body
     *
     */
    @Timed
    @ApiOperation(value = "get all the entidades")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public List<Entidade> getAllEntidades() {
        log.debug("REST request to get all Entidades");
        List<Entidade> entidades = entidadeRepository.findAll();
        return entidades;
    }

    /**
     * GET /:id : get the "id" entidade.
     *
     * @param id the id of the entidade to retrieve
     * @return the Response with status 200 (OK) and with body the entidade, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the entidade")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getEntidade(@PathParam("id") Long id) {
        log.debug("REST request to get Entidade : {}", id);
        Entidade entidade = entidadeRepository.find(id);
        return Optional.ofNullable(entidade)
                .map(result -> Response.status(Response.Status.OK).entity(entidade).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" entidade.
     *
     * @param id the id of the entidade to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the entidade")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeEntidade(@PathParam("id") Long id) {
        log.debug("REST request to delete Entidade : {}", id);
        entidadeRepository.remove(entidadeRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "entidade", id.toString()).build();
    }

}
