package controller;

import service.facade.ProdutoFacade;
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
import py.com.datapar.maestro.domain.produto.model.Produto;

/**
 * REST controller for managing Produto.
 */
@Api(value = "/api/produto", description = "Produto Controller")
@Path("/api/produto")
@Secured
public class ProdutoController {

    @Inject
    private Logger log;

    @Inject
    private ProdutoFacade produtoFacade;

    /**
     * POST : Create a new produto.
     *
     * @param produto the produto to create
     * @return the Response with status 201 (Created) and with body the new
     * produto, or with status 400 (Bad Request) if the produto has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new produto", notes = "Create a new produto")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createProduto(Produto produto) throws URISyntaxException {
        log.debug("REST request to save Produto : {}", produto);
        produtoFacade.create(produto);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/produto/" + produto.getId())),
                "produto", produto.getId())
                .entity(produto).build();
    }

    /**
     * PUT : Updates an existing produto.
     *
     * @param produto the produto to update
     * @return the Response with status 200 (OK) and with body the updated
     * produto, or with status 400 (Bad Request) if the produto is not valid, or
     * with status 500 (Internal Server Error) if the produto couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update produto", notes = "Updates an existing produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateProduto(Produto produto) throws URISyntaxException {
        log.debug("REST request to update Produto : {}", produto);
        produtoFacade.edit(produto);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "produto", produto.getId().toString())
                .entity(produto).build();
    }

    /**
     * GET : get all the produtoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of produtoes in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the produtoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllProdutoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Produtoes");
        List<Produto> produtoes = produtoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(produtoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, produtoFacade.count()), "/resources/api/produto");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" produto.
     *
     * @param id the id of the produto to retrieve
     * @return the Response with status 200 (OK) and with body the produto, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getProduto(@PathParam("id") String id) {
        log.debug("REST request to get Produto : {}", id);
        Produto produto = produtoFacade.find(id);
        return Optional.ofNullable(produto)
                .map(result -> Response.status(Response.Status.OK).entity(produto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" produto.
     *
     * @param id the id of the produto to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeProduto(@PathParam("id") String id) {
        log.debug("REST request to delete Produto : {}", id);
        produtoFacade.remove(produtoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "produto", id.toString()).build();
    }

}
