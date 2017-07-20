package controller;

import service.facade.ProdutoAppVersaoFacade;
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
import py.com.datapar.maestro.domain.produto.model.ProdutoAppVersao;

/**
 * REST controller for managing ProdutoAppVersao.
 */
@Api(value = "/api/produto-app-versao", description = "Produto App Versao Controller")
@Path("/api/produto-app-versao")
@Secured
public class ProdutoAppVersaoController {

    @Inject
    private Logger log;

    @Inject
    private ProdutoAppVersaoFacade produtoAppVersaoFacade;

    /**
     * POST : Create a new produtoAppVersao.
     *
     * @param produtoAppVersao the produtoAppVersao to create
     * @return the Response with status 201 (Created) and with body the new
     * produtoAppVersao, or with status 400 (Bad Request) if the
     * produtoAppVersao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new produtoAppVersao", notes = "Create a new produtoAppVersao")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createProdutoAppVersao(ProdutoAppVersao produtoAppVersao) throws URISyntaxException {
        log.debug("REST request to save ProdutoAppVersao : {}", produtoAppVersao);
        produtoAppVersaoFacade.create(produtoAppVersao);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/produto-app-versao/" + produtoAppVersao.getId())),
                "produtoAppVersao", produtoAppVersao.getId())
                .entity(produtoAppVersao).build();
    }

    /**
     * PUT : Updates an existing produtoAppVersao.
     *
     * @param produtoAppVersao the produtoAppVersao to update
     * @return the Response with status 200 (OK) and with body the updated
     * produtoAppVersao, or with status 400 (Bad Request) if the
     * produtoAppVersao is not valid, or with status 500 (Internal Server Error)
     * if the produtoAppVersao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update produtoAppVersao", notes = "Updates an existing produtoAppVersao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateProdutoAppVersao(ProdutoAppVersao produtoAppVersao) throws URISyntaxException {
        log.debug("REST request to update ProdutoAppVersao : {}", produtoAppVersao);
        produtoAppVersaoFacade.edit(produtoAppVersao);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "produtoAppVersao", produtoAppVersao.getId().toString())
                .entity(produtoAppVersao).build();
    }

    /**
     * GET : get all the produtoAppVersaoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * produtoAppVersaoes in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the produtoAppVersaoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllProdutoAppVersaoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ProdutoAppVersaoes");
        List<ProdutoAppVersao> produtoAppVersaoes = produtoAppVersaoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(produtoAppVersaoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, produtoAppVersaoFacade.count()), "/resources/api/produto-app-versao");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" produtoAppVersao.
     *
     * @param id the id of the produtoAppVersao to retrieve
     * @return the Response with status 200 (OK) and with body the
     * produtoAppVersao, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the produtoAppVersao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getProdutoAppVersao(@PathParam("id") String id) {
        log.debug("REST request to get ProdutoAppVersao : {}", id);
        ProdutoAppVersao produtoAppVersao = produtoAppVersaoFacade.find(id);
        return Optional.ofNullable(produtoAppVersao)
                .map(result -> Response.status(Response.Status.OK).entity(produtoAppVersao).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" produtoAppVersao.
     *
     * @param id the id of the produtoAppVersao to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the produtoAppVersao")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeProdutoAppVersao(@PathParam("id") String id) {
        log.debug("REST request to delete ProdutoAppVersao : {}", id);
        produtoAppVersaoFacade.remove(produtoAppVersaoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "produtoAppVersao", id.toString()).build();
    }

}
