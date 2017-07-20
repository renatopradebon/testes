import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { DeliveryOrderAlvoServico } from './delivery-order-alvo-servico.model';
import { DeliveryOrderAlvoServicoService } from './delivery-order-alvo-servico.service';

@Component({
    selector: 'jhi-delivery-order-alvo-servico-detail',
    templateUrl: './delivery-order-alvo-servico-detail.component.html'
})
export class DeliveryOrderAlvoServicoDetailComponent implements OnInit, OnDestroy {

    deliveryOrderAlvoServico: DeliveryOrderAlvoServico;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private deliveryOrderAlvoServicoService: DeliveryOrderAlvoServicoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrderAlvoServico']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDeliveryOrderAlvoServicoes();
    }

    load(id) {
        this.deliveryOrderAlvoServicoService.find(id).subscribe((deliveryOrderAlvoServico) => {
            this.deliveryOrderAlvoServico = deliveryOrderAlvoServico;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDeliveryOrderAlvoServicoes() {
        this.eventSubscriber = this.eventManager.subscribe('deliveryOrderAlvoServicoListModification', (response) => this.load(this.deliveryOrderAlvoServico.id));
    }
}
