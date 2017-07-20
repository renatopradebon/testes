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
import py.com.datapar.model.ProductOrder;
import service.facade.ProductOrderFacade;

/**
 *
 * @author Renato Pradebon
 */
@Path("productOrder")
public class ProductOrderController {

    @Inject
    private javax.mvc.Models model;
    @Inject
    private ProductOrderFacade facade;
    @Inject
    private javax.mvc.binding.BindingResult validationResult;
    @Inject
    private ErrorBean error;

    @GET
    @Path("new")
    @javax.mvc.annotation.Controller
    public String emptyProductOrder() {
        return "productOrder/create.jsp";
    }

    @POST
    @Path("new")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String createProductOrder(@Valid
            @BeanParam ProductOrder productOrder) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.create(productOrder);
        return "redirect:productOrder/list";
    }

    @GET
    @Path("update/{id}")
    @javax.mvc.annotation.Controller
    public String editProductOrder(@PathParam("id") Long id) {
        model.put("PRODUCTORDER", facade.find(id));
        return "productOrder/update.jsp";
    }

    @POST
    @Path("update")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String updateProductOrder(@Valid
            @BeanParam ProductOrder productOrder) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.edit(productOrder);
        return "redirect:productOrder/list";
    }

    @GET
    @Path("remove/{id}")
    @javax.mvc.annotation.Controller
    public String removeProductOrder(@PathParam("id") Long id) {
        facade.remove(facade.find(id));
        return "redirect:productOrder/list";
    }

    @GET
    @Path("{id}")
    @javax.mvc.annotation.Controller
    public String findProductOrder(@PathParam("id") Long id) {
        model.put("PRODUCTORDER", facade.find(id));
        return "productOrder/view.jsp";
    }

    @GET
    @Path("list")
    @javax.mvc.annotation.Controller
    public String findAllProductOrder() {
        model.put("PRODUCTORDER_LIST", facade.findAll());
        return "productOrder/list.jsp";
    }
    
}
