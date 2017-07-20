package controller;

import service.facade.ProdutoDbRevisionFacade;
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
import py.com.datapar.maestro.domain.produto.model.ProdutoDbRevision;

/**
 * REST controller for managing ProdutoDbRevision.
 */
@Api(value = "/api/produto-db-revision", description = "Produto Db Revision Controller")
@Path("/api/produto-db-revision")
@Secured
public class ProdutoDbRevisionController {

    @Inject
    private Logger log;

    @Inject
    private ProdutoDbRevisionFacade produtoDbRevisionFacade;

    /**
     * POST : Create a new produtoDbRevision.
     *
     * @param produtoDbRevision the produtoDbRevision to create
     * @return the Response with status 201 (Created) and with body the new
     * produtoDbRevision, or with status 400 (Bad Request) if the
     * produtoDbRevision has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new produtoDbRevision", notes = "Create a new produtoDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createProdutoDbRevision(ProdutoDbRevision produtoDbRevision) throws URISyntaxException {
        log.debug("REST request to save ProdutoDbRevision : {}", produtoDbRevision);
        produtoDbRevisionFacade.create(produtoDbRevision);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/produto-db-revision/" + produtoDbRevision.getId())),
                "produtoDbRevision", produtoDbRevision.getId())
                .entity(produtoDbRevision).build();
    }

    /**
     * PUT : Updates an existing produtoDbRevision.
     *
     * @param produtoDbRevision the produtoDbRevision to update
     * @return the Response with status 200 (OK) and with body the updated
     * produtoDbRevision, or with status 400 (Bad Request) if the
     * produtoDbRevision is not valid, or with status 500 (Internal Server
     * Error) if the produtoDbRevision couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update produtoDbRevision", notes = "Updates an existing produtoDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateProdutoDbRevision(ProdutoDbRevision produtoDbRevision) throws URISyntaxException {
        log.debug("REST request to update ProdutoDbRevision : {}", produtoDbRevision);
        produtoDbRevisionFacade.edit(produtoDbRevision);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "produtoDbRevision", produtoDbRevision.getId().toString())
                .entity(produtoDbRevision).build();
    }

    /**
     * GET : get all the produtoDbRevisions.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * produtoDbRevisions in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the produtoDbRevisions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllProdutoDbRevisions(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ProdutoDbRevisions");
        List<ProdutoDbRevision> produtoDbRevisions = produtoDbRevisionFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(produtoDbRevisions);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, produtoDbRevisionFacade.count()), "/resources/api/produto-db-revision");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" produtoDbRevision.
     *
     * @param id the id of the produtoDbRevision to retrieve
     * @return the Response with status 200 (OK) and with body the
     * produtoDbRevision, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the produtoDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getProdutoDbRevision(@PathParam("id") String id) {
        log.debug("REST request to get ProdutoDbRevision : {}", id);
        ProdutoDbRevision produtoDbRevision = produtoDbRevisionFacade.find(id);
        return Optional.ofNullable(produtoDbRevision)
                .map(result -> Response.status(Response.Status.OK).entity(produtoDbRevision).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" produtoDbRevision.
     *
     * @param id the id of the produtoDbRevision to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the produtoDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeProdutoDbRevision(@PathParam("id") String id) {
        log.debug("REST request to delete ProdutoDbRevision : {}", id);
        produtoDbRevisionFacade.remove(produtoDbRevisionFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "produtoDbRevision", id.toString()).build();
    }

}
