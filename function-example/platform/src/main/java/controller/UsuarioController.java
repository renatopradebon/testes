package controller;

import service.facade.UsuarioFacade;
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
import py.com.datapar.maestro.domain.commons.model.Usuario;

/**
 * REST controller for managing Usuario.
 */
@Api(value = "/api/usuario", description = "Usuario Controller")
@Path("/api/usuario")
@Secured
public class UsuarioController {

    @Inject
    private Logger log;

    @Inject
    private UsuarioFacade usuarioFacade;

    /**
     * POST : Create a new usuario.
     *
     * @param usuario the usuario to create
     * @return the Response with status 201 (Created) and with body the new
     * usuario, or with status 400 (Bad Request) if the usuario has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new usuario", notes = "Create a new usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createUsuario(Usuario usuario) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuario);
        usuarioFacade.create(usuario);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/usuario/" + usuario.getId())),
                "usuario", usuario.getId())
                .entity(usuario).build();
    }

    /**
     * PUT : Updates an existing usuario.
     *
     * @param usuario the usuario to update
     * @return the Response with status 200 (OK) and with body the updated
     * usuario, or with status 400 (Bad Request) if the usuario is not valid, or
     * with status 500 (Internal Server Error) if the usuario couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update usuario", notes = "Updates an existing usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateUsuario(Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}", usuario);
        usuarioFacade.edit(usuario);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "usuario", usuario.getId().toString())
                .entity(usuario).build();
    }

    /**
     * GET : get all the usuarioes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of usuarioes in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the usuarioes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllUsuarioes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Usuarioes");
        List<Usuario> usuarioes = usuarioFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(usuarioes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, usuarioFacade.count()), "/resources/api/usuario");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" usuario.
     *
     * @param id the id of the usuario to retrieve
     * @return the Response with status 200 (OK) and with body the usuario, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getUsuario(@PathParam("id") String id) {
        log.debug("REST request to get Usuario : {}", id);
        Usuario usuario = usuarioFacade.find(id);
        return Optional.ofNullable(usuario)
                .map(result -> Response.status(Response.Status.OK).entity(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" usuario.
     *
     * @param id the id of the usuario to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeUsuario(@PathParam("id") String id) {
        log.debug("REST request to delete Usuario : {}", id);
        usuarioFacade.remove(usuarioFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "usuario", id.toString()).build();
    }

}
