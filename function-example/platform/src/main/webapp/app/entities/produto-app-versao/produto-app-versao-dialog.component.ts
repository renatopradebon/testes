import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ProdutoAppVersao } from './produto-app-versao.model';
import { ProdutoAppVersaoPopupService } from './produto-app-versao-popup.service';
import { ProdutoAppVersaoService } from './produto-app-versao.service';
import { Produto, ProdutoService } from '../produto';
import { Artefato, ArtefatoService } from '../artefato';

@Component({
    selector: 'jhi-produto-app-versao-dialog',
    templateUrl: './produto-app-versao-dialog.component.html'
})
export class ProdutoAppVersaoDialogComponent implements OnInit {

    produtoAppVersao: ProdutoAppVersao;
    authorities: any[];
    isSaving: boolean;

    produtoes: Produto[];

    artefato: Artefato[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        private produtoService: ProdutoService,
        private artefatoService: ArtefatoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['produtoAppVersao']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoService.query().subscribe(
            (res: Response) => { this.produtoes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.artefatoService.query().subscribe(
            (res: Response) => { this.artefato = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.produtoAppVersao.id !== undefined) {
            this.produtoAppVersaoService.update(this.produtoAppVersao)
                .subscribe((res: ProdutoAppVersao) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.produtoAppVersaoService.create(this.produtoAppVersao)
                .subscribe((res: ProdutoAppVersao) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ProdutoAppVersao) {
        this.eventManager.broadcast({ name: 'produtoAppVersaoListModification', content: 'OK'});
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

    trackArtefatoById(index: number, item: Artefato) {
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
    selector: 'jhi-produto-app-versao-popup',
    template: ''
})
export class ProdutoAppVersaoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoAppVersaoPopupService: ProdutoAppVersaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.produtoAppVersaoPopupService
                    .open(ProdutoAppVersaoDialogComponent, params['id']);
            } else {
                this.modalRef = this.produtoAppVersaoPopupService
                    .open(ProdutoAppVersaoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
