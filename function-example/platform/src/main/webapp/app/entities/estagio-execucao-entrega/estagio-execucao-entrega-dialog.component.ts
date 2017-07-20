import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { EstagioExecucaoEntrega } from './estagio-execucao-entrega.model';
import { EstagioExecucaoEntregaPopupService } from './estagio-execucao-entrega-popup.service';
import { EstagioExecucaoEntregaService } from './estagio-execucao-entrega.service';
import { PlanoClassificador, PlanoClassificadorService } from '../plano-classificador';
import { Entrega, EntregaService } from '../entrega';

@Component({
    selector: 'jhi-estagio-execucao-entrega-dialog',
    templateUrl: './estagio-execucao-entrega-dialog.component.html'
})
export class EstagioExecucaoEntregaDialogComponent implements OnInit {

    estagioExecucaoEntrega: EstagioExecucaoEntrega;
    authorities: any[];
    isSaving: boolean;

    planoclassificadors: PlanoClassificador[];

    entregas: Entrega[];

    entregas: EntregaAlvo[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private estagioExecucaoEntregaService: EstagioExecucaoEntregaService,
        private planoClassificadorService: PlanoClassificadorService,
        private entregaService: EntregaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['estagioExecucaoEntrega']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.planoClassificadorService.query({filter: 'estagioexecucaoentrega-is-null'}).subscribe((res: Response) => {
            if (!this.estagioExecucaoEntrega.planoClassificador || !this.estagioExecucaoEntrega.planoClassificador.id) {
                this.planoclassificadors = res.json();
            } else {
                this.planoClassificadorService.find(this.estagioExecucaoEntrega.planoClassificador.id).subscribe((subRes: PlanoClassificador) => {
                    this.planoclassificadors = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.entregaService.query().subscribe(
            (res: Response) => { this.entregas = res.json(); }, (res: Response) => this.onError(res.json()));
        this.entregaService.query().subscribe(
            (res: Response) => { this.entregas = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.estagioExecucaoEntrega.id !== undefined) {
            this.estagioExecucaoEntregaService.update(this.estagioExecucaoEntrega)
                .subscribe((res: EstagioExecucaoEntrega) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.estagioExecucaoEntregaService.create(this.estagioExecucaoEntrega)
                .subscribe((res: EstagioExecucaoEntrega) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: EstagioExecucaoEntrega) {
        this.eventManager.broadcast({ name: 'estagioExecucaoEntregaListModification', content: 'OK'});
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

    trackPlanoClassificadorById(index: number, item: PlanoClassificador) {
        return item.id;
    }

    trackEntregaById(index: number, item: Entrega) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-estagio-execucao-entrega-popup',
    template: ''
})
export class EstagioExecucaoEntregaPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private estagioExecucaoEntregaPopupService: EstagioExecucaoEntregaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.estagioExecucaoEntregaPopupService
                    .open(EstagioExecucaoEntregaDialogComponent, params['id']);
            } else {
                this.modalRef = this.estagioExecucaoEntregaPopupService
                    .open(EstagioExecucaoEntregaDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
