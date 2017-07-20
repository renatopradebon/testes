import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Cliente } from './cliente.model';
import { ClientePopupService } from './cliente-popup.service';
import { ClienteService } from './cliente.service';
import { Produto, ProdutoService } from '../produto';

@Component({
    selector: 'jhi-cliente-dialog',
    templateUrl: './cliente-dialog.component.html'
})
export class ClienteDialogComponent implements OnInit {

    cliente: Cliente;
    authorities: any[];
    isSaving: boolean;

    produto: Produto[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private clienteService: ClienteService,
        private produtoService: ProdutoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['cliente']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoService.query().subscribe(
            (res: Response) => { this.produto = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.cliente.id !== undefined) {
            this.clienteService.update(this.cliente)
                .subscribe((res: Cliente) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.clienteService.create(this.cliente)
                .subscribe((res: Cliente) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Cliente) {
        this.eventManager.broadcast({ name: 'clienteListModification', content: 'OK'});
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

    trackProdutoById(index: number, item: Produto) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-cliente-popup',
    template: ''
})
export class ClientePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clientePopupService: ClientePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clientePopupService
                    .open(ClienteDialogComponent, params['id']);
            } else {
                this.modalRef = this.clientePopupService
                    .open(ClienteDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
