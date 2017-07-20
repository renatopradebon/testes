/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.datapar.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import py.com.datapar.controller.util.ErrorBean;
import py.com.datapar.controller.util.ValidationUtil;
import py.com.datapar.model.Product;
import service.facade.ProductFacade;

/**
 *
 * @author Renato Pradebon
 */
@Path("product")
public class ProductController {

    @Inject
    private javax.mvc.Models model;
    @Inject
    private ProductFacade facade;
    @Inject
    private javax.mvc.binding.BindingResult validationResult;
    @Inject
    private ErrorBean error;

    @GET
    @Path("new")
    @javax.mvc.annotation.Controller
    public String emptyProduct() {
        return "product/create.jsp";
    }

    @POST
    @Path("new")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String createProduct(@Valid
            @BeanParam Product product) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.create(product);
        return "redirect:product/list";
    }

    @GET
    @Path("update/{id}")
    @javax.mvc.annotation.Controller
    public String editProduct(@PathParam("id") Long id) {
        model.put("PRODUCT", facade.find(id));
        return "product/update.jsp";
    }

    @POST
    @Path("update")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String updateProduct(@Valid
            @BeanParam Product product) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.edit(product);
        return "redirect:product/list";
    }

    @GET
    @Path("remove/{id}")
    @javax.mvc.annotation.Controller
    public String removeProduct(@PathParam("id") Long id) {
        facade.remove(facade.find(id));
        return "redirect:product/list";
    }

    @GET
    @Path("{id}")
    @javax.mvc.annotation.Controller
    public String findProduct(@PathParam("id") Long id) {
        model.put("PRODUCT", facade.find(id));
        return "product/view.jsp";
    }

    @GET
    @Path("list")
    @javax.mvc.annotation.Controller
    public String findAllProduct() {
        model.put("PRODUCT_LIST", facade.findAll());
        return "product/list.jsp";
    }
    
}
