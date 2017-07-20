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
import py.com.datapar.model.Customer;
import service.facade.CustomerFacade;

/**
 *
 * @author Renato Pradebon
 */
@Path("customer")
public class CustomerController {

    @Inject
    private javax.mvc.Models model;
    @Inject
    private CustomerFacade facade;
    @Inject
    private javax.mvc.binding.BindingResult validationResult;
    @Inject
    private ErrorBean error;

    @GET
    @Path("new")
    @javax.mvc.annotation.Controller
    public String emptyCustomer() {
        return "customer/create.jsp";
    }

    @POST
    @Path("new")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String createCustomer(@Valid
            @BeanParam Customer customer) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.create(customer);
        return "redirect:customer/list";
    }

    @GET
    @Path("update/{id}")
    @javax.mvc.annotation.Controller
    public String editCustomer(@PathParam("id") Long id) {
        model.put("CUSTOMER", facade.find(id));
        return "customer/update.jsp";
    }

    @POST
    @Path("update")
    @javax.mvc.annotation.Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String updateCustomer(@Valid
            @BeanParam Customer customer) {
        if (validationResult.isFailed()) {
            return ValidationUtil.getResponse(validationResult, error);
        }
        facade.edit(customer);
        return "redirect:customer/list";
    }

    @GET
    @Path("remove/{id}")
    @javax.mvc.annotation.Controller
    public String removeCustomer(@PathParam("id") Long id) {
        facade.remove(facade.find(id));
        return "redirect:customer/list";
    }

    @GET
    @Path("{id}")
    @javax.mvc.annotation.Controller
    public String findCustomer(@PathParam("id") Long id) {
        model.put("CUSTOMER", facade.find(id));
        return "customer/view.jsp";
    }

    @GET
    @Path("list")
    @javax.mvc.annotation.Controller
    public String findAllCustomer() {
        model.put("CUSTOMER_LIST", facade.findAll());
        return "customer/list.jsp";
    }
    
}
