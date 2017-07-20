import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ServicoDb } from './servico-db.model';
import { ServicoDbPopupService } from './servico-db-popup.service';
import { ServicoDbService } from './servico-db.service';

@Component({
    selector: 'jhi-servico-db-dialog',
    templateUrl: './servico-db-dialog.component.html'
})
export class ServicoDbDialogComponent implements OnInit {

    servicoDb: ServicoDb;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private servicoDbService: ServicoDbService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servicoDb']);
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
        if (this.servicoDb.id !== undefined) {
            this.servicoDbService.update(this.servicoDb)
                .subscribe((res: ServicoDb) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.servicoDbService.create(this.servicoDb)
                .subscribe((res: ServicoDb) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ServicoDb) {
        this.eventManager.broadcast({ name: 'servicoDbListModification', content: 'OK'});
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
    selector: 'jhi-servico-db-popup',
    template: ''
})
export class ServicoDbPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servicoDbPopupService: ServicoDbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.servicoDbPopupService
                    .open(ServicoDbDialogComponent, params['id']);
            } else {
                this.modalRef = this.servicoDbPopupService
                    .open(ServicoDbDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
