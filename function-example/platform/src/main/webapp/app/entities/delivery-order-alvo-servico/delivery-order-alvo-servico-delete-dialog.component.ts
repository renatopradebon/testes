import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { DeliveryOrderAlvoServico } from './delivery-order-alvo-servico.model';
import { DeliveryOrderAlvoServicoPopupService } from './delivery-order-alvo-servico-popup.service';
import { DeliveryOrderAlvoServicoService } from './delivery-order-alvo-servico.service';

@Component({
    selector: 'jhi-delivery-order-alvo-servico-delete-dialog',
    templateUrl: './delivery-order-alvo-servico-delete-dialog.component.html'
})
export class DeliveryOrderAlvoServicoDeleteDialogComponent {

    deliveryOrderAlvoServico: DeliveryOrderAlvoServico;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private deliveryOrderAlvoServicoService: DeliveryOrderAlvoServicoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrderAlvoServico']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.deliveryOrderAlvoServicoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'deliveryOrderAlvoServicoListModification',
                content: 'Deleted an deliveryOrderAlvoServico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-delivery-order-alvo-servico-delete-popup',
    template: ''
})
export class DeliveryOrderAlvoServicoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private deliveryOrderAlvoServicoPopupService: DeliveryOrderAlvoServicoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.deliveryOrderAlvoServicoPopupService
                .open(DeliveryOrderAlvoServicoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
