import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DeliveryOrder } from './delivery-order.model';
import { DeliveryOrderService } from './delivery-order.service';
@Injectable()
export class DeliveryOrderPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private deliveryOrderService: DeliveryOrderService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.deliveryOrderService.find(id).subscribe((deliveryOrder) => {
                this.deliveryOrderModalRef(component, deliveryOrder);
            });
        } else {
            return this.deliveryOrderModalRef(component, new DeliveryOrder());
        }
    }

    deliveryOrderModalRef(component: Component, deliveryOrder: DeliveryOrder): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.deliveryOrder = deliveryOrder;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
