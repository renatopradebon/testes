import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Entrega } from './entrega.model';
import { EntregaPopupService } from './entrega-popup.service';
import { EntregaService } from './entrega.service';
import { Plano, PlanoService } from '../plano';

@Component({
    selector: 'jhi-entrega-dialog',
    templateUrl: './entrega-dialog.component.html'
})
export class EntregaDialogComponent implements OnInit {

    entrega: Entrega;
    authorities: any[];
    isSaving: boolean;

    planoes: Plano[];

    planoes: EstagioExecucaoEntrega[];

    planoes: Conteudo[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private entregaService: EntregaService,
        private planoService: PlanoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['entrega']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.planoService.query().subscribe(
            (res: Response) => { this.planoes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.planoService.query().subscribe(
            (res: Response) => { this.planoes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.planoService.query().subscribe(
            (res: Response) => { this.planoes = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.entrega.id !== undefined) {
            this.entregaService.update(this.entrega)
                .subscribe((res: Entrega) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.entregaService.create(this.entrega)
                .subscribe((res: Entrega) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Entrega) {
        this.eventManager.broadcast({ name: 'entregaListModification', content: 'OK'});
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

    trackPlanoById(index: number, item: Plano) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-entrega-popup',
    template: ''
})
export class EntregaPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entregaPopupService: EntregaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.entregaPopupService
                    .open(EntregaDialogComponent, params['id']);
            } else {
                this.modalRef = this.entregaPopupService
                    .open(EntregaDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
