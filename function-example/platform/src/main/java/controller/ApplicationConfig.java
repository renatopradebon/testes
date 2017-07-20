/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.metrics.MetricsConfigurer;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

/**
 *
 * @author Renato Pradebon
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Inject
    private MetricsConfigurer metricsConfigurer;

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        instances.add(new JacksonJsonProvider());
        instances.add(new InstrumentedResourceMethodApplicationListener(metricsConfigurer.getMetricRegistry()));
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(app.metrics.DiagnosticFilter.class);
        resources.add(app.security.SecurityUtils.class);
        resources.add(app.security.jwt.JWTAuthenticationFilter.class);
        resources.add(controller.AccountController.class);
        resources.add(controller.ArtefatoController.class);
        resources.add(controller.ClienteController.class);
        resources.add(controller.ConfigSchemaAppRevisionController.class);
        resources.add(controller.ConfigSchemaController.class);
        resources.add(controller.ConfigSchemaDbRevisionController.class);
        resources.add(controller.ConteudoAppController.class);
        resources.add(controller.ConteudoCommandController.class);
        resources.add(controller.ConteudoDbController.class);
        resources.add(controller.DeliveryOrderAlvoServicoController.class);
        resources.add(controller.DeliveryOrderController.class);
        resources.add(controller.EmpresaController.class);
        resources.add(controller.EntregaAlvoController.class);
        resources.add(controller.EntregaController.class);
        resources.add(controller.EstagioExecucaoEntregaController.class);
        resources.add(controller.FilialController.class);
        resources.add(controller.LogsResource.class);
        resources.add(controller.MaestroAppConfigController.class);
        resources.add(controller.PlanoClassificadorController.class);
        resources.add(controller.PlanoController.class);
        resources.add(controller.ProdutoAppVersaoController.class);
        resources.add(controller.ProdutoController.class);
        resources.add(controller.ProdutoDbRevisionController.class);
        resources.add(controller.ServicoAppController.class);
        resources.add(controller.ServicoDbController.class);
        resources.add(controller.ServidorController.class);
        resources.add(controller.UserController.class);
        resources.add(controller.UserJWTController.class);
        resources.add(controller.UsuarioController.class);
        resources.add(org.jboss.resteasy.client.exception.mapper.ApacheHttpClient4ExceptionMapper.class);
        resources.add(org.jboss.resteasy.core.AcceptHeaderByFileSuffixFilter.class);
        resources.add(org.jboss.resteasy.core.AsynchronousDispatcher.class);
        resources.add(org.jboss.resteasy.plugins.interceptors.encoding.AcceptEncodingGZIPFilter.class);
        resources.add(org.jboss.resteasy.plugins.interceptors.encoding.AcceptEncodingGZIPInterceptor.class);
        resources.add(org.jboss.resteasy.plugins.interceptors.encoding.GZIPDecodingInterceptor.class);
        resources.add(org.jboss.resteasy.plugins.interceptors.encoding.GZIPEncodingInterceptor.class);
        resources.add(org.jboss.resteasy.plugins.providers.DataSourceProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.DefaultNumberWriter.class);
        resources.add(org.jboss.resteasy.plugins.providers.DefaultTextPlain.class);
        resources.add(org.jboss.resteasy.plugins.providers.DocumentProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.FileProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.FormUrlEncodedProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.IIOImageProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.InputStreamProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.JaxrsFormProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.ReaderProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.SerializableProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.SourceProvider.class);
        resources.add(org.jboss.resteasy.plugins.providers.StringTextStar.class);
    }
    
}
