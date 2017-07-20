import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { DeliveryOrderAlvoServico } from './delivery-order-alvo-servico.model';
import { DeliveryOrderAlvoServicoPopupService } from './delivery-order-alvo-servico-popup.service';
import { DeliveryOrderAlvoServicoService } from './delivery-order-alvo-servico.service';
import { ConfigSchema, ConfigSchemaService } from '../config-schema';
import { DeliveryOrder, DeliveryOrderService } from '../delivery-order';

@Component({
    selector: 'jhi-delivery-order-alvo-servico-dialog',
    templateUrl: './delivery-order-alvo-servico-dialog.component.html'
})
export class DeliveryOrderAlvoServicoDialogComponent implements OnInit {

    deliveryOrderAlvoServico: DeliveryOrderAlvoServico;
    authorities: any[];
    isSaving: boolean;

    configschemas: ConfigSchema[];

    deliveryorders: DeliveryOrder[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private deliveryOrderAlvoServicoService: DeliveryOrderAlvoServicoService,
        private configSchemaService: ConfigSchemaService,
        private deliveryOrderService: DeliveryOrderService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['deliveryOrderAlvoServico']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.configSchemaService.query({filter: 'deliveryorderalvoservico-is-null'}).subscribe((res: Response) => {
            if (!this.deliveryOrderAlvoServico.configSchema || !this.deliveryOrderAlvoServico.configSchema.id) {
                this.configschemas = res.json();
            } else {
                this.configSchemaService.find(this.deliveryOrderAlvoServico.configSchema.id).subscribe((subRes: ConfigSchema) => {
                    this.configschemas = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.deliveryOrderService.query().subscribe(
            (res: Response) => { this.deliveryorders = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.deliveryOrderAlvoServico.id !== undefined) {
            this.deliveryOrderAlvoServicoService.update(this.deliveryOrderAlvoServico)
                .subscribe((res: DeliveryOrderAlvoServico) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.deliveryOrderAlvoServicoService.create(this.deliveryOrderAlvoServico)
                .subscribe((res: DeliveryOrderAlvoServico) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: DeliveryOrderAlvoServico) {
        this.eventManager.broadcast({ name: 'deliveryOrderAlvoServicoListModification', content: 'OK'});
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

    trackConfigSchemaById(index: number, item: ConfigSchema) {
        return item.id;
    }

    trackDeliveryOrderById(index: number, item: DeliveryOrder) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-delivery-order-alvo-servico-popup',
    template: ''
})
export class DeliveryOrderAlvoServicoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private deliveryOrderAlvoServicoPopupService: DeliveryOrderAlvoServicoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.deliveryOrderAlvoServicoPopupService
                    .open(DeliveryOrderAlvoServicoDialogComponent, params['id']);
            } else {
                this.modalRef = this.deliveryOrderAlvoServicoPopupService
                    .open(DeliveryOrderAlvoServicoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
