import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchemaAppRevision } from './config-schema-app-revision.model';
import { ConfigSchemaAppRevisionPopupService } from './config-schema-app-revision-popup.service';
import { ConfigSchemaAppRevisionService } from './config-schema-app-revision.service';
import { ProdutoAppVersao, ProdutoAppVersaoService } from '../produto-app-versao';
import { ConfigSchema, ConfigSchemaService } from '../config-schema';

@Component({
    selector: 'jhi-config-schema-app-revision-dialog',
    templateUrl: './config-schema-app-revision-dialog.component.html'
})
export class ConfigSchemaAppRevisionDialogComponent implements OnInit {

    configSchemaAppRevision: ConfigSchemaAppRevision;
    authorities: any[];
    isSaving: boolean;

    produtoappversaoes: ProdutoAppVersao[];

    configschemas: ConfigSchema[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private configSchemaAppRevisionService: ConfigSchemaAppRevisionService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        private configSchemaService: ConfigSchemaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchemaAppRevision']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoAppVersaoService.query({filter: 'configschemaapprevision-is-null'}).subscribe((res: Response) => {
            if (!this.configSchemaAppRevision.produtoAppVersao || !this.configSchemaAppRevision.produtoAppVersao.id) {
                this.produtoappversaoes = res.json();
            } else {
                this.produtoAppVersaoService.find(this.configSchemaAppRevision.produtoAppVersao.id).subscribe((subRes: ProdutoAppVersao) => {
                    this.produtoappversaoes = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.configSchemaService.query().subscribe(
            (res: Response) => { this.configschemas = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.configSchemaAppRevision.id !== undefined) {
            this.configSchemaAppRevisionService.update(this.configSchemaAppRevision)
                .subscribe((res: ConfigSchemaAppRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.configSchemaAppRevisionService.create(this.configSchemaAppRevision)
                .subscribe((res: ConfigSchemaAppRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConfigSchemaAppRevision) {
        this.eventManager.broadcast({ name: 'configSchemaAppRevisionListModification', content: 'OK'});
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

    trackConfigSchemaById(index: number, item: ConfigSchema) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-config-schema-app-revision-popup',
    template: ''
})
export class ConfigSchemaAppRevisionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaAppRevisionPopupService: ConfigSchemaAppRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.configSchemaAppRevisionPopupService
                    .open(ConfigSchemaAppRevisionDialogComponent, params['id']);
            } else {
                this.modalRef = this.configSchemaAppRevisionPopupService
                    .open(ConfigSchemaAppRevisionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
