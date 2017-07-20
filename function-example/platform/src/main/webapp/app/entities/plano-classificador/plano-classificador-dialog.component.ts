import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { PlanoClassificador } from './plano-classificador.model';
import { PlanoClassificadorPopupService } from './plano-classificador-popup.service';
import { PlanoClassificadorService } from './plano-classificador.service';
import { Plano, PlanoService } from '../plano';

@Component({
    selector: 'jhi-plano-classificador-dialog',
    templateUrl: './plano-classificador-dialog.component.html'
})
export class PlanoClassificadorDialogComponent implements OnInit {

    planoClassificador: PlanoClassificador;
    authorities: any[];
    isSaving: boolean;

    planoes: Plano[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private planoClassificadorService: PlanoClassificadorService,
        private planoService: PlanoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['planoClassificador']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.planoService.query().subscribe(
            (res: Response) => { this.planoes = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.planoClassificador.id !== undefined) {
            this.planoClassificadorService.update(this.planoClassificador)
                .subscribe((res: PlanoClassificador) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.planoClassificadorService.create(this.planoClassificador)
                .subscribe((res: PlanoClassificador) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: PlanoClassificador) {
        this.eventManager.broadcast({ name: 'planoClassificadorListModification', content: 'OK'});
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
    selector: 'jhi-plano-classificador-popup',
    template: ''
})
export class PlanoClassificadorPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private planoClassificadorPopupService: PlanoClassificadorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.planoClassificadorPopupService
                    .open(PlanoClassificadorDialogComponent, params['id']);
            } else {
                this.modalRef = this.planoClassificadorPopupService
                    .open(PlanoClassificadorDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
