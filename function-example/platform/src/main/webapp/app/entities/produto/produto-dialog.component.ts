import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Produto } from './produto.model';
import { ProdutoPopupService } from './produto-popup.service';
import { ProdutoService } from './produto.service';
import { Cliente, ClienteService } from '../cliente';

@Component({
    selector: 'jhi-produto-dialog',
    templateUrl: './produto-dialog.component.html'
})
export class ProdutoDialogComponent implements OnInit {

    produto: Produto;
    authorities: any[];
    isSaving: boolean;

    cliente: Cliente[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private produtoService: ProdutoService,
        private clienteService: ClienteService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['produto']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clienteService.query().subscribe(
            (res: Response) => { this.cliente = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.produto.id !== undefined) {
            this.produtoService.update(this.produto)
                .subscribe((res: Produto) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.produtoService.create(this.produto)
                .subscribe((res: Produto) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Produto) {
        this.eventManager.broadcast({ name: 'produtoListModification', content: 'OK'});
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
    selector: 'jhi-produto-popup',
    template: ''
})
export class ProdutoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoPopupService: ProdutoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.produtoPopupService
                    .open(ProdutoDialogComponent, params['id']);
            } else {
                this.modalRef = this.produtoPopupService
                    .open(ProdutoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
