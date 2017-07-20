import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Artefato } from './artefato.model';
import { ArtefatoPopupService } from './artefato-popup.service';
import { ArtefatoService } from './artefato.service';
import { ProdutoAppVersao, ProdutoAppVersaoService } from '../produto-app-versao';

@Component({
    selector: 'jhi-artefato-dialog',
    templateUrl: './artefato-dialog.component.html'
})
export class ArtefatoDialogComponent implements OnInit {

    artefato: Artefato;
    authorities: any[];
    isSaving: boolean;

    produtoappversao: ProdutoAppVersao[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private artefatoService: ArtefatoService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['artefato']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoAppVersaoService.query().subscribe(
            (res: Response) => { this.produtoappversao = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.artefato.id !== undefined) {
            this.artefatoService.update(this.artefato)
                .subscribe((res: Artefato) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.artefatoService.create(this.artefato)
                .subscribe((res: Artefato) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Artefato) {
        this.eventManager.broadcast({ name: 'artefatoListModification', content: 'OK'});
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
    selector: 'jhi-artefato-popup',
    template: ''
})
export class ArtefatoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private artefatoPopupService: ArtefatoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.artefatoPopupService
                    .open(ArtefatoDialogComponent, params['id']);
            } else {
                this.modalRef = this.artefatoPopupService
                    .open(ArtefatoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
