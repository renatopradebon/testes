import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ServicoApp } from './servico-app.model';
import { ServicoAppPopupService } from './servico-app-popup.service';
import { ServicoAppService } from './servico-app.service';

@Component({
    selector: 'jhi-servico-app-dialog',
    templateUrl: './servico-app-dialog.component.html'
})
export class ServicoAppDialogComponent implements OnInit {

    servicoApp: ServicoApp;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private servicoAppService: ServicoAppService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servicoApp']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.servicoApp.id !== undefined) {
            this.servicoAppService.update(this.servicoApp)
                .subscribe((res: ServicoApp) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.servicoAppService.create(this.servicoApp)
                .subscribe((res: ServicoApp) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ServicoApp) {
        this.eventManager.broadcast({ name: 'servicoAppListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-servico-app-popup',
    template: ''
})
export class ServicoAppPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servicoAppPopupService: ServicoAppPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.servicoAppPopupService
                    .open(ServicoAppDialogComponent, params['id']);
            } else {
                this.modalRef = this.servicoAppPopupService
                    .open(ServicoAppDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
