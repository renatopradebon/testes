package controller;


import service.facade.ArtefatoFacade;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.ResponseBuilder;
import controller.util.Page;
import controller.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import py.com.datapar.maestro.domain.produto.model.Artefato;

/**
 * REST controller for managing Artefato.
 */
@Api(value = "/api/artefato", description = "Artefato Controller")
@Path("/api/artefato")
@Secured
public class ArtefatoController {

    @Inject
    private Logger log;

    @Inject
    private ArtefatoFacade artefatoFacade;

    /**
     * POST : Create a new artefato.
     *
     * @param artefato the artefato to create
     * @return the Response with status 201 (Created) and with body the new
     * artefato, or with status 400 (Bad Request) if the artefato has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new artefato", notes = "Create a new artefato")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createArtefato(Artefato artefato) throws URISyntaxException {
        log.debug("REST request to save Artefato : {}", artefato);
        artefatoFacade.create(artefato);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/artefato/" + artefato.getId())),
                "artefato", artefato.getId())
                .entity(artefato).build();
    }

    /**
     * PUT : Updates an existing artefato.
     *
     * @param artefato the artefato to update
     * @return the Response with status 200 (OK) and with body the updated
     * artefato, or with status 400 (Bad Request) if the artefato is not valid,
     * or with status 500 (Internal Server Error) if the artefato couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update artefato", notes = "Updates an existing artefato")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateArtefato(Artefato artefato) throws URISyntaxException {
        log.debug("REST request to update Artefato : {}", artefato);
        artefatoFacade.edit(artefato);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "artefato", artefato.getId().toString())
                .entity(artefato).build();
    }

    /**
     * GET : get all the artefatoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of artefatoes in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the artefatoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllArtefatoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Artefatoes");
        List<Artefato> artefatoes = artefatoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(artefatoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, artefatoFacade.count()), "/resources/api/artefato");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" artefato.
     *
     * @param id the id of the artefato to retrieve
     * @return the Response with status 200 (OK) and with body the artefato, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the artefato")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getArtefato(@PathParam("id") String id) {
        log.debug("REST request to get Artefato : {}", id);
        Artefato artefato = artefatoFacade.find(id);
        return Optional.ofNullable(artefato)
                .map(result -> Response.status(Response.Status.OK).entity(artefato).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" artefato.
     *
     * @param id the id of the artefato to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the artefato")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeArtefato(@PathParam("id") String id) {
        log.debug("REST request to delete Artefato : {}", id);
        artefatoFacade.remove(artefatoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "artefato", id.toString()).build();
    }

}
