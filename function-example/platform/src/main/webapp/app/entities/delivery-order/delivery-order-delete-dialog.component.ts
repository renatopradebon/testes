import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { DeliveryOrder } from './delivery-order.model';
import { DeliveryOrderPopupService } from './delivery-order-popup.service';
import { DeliveryOrderService } from './delivery-order.service';

@Component({
    selector: 'jhi-delivery-order-delete-dialog',
    templateUrl: './delivery-order-delete-dialog.component.html'
})
export class DeliveryOrderDeleteDialogComponent {

    deliveryOrder: DeliveryOrder;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private deliveryOrderService: DeliveryOrderService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrder']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deliveryOrderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'deliveryOrderListModification',
                content: 'Deleted an deliveryOrder'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-delivery-order-delete-popup',
    template: ''
})
export class DeliveryOrderDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private deliveryOrderPopupService: DeliveryOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.deliveryOrderPopupService
                .open(DeliveryOrderDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
