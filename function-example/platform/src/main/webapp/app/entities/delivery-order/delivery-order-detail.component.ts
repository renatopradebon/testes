import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { DeliveryOrder } from './delivery-order.model';
import { DeliveryOrderService } from './delivery-order.service';

@Component({
    selector: 'jhi-delivery-order-detail',
    templateUrl: './delivery-order-detail.component.html'
})
export class DeliveryOrderDetailComponent implements OnInit, OnDestroy {

    deliveryOrder: DeliveryOrder;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private deliveryOrderService: DeliveryOrderService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrder']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDeliveryOrders();
    }

    load(id) {
        this.deliveryOrderService.find(id).subscribe((deliveryOrder) => {
            this.deliveryOrder = deliveryOrder;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDeliveryOrders() {
        this.eventSubscriber = this.eventManager.subscribe('deliveryOrderListModification', (response) => this.load(this.deliveryOrder.id));
    }
}
