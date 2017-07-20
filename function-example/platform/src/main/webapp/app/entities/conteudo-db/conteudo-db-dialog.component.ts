import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConteudoDb } from './conteudo-db.model';
import { ConteudoDbPopupService } from './conteudo-db-popup.service';
import { ConteudoDbService } from './conteudo-db.service';
import { ProdutoDbRevision, ProdutoDbRevisionService } from '../produto-db-revision';

@Component({
    selector: 'jhi-conteudo-db-dialog',
    templateUrl: './conteudo-db-dialog.component.html'
})
export class ConteudoDbDialogComponent implements OnInit {

    conteudoDb: ConteudoDb;
    authorities: any[];
    isSaving: boolean;

    revisaoes: ProdutoDbRevision[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private conteudoDbService: ConteudoDbService,
        private produtoDbRevisionService: ProdutoDbRevisionService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoDb']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoDbRevisionService.query({filter: 'conteudodb-is-null'}).subscribe((res: Response) => {
            if (!this.conteudoDb.revisao || !this.conteudoDb.revisao.id) {
                this.revisaoes = res.json();
            } else {
                this.produtoDbRevisionService.find(this.conteudoDb.revisao.id).subscribe((subRes: ProdutoDbRevision) => {
                    this.revisaoes = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.conteudoDb.id !== undefined) {
            this.conteudoDbService.update(this.conteudoDb)
                .subscribe((res: ConteudoDb) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.conteudoDbService.create(this.conteudoDb)
                .subscribe((res: ConteudoDb) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConteudoDb) {
        this.eventManager.broadcast({ name: 'conteudoDbListModification', content: 'OK'});
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

    trackProdutoDbRevisionById(index: number, item: ProdutoDbRevision) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-conteudo-db-popup',
    template: ''
})
export class ConteudoDbPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoDbPopupService: ConteudoDbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.conteudoDbPopupService
                    .open(ConteudoDbDialogComponent, params['id']);
            } else {
                this.modalRef = this.conteudoDbPopupService
                    .open(ConteudoDbDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
