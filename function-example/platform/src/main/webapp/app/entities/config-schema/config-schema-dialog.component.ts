import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchema } from './config-schema.model';
import { ConfigSchemaPopupService } from './config-schema-popup.service';
import { ConfigSchemaService } from './config-schema.service';
import { ConfigSchemaAppRevision, ConfigSchemaAppRevisionService } from '../config-schema-app-revision';
import { ConfigSchemaDbRevision, ConfigSchemaDbRevisionService } from '../config-schema-db-revision';
import { ServicoApp, ServicoAppService } from '../servico-app';
import { Produto, ProdutoService } from '../produto';
import { Filial, FilialService } from '../filial';
import { ServicoDb, ServicoDbService } from '../servico-db';

@Component({
    selector: 'jhi-config-schema-dialog',
    templateUrl: './config-schema-dialog.component.html'
})
export class ConfigSchemaDialogComponent implements OnInit {

    configSchema: ConfigSchema;
    authorities: any[];
    isSaving: boolean;

    latestapprevisions: ConfigSchemaAppRevision[];

    latestdbrevisions: ConfigSchemaDbRevision[];

    servicoapps: ServicoApp[];

    produtoes: Produto[];

    filials: Filial[];

    servicodbs: ServicoDb[];

    servicodbs: ConfigSchemaAppRevision[];

    servicodbs: ConfigSchemaDbRevision[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private configSchemaService: ConfigSchemaService,
        private configSchemaAppRevisionService: ConfigSchemaAppRevisionService,
        private configSchemaDbRevisionService: ConfigSchemaDbRevisionService,
        private servicoAppService: ServicoAppService,
        private produtoService: ProdutoService,
        private filialService: FilialService,
        private servicoDbService: ServicoDbService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchema']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.configSchemaAppRevisionService.query({filter: 'configschema-is-null'}).subscribe((res: Response) => {
            if (!this.configSchema.latestAppRevision || !this.configSchema.latestAppRevision.id) {
                this.latestapprevisions = res.json();
            } else {
                this.configSchemaAppRevisionService.find(this.configSchema.latestAppRevision.id).subscribe((subRes: ConfigSchemaAppRevision) => {
                    this.latestapprevisions = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.configSchemaDbRevisionService.query({filter: 'configschema-is-null'}).subscribe((res: Response) => {
            if (!this.configSchema.latestDbRevision || !this.configSchema.latestDbRevision.id) {
                this.latestdbrevisions = res.json();
            } else {
                this.configSchemaDbRevisionService.find(this.configSchema.latestDbRevision.id).subscribe((subRes: ConfigSchemaDbRevision) => {
                    this.latestdbrevisions = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
        this.servicoAppService.query().subscribe(
            (res: Response) => { this.servicoapps = res.json(); }, (res: Response) => this.onError(res.json()));
        this.produtoService.query().subscribe(
            (res: Response) => { this.produtoes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.filialService.query().subscribe(
            (res: Response) => { this.filials = res.json(); }, (res: Response) => this.onError(res.json()));
        this.servicoDbService.query().subscribe(
            (res: Response) => { this.servicodbs = res.json(); }, (res: Response) => this.onError(res.json()));
        this.servicoDbService.query().subscribe(
            (res: Response) => { this.servicodbs = res.json(); }, (res: Response) => this.onError(res.json()));
        this.servicoDbService.query().subscribe(
            (res: Response) => { this.servicodbs = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.configSchema.id !== undefined) {
            this.configSchemaService.update(this.configSchema)
                .subscribe((res: ConfigSchema) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.configSchemaService.create(this.configSchema)
                .subscribe((res: ConfigSchema) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: ConfigSchema) {
        this.eventManager.broadcast({ name: 'configSchemaListModification', content: 'OK'});
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

    trackConfigSchemaAppRevisionById(index: number, item: ConfigSchemaAppRevision) {
        return item.id;
    }

    trackConfigSchemaDbRevisionById(index: number, item: ConfigSchemaDbRevision) {
        return item.id;
    }

    trackServicoAppById(index: number, item: ServicoApp) {
        return item.id;
    }

    trackProdutoById(index: number, item: Produto) {
        return item.id;
    }

    trackFilialById(index: number, item: Filial) {
        return item.id;
    }

    trackServicoDbById(index: number, item: ServicoDb) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-config-schema-popup',
    template: ''
})
export class ConfigSchemaPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaPopupService: ConfigSchemaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.configSchemaPopupService
                    .open(ConfigSchemaDialogComponent, params['id']);
            } else {
                this.modalRef = this.configSchemaPopupService
                    .open(ConfigSchemaDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
