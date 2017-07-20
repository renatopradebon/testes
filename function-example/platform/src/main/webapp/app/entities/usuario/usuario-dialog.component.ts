import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Usuario } from './usuario.model';
import { UsuarioPopupService } from './usuario-popup.service';
import { UsuarioService } from './usuario.service';

@Component({
    selector: 'jhi-usuario-dialog',
    templateUrl: './usuario-dialog.component.html'
})
export class UsuarioDialogComponent implements OnInit {

    usuario: Usuario;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private usuarioService: UsuarioService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['usuario']);
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
        if (this.usuario.id !== undefined) {
            this.usuarioService.update(this.usuario)
                .subscribe((res: Usuario) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.usuarioService.create(this.usuario)
                .subscribe((res: Usuario) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Usuario) {
        this.eventManager.broadcast({ name: 'usuarioListModification', content: 'OK'});
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
    selector: 'jhi-usuario-popup',
    template: ''
})
export class UsuarioPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usuarioPopupService: UsuarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.usuarioPopupService
                    .open(UsuarioDialogComponent, params['id']);
            } else {
                this.modalRef = this.usuarioPopupService
                    .open(UsuarioDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
