import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchemaDbRevision } from './config-schema-db-revision.model';
import { ConfigSchemaDbRevisionPopupService } from './config-schema-db-revision-popup.service';
import { ConfigSchemaDbRevisionService } from './config-schema-db-revision.service';
import { ProdutoDbRevision, ProdutoDbRevisionService } from '../produto-db-revision';
import { ConfigSchema, ConfigSchemaService } from '../config-schema';

@Component({
    selector: 'jhi-config-schema-db-revision-dialog',
    templateUrl: './config-schema-db-revision-dialog.component.html'
})
export class ConfigSchemaDbRevisionDialogComponent implements OnInit {

    configSchemaDbRevision: ConfigSchemaDbRevision;
    authorities: any[];
    isSaving: boolean;

    produtodbrevisions: ProdutoDbRevision[];

    configschemas: ConfigSchema[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private configSchemaDbRevisionService: ConfigSchemaDbRevisionService,
        private produtoDbRevisionService: ProdutoDbRevisionService,
        private configSchemaService: ConfigSchemaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchemaDbRevision']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.produtoDbRevisionService.query({filter: 'configschemadbrevision-is-null'}).subscribe((res: Response) => {
            if (!this.configSchemaDbRevision.produtoDbRevision || !this.configSchemaDbRevision.produtoDbRevision.id) {
                this.produtodbrevisions = res.json();
            } else {
                this.produtoDbRevisionService.find(this.configSchemaDbRevision.produtoDbRevision.id).subscribe((subRes: ProdutoDbRevision) => {
                    this.produtodbrevisions = [subRes].concat(res.json());
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
        if (this.configSchemaDbRevision.id !== undefined) {
            this.configSchemaDbRevisionService.update(this.configSchemaDbRevision)
                .subscribe((res: ConfigSchemaDbRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.configSchemaDbRevisionService.create(this.configSchemaDbRevision)
                .subscribe((res: ConfigSchemaDbRevision) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConfigSchemaDbRevision) {
        this.eventManager.broadcast({ name: 'configSchemaDbRevisionListModification', content: 'OK'});
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

    trackConfigSchemaById(index: number, item: ConfigSchema) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-config-schema-db-revision-popup',
    template: ''
})
export class ConfigSchemaDbRevisionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaDbRevisionPopupService: ConfigSchemaDbRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.configSchemaDbRevisionPopupService
                    .open(ConfigSchemaDbRevisionDialogComponent, params['id']);
            } else {
                this.modalRef = this.configSchemaDbRevisionPopupService
                    .open(ConfigSchemaDbRevisionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
