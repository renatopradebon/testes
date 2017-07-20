import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { EntregaAlvo } from './entrega-alvo.model';
import { EntregaAlvoPopupService } from './entrega-alvo-popup.service';
import { EntregaAlvoService } from './entrega-alvo.service';
import { Empresa, EmpresaService } from '../empresa';
import { DeliveryOrder, DeliveryOrderService } from '../delivery-order';
import { EstagioExecucaoEntrega, EstagioExecucaoEntregaService } from '../estagio-execucao-entrega';

@Component({
    selector: 'jhi-entrega-alvo-dialog',
    templateUrl: './entrega-alvo-dialog.component.html'
})
export class EntregaAlvoDialogComponent implements OnInit {

    entregaAlvo: EntregaAlvo;
    authorities: any[];
    isSaving: boolean;

    empresas: Empresa[];

    deliveryorders: DeliveryOrder[];

    estagioexecucaoentregas: EstagioExecucaoEntrega[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private entregaAlvoService: EntregaAlvoService,
        private empresaService: EmpresaService,
        private deliveryOrderService: DeliveryOrderService,
        private estagioExecucaoEntregaService: EstagioExecucaoEntregaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['entregaAlvo']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.empresaService.query({filter: 'entregaalvo-is-null'}).subscribe((res: Response) => {
            if (!this.entregaAlvo.empresa || !this.entregaAlvo.empresa.id) {
                this.empresas = res.json();
            } else {
                this.empresaService.find(this.entregaAlvo.empresa.id).subscribe((subRes: Empresa) => {
                    this.empresas = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.deliveryOrderService.query({filter: 'entregaalvo-is-null'}).subscribe((res: Response) => {
            if (!this.entregaAlvo.deliveryOrder || !this.entregaAlvo.deliveryOrder.id) {
                this.deliveryorders = res.json();
            } else {
                this.deliveryOrderService.find(this.entregaAlvo.deliveryOrder.id).subscribe((subRes: DeliveryOrder) => {
                    this.deliveryorders = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.estagioExecucaoEntregaService.query().subscribe(
            (res: Response) => { this.estagioexecucaoentregas = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.entregaAlvo.id !== undefined) {
            this.entregaAlvoService.update(this.entregaAlvo)
                .subscribe((res: EntregaAlvo) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.entregaAlvoService.create(this.entregaAlvo)
                .subscribe((res: EntregaAlvo) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: EntregaAlvo) {
        this.eventManager.broadcast({ name: 'entregaAlvoListModification', content: 'OK'});
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

    trackDeliveryOrderById(index: number, item: DeliveryOrder) {
        return item.id;
    }

    trackEstagioExecucaoEntregaById(index: number, item: EstagioExecucaoEntrega) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-entrega-alvo-popup',
    template: ''
})
export class EntregaAlvoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entregaAlvoPopupService: EntregaAlvoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entregaAlvoPopupService
                    .open(EntregaAlvoDialogComponent, params['id']);
            } else {
                this.modalRef = this.entregaAlvoPopupService
                    .open(EntregaAlvoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
