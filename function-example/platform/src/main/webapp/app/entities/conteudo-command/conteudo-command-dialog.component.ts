import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConteudoCommand } from './conteudo-command.model';
import { ConteudoCommandPopupService } from './conteudo-command-popup.service';
import { ConteudoCommandService } from './conteudo-command.service';

@Component({
    selector: 'jhi-conteudo-command-dialog',
    templateUrl: './conteudo-command-dialog.component.html'
})
export class ConteudoCommandDialogComponent implements OnInit {

    conteudoCommand: ConteudoCommand;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private conteudoCommandService: ConteudoCommandService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoCommand']);
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
        if (this.conteudoCommand.id !== undefined) {
            this.conteudoCommandService.update(this.conteudoCommand)
                .subscribe((res: ConteudoCommand) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.conteudoCommandService.create(this.conteudoCommand)
                .subscribe((res: ConteudoCommand) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConteudoCommand) {
        this.eventManager.broadcast({ name: 'conteudoCommandListModification', content: 'OK'});
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
    selector: 'jhi-conteudo-command-popup',
    template: ''
})
export class ConteudoCommandPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoCommandPopupService: ConteudoCommandPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.conteudoCommandPopupService
                    .open(ConteudoCommandDialogComponent, params['id']);
            } else {
                this.modalRef = this.conteudoCommandPopupService
                    .open(ConteudoCommandDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
