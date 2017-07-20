package controller;

import service.facade.DeliveryOrderAlvoServicoFacade;
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
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrderAlvoServico;

/**
 * REST controller for managing DeliveryOrderAlvoServico.
 */
@Api(value = "/api/delivery-order-alvo-servico", description = "Delivery Order Alvo Servico Controller")
@Path("/api/delivery-order-alvo-servico")
@Secured
public class DeliveryOrderAlvoServicoController {

    @Inject
    private Logger log;

    @Inject
    private DeliveryOrderAlvoServicoFacade deliveryOrderAlvoServicoFacade;

    /**
     * POST : Create a new deliveryOrderAlvoServico.
     *
     * @param deliveryOrderAlvoServico the deliveryOrderAlvoServico to create
     * @return the Response with status 201 (Created) and with body the new
     * deliveryOrderAlvoServico, or with status 400 (Bad Request) if the
     * deliveryOrderAlvoServico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new deliveryOrderAlvoServico", notes = "Create a new deliveryOrderAlvoServico")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createDeliveryOrderAlvoServico(DeliveryOrderAlvoServico deliveryOrderAlvoServico) throws URISyntaxException {
        log.debug("REST request to save DeliveryOrderAlvoServico : {}", deliveryOrderAlvoServico);
        deliveryOrderAlvoServicoFacade.create(deliveryOrderAlvoServico);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/delivery-order-alvo-servico/" + deliveryOrderAlvoServico.getId())),
                "deliveryOrderAlvoServico", deliveryOrderAlvoServico.getId())
                .entity(deliveryOrderAlvoServico).build();
    }

    /**
     * PUT : Updates an existing deliveryOrderAlvoServico.
     *
     * @param deliveryOrderAlvoServico the deliveryOrderAlvoServico to update
     * @return the Response with status 200 (OK) and with body the updated
     * deliveryOrderAlvoServico, or with status 400 (Bad Request) if the
     * deliveryOrderAlvoServico is not valid, or with status 500 (Internal
     * Server Error) if the deliveryOrderAlvoServico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update deliveryOrderAlvoServico", notes = "Updates an existing deliveryOrderAlvoServico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateDeliveryOrderAlvoServico(DeliveryOrderAlvoServico deliveryOrderAlvoServico) throws URISyntaxException {
        log.debug("REST request to update DeliveryOrderAlvoServico : {}", deliveryOrderAlvoServico);
        deliveryOrderAlvoServicoFacade.edit(deliveryOrderAlvoServico);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "deliveryOrderAlvoServico", deliveryOrderAlvoServico.getId().toString())
                .entity(deliveryOrderAlvoServico).build();
    }

    /**
     * GET : get all the deliveryOrderAlvoServicoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * deliveryOrderAlvoServicoes in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the deliveryOrderAlvoServicoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllDeliveryOrderAlvoServicoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all DeliveryOrderAlvoServicoes");
        List<DeliveryOrderAlvoServico> deliveryOrderAlvoServicoes = deliveryOrderAlvoServicoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(deliveryOrderAlvoServicoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, deliveryOrderAlvoServicoFacade.count()), "/resources/api/delivery-order-alvo-servico");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" deliveryOrderAlvoServico.
     *
     * @param id the id of the deliveryOrderAlvoServico to retrieve
     * @return the Response with status 200 (OK) and with body the
     * deliveryOrderAlvoServico, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the deliveryOrderAlvoServico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getDeliveryOrderAlvoServico(@PathParam("id") String id) {
        log.debug("REST request to get DeliveryOrderAlvoServico : {}", id);
        DeliveryOrderAlvoServico deliveryOrderAlvoServico = deliveryOrderAlvoServicoFacade.find(id);
        return Optional.ofNullable(deliveryOrderAlvoServico)
                .map(result -> Response.status(Response.Status.OK).entity(deliveryOrderAlvoServico).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" deliveryOrderAlvoServico.
     *
     * @param id the id of the deliveryOrderAlvoServico to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the deliveryOrderAlvoServico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeDeliveryOrderAlvoServico(@PathParam("id") String id) {
        log.debug("REST request to delete DeliveryOrderAlvoServico : {}", id);
        deliveryOrderAlvoServicoFacade.remove(deliveryOrderAlvoServicoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "deliveryOrderAlvoServico", id.toString()).build();
    }

}
