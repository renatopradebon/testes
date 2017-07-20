import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ProdutoDbRevision } from './produto-db-revision.model';
import { ProdutoDbRevisionPopupService } from './produto-db-revision-popup.service';
import { ProdutoDbRevisionService } from './produto-db-revision.service';
import { Produto, ProdutoService } from '../produto';

@Component({
    selector: 'jhi-produto-db-revision-dialog',
    templateUrl: './produto-db-revision-dialog.component.html'
})
export class ProdutoDbRevisionDialogComponent implements OnInit {

    produtoDbRevision: ProdutoDbRevision;
    authorities: any[];
    isSaving: boolean;

    produtoes: Produto[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private produtoDbRevisionService: ProdutoDbRevisionService,
        private produtoService: ProdutoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['produtoDbRevision']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoService.query().subscribe(
            (res: Response) => { this.produtoes = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.produtoDbRevision.id !== undefined) {
            this.produtoDbRevisionService.update(this.produtoDbRevision)
                .subscribe((res: ProdutoDbRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.produtoDbRevisionService.create(this.produtoDbRevision)
                .subscribe((res: ProdutoDbRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ProdutoDbRevision) {
        this.eventManager.broadcast({ name: 'produtoDbRevisionListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-produto-db-revision-popup',
    template: ''
})
export class ProdutoDbRevisionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoDbRevisionPopupService: ProdutoDbRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.produtoDbRevisionPopupService
                    .open(ProdutoDbRevisionDialogComponent, params['id']);
            } else {
                this.modalRef = this.produtoDbRevisionPopupService
                    .open(ProdutoDbRevisionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
