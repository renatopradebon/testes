import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DeliveryOrderAlvoServico } from './delivery-order-alvo-servico.model';
import { DeliveryOrderAlvoServicoService } from './delivery-order-alvo-servico.service';
@Injectable()
export class DeliveryOrderAlvoServicoPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private deliveryOrderAlvoServicoService: DeliveryOrderAlvoServicoService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.deliveryOrderAlvoServicoService.find(id).subscribe((deliveryOrderAlvoServico) => {
                this.deliveryOrderAlvoServicoModalRef(component, deliveryOrderAlvoServico);
            });
        } else {
            return this.deliveryOrderAlvoServicoModalRef(component, new DeliveryOrderAlvoServico());
        }
    }

    deliveryOrderAlvoServicoModalRef(component: Component, deliveryOrderAlvoServico: DeliveryOrderAlvoServico): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.deliveryOrderAlvoServico = deliveryOrderAlvoServico;
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
