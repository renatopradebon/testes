import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Plano } from './plano.model';
import { PlanoPopupService } from './plano-popup.service';
import { PlanoService } from './plano.service';
import { Produto, ProdutoService } from '../produto';

@Component({
    selector: 'jhi-plano-dialog',
    templateUrl: './plano-dialog.component.html'
})
export class PlanoDialogComponent implements OnInit {

    plano: Plano;
    authorities: any[];
    isSaving: boolean;

    produtoes: Produto[];

    produtoes: PlanoClassificador[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private planoService: PlanoService,
        private produtoService: ProdutoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['plano']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoService.query().subscribe(
            (res: Response) => { this.produtoes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.produtoService.query().subscribe(
            (res: Response) => { this.produtoes = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.plano.id !== undefined) {
            this.planoService.update(this.plano)
                .subscribe((res: Plano) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.planoService.create(this.plano)
                .subscribe((res: Plano) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Plano) {
        this.eventManager.broadcast({ name: 'planoListModification', content: 'OK'});
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
    selector: 'jhi-plano-popup',
    template: ''
})
export class PlanoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private planoPopupService: PlanoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.planoPopupService
                    .open(PlanoDialogComponent, params['id']);
            } else {
                this.modalRef = this.planoPopupService
                    .open(PlanoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
