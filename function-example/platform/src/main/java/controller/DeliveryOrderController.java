package controller;

import service.facade.DeliveryOrderFacade;
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
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrder;

/**
 * REST controller for managing DeliveryOrder.
 */
@Api(value = "/api/delivery-order", description = "Delivery Order Controller")
@Path("/api/delivery-order")
@Secured
public class DeliveryOrderController {

    @Inject
    private Logger log;

    @Inject
    private DeliveryOrderFacade deliveryOrderFacade;

    /**
     * POST : Create a new deliveryOrder.
     *
     * @param deliveryOrder the deliveryOrder to create
     * @return the Response with status 201 (Created) and with body the new
     * deliveryOrder, or with status 400 (Bad Request) if the deliveryOrder has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new deliveryOrder", notes = "Create a new deliveryOrder")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createDeliveryOrder(DeliveryOrder deliveryOrder) throws URISyntaxException {
        log.debug("REST request to save DeliveryOrder : {}", deliveryOrder);
        deliveryOrderFacade.create(deliveryOrder);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/delivery-order/" + deliveryOrder.getId())),
                "deliveryOrder", deliveryOrder.getId())
                .entity(deliveryOrder).build();
    }

    /**
     * PUT : Updates an existing deliveryOrder.
     *
     * @param deliveryOrder the deliveryOrder to update
     * @return the Response with status 200 (OK) and with body the updated
     * deliveryOrder, or with status 400 (Bad Request) if the deliveryOrder is
     * not valid, or with status 500 (Internal Server Error) if the
     * deliveryOrder couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update deliveryOrder", notes = "Updates an existing deliveryOrder")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateDeliveryOrder(DeliveryOrder deliveryOrder) throws URISyntaxException {
        log.debug("REST request to update DeliveryOrder : {}", deliveryOrder);
        deliveryOrderFacade.edit(deliveryOrder);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "deliveryOrder", deliveryOrder.getId().toString())
                .entity(deliveryOrder).build();
    }

    /**
     * GET : get all the deliveryOrders.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of deliveryOrders
     * in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the deliveryOrders")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllDeliveryOrders(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all DeliveryOrders");
        List<DeliveryOrder> deliveryOrders = deliveryOrderFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(deliveryOrders);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, deliveryOrderFacade.count()), "/resources/api/delivery-order");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" deliveryOrder.
     *
     * @param id the id of the deliveryOrder to retrieve
     * @return the Response with status 200 (OK) and with body the
     * deliveryOrder, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the deliveryOrder")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getDeliveryOrder(@PathParam("id") String id) {
        log.debug("REST request to get DeliveryOrder : {}", id);
        DeliveryOrder deliveryOrder = deliveryOrderFacade.find(id);
        return Optional.ofNullable(deliveryOrder)
                .map(result -> Response.status(Response.Status.OK).entity(deliveryOrder).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" deliveryOrder.
     *
     * @param id the id of the deliveryOrder to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the deliveryOrder")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeDeliveryOrder(@PathParam("id") String id) {
        log.debug("REST request to delete DeliveryOrder : {}", id);
        deliveryOrderFacade.remove(deliveryOrderFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "deliveryOrder", id.toString()).build();
    }

}
