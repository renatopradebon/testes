import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { DeliveryOrder } from './delivery-order.model';
import { DeliveryOrderPopupService } from './delivery-order-popup.service';
import { DeliveryOrderService } from './delivery-order.service';
import { Empresa, EmpresaService } from '../empresa';

@Component({
    selector: 'jhi-delivery-order-dialog',
    templateUrl: './delivery-order-dialog.component.html'
})
export class DeliveryOrderDialogComponent implements OnInit {

    deliveryOrder: DeliveryOrder;
    authorities: any[];
    isSaving: boolean;

    empresas: Empresa[];

    empresas: DeliveryOrderAlvoServico[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private deliveryOrderService: DeliveryOrderService,
        private empresaService: EmpresaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrder']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.empresaService.query().subscribe(
            (res: Response) => { this.empresas = res.json(); }, (res: Response) => this.onError(res.json()));
        this.empresaService.query().subscribe(
            (res: Response) => { this.empresas = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.deliveryOrder.id !== undefined) {
            this.deliveryOrderService.update(this.deliveryOrder)
                .subscribe((res: DeliveryOrder) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.deliveryOrderService.create(this.deliveryOrder)
                .subscribe((res: DeliveryOrder) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: DeliveryOrder) {
        this.eventManager.broadcast({ name: 'deliveryOrderListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackEmpresaById(index: number, item: Empresa) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-delivery-order-popup',
    template: ''
})
export class DeliveryOrderPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private deliveryOrderPopupService: DeliveryOrderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.deliveryOrderPopupService
                    .open(DeliveryOrderDialogComponent, params['id']);
            } else {
                this.modalRef = this.deliveryOrderPopupService
                    .open(DeliveryOrderDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
