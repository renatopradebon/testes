import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Servidor } from './servidor.model';
import { ServidorPopupService } from './servidor-popup.service';
import { ServidorService } from './servidor.service';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-servidor-dialog',
    templateUrl: './servidor-dialog.component.html'
})
export class ServidorDialogComponent implements OnInit {

    servidor: Servidor;
    authorities: any[];
    isSaving: boolean;

    clientes: Cliente[];

    clientes: Servico[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private servidorService: ServidorService,
        private clienteService: ClienteService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servidor']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clienteService.query().subscribe(
            (res: Response) => { this.clientes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.clienteService.query().subscribe(
            (res: Response) => { this.clientes = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.servidor.id !== undefined) {
            this.servidorService.update(this.servidor)
                .subscribe((res: Servidor) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.servidorService.create(this.servidor)
                .subscribe((res: Servidor) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Servidor) {
        this.eventManager.broadcast({ name: 'servidorListModification', content: 'OK'});
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

    trackClienteById(index: number, item: Cliente) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-servidor-popup',
    template: ''
})
export class ServidorPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servidorPopupService: ServidorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.servidorPopupService
                    .open(ServidorDialogComponent, params['id']);
            } else {
                this.modalRef = this.servidorPopupService
                    .open(ServidorDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
