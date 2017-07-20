import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConteudoApp } from './conteudo-app.model';
import { ConteudoAppPopupService } from './conteudo-app-popup.service';
import { ConteudoAppService } from './conteudo-app.service';
import { ProdutoAppVersao, ProdutoAppVersaoService } from '../produto-app-versao';

@Component({
    selector: 'jhi-conteudo-app-dialog',
    templateUrl: './conteudo-app-dialog.component.html'
})
export class ConteudoAppDialogComponent implements OnInit {

    conteudoApp: ConteudoApp;
    authorities: any[];
    isSaving: boolean;

    versaoes: ProdutoAppVersao[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private conteudoAppService: ConteudoAppService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoApp']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoAppVersaoService.query({filter: 'conteudoapp-is-null'}).subscribe((res: Response) => {
            if (!this.conteudoApp.versao || !this.conteudoApp.versao.id) {
                this.versaoes = res.json();
            } else {
                this.produtoAppVersaoService.find(this.conteudoApp.versao.id).subscribe((subRes: ProdutoAppVersao) => {
                    this.versaoes = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.conteudoApp.id !== undefined) {
            this.conteudoAppService.update(this.conteudoApp)
                .subscribe((res: ConteudoApp) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.conteudoAppService.create(this.conteudoApp)
                .subscribe((res: ConteudoApp) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConteudoApp) {
        this.eventManager.broadcast({ name: 'conteudoAppListModification', content: 'OK'});
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

    trackProdutoAppVersaoById(index: number, item: ProdutoAppVersao) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-conteudo-app-popup',
    template: ''
})
export class ConteudoAppPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoAppPopupService: ConteudoAppPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.conteudoAppPopupService
                    .open(ConteudoAppDialogComponent, params['id']);
            } else {
                this.modalRef = this.conteudoAppPopupService
                    .open(ConteudoAppDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
