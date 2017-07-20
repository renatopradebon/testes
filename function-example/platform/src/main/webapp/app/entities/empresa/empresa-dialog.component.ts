import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Empresa } from './empresa.model';
import { EmpresaPopupService } from './empresa-popup.service';
import { EmpresaService } from './empresa.service';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-empresa-dialog',
    templateUrl: './empresa-dialog.component.html'
})
export class EmpresaDialogComponent implements OnInit {

    empresa: Empresa;
    authorities: any[];
    isSaving: boolean;

    clientes: Cliente[];

    clientes: Filial[];

    clientes: DeliveryOrder[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private empresaService: EmpresaService,
        private clienteService: ClienteService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['empresa']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clienteService.query().subscribe(
            (res: Response) => { this.clientes = res.json(); }, (res: Response) => this.onError(res.json()));
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
        if (this.empresa.id !== undefined) {
            this.empresaService.update(this.empresa)
                .subscribe((res: Empresa) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.empresaService.create(this.empresa)
                .subscribe((res: Empresa) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Empresa) {
        this.eventManager.broadcast({ name: 'empresaListModification', content: 'OK'});
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
    selector: 'jhi-empresa-popup',
    template: ''
})
export class EmpresaPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private empresaPopupService: EmpresaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.empresaPopupService
                    .open(EmpresaDialogComponent, params['id']);
            } else {
                this.modalRef = this.empresaPopupService
                    .open(EmpresaDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
