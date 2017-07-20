/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.datapar.controller.util;

import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author Renato Pradebon
 */
public class ControllerEventObserver {

    @Inject
    private Logger logger;

    private void onBeforeController(@Observes javax.mvc.event.BeforeControllerEvent e) {
        logger.info(() -> "Controller matched for " + e.getUriInfo().getRequestUri());
    }

    private void onAfterController(@Observes javax.mvc.event.AfterControllerEvent e) {
        logger.info(() -> "Controller executed : " + e.getResourceInfo().getResourceMethod());
    }

    private void onBeforeProcessView(@Observes javax.mvc.event.BeforeProcessViewEvent e) {
        logger.info(() -> "View : " + e.getView());
    }

    private void onAfterProcessView(@Observes javax.mvc.event.AfterProcessViewEvent e) {
        logger.info(() -> "View engine: " + e.getEngine());
    }
    
}
