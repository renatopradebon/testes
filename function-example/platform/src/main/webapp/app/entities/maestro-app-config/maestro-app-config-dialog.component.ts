import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { MaestroAppConfig } from './maestro-app-config.model';
import { MaestroAppConfigPopupService } from './maestro-app-config-popup.service';
import { MaestroAppConfigService } from './maestro-app-config.service';
import { ServicoApp, ServicoAppService } from '../servico-app';

@Component({
    selector: 'jhi-maestro-app-config-dialog',
    templateUrl: './maestro-app-config-dialog.component.html'
})
export class MaestroAppConfigDialogComponent implements OnInit {

    maestroAppConfig: MaestroAppConfig;
    authorities: any[];
    isSaving: boolean;

    servicomaestroapps: ServicoApp[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private maestroAppConfigService: MaestroAppConfigService,
        private servicoAppService: ServicoAppService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['maestroAppConfig']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.servicoAppService.query({filter: 'maestroappconfig-is-null'}).subscribe((res: Response) => {
            if (!this.maestroAppConfig.servicoMaestroApp || !this.maestroAppConfig.servicoMaestroApp.id) {
                this.servicomaestroapps = res.json();
            } else {
                this.servicoAppService.find(this.maestroAppConfig.servicoMaestroApp.id).subscribe((subRes: ServicoApp) => {
                    this.servicomaestroapps = [subRes].concat(res.json());
                }, (subRes: Response) => this.onError(subRes.json()));
            }
        }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.maestroAppConfig.id !== undefined) {
            this.maestroAppConfigService.update(this.maestroAppConfig)
                .subscribe((res: MaestroAppConfig) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.maestroAppConfigService.create(this.maestroAppConfig)
                .subscribe((res: MaestroAppConfig) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: MaestroAppConfig) {
        this.eventManager.broadcast({ name: 'maestroAppConfigListModification', content: 'OK'});
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

    trackServicoAppById(index: number, item: ServicoApp) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-maestro-app-config-popup',
    template: ''
})
export class MaestroAppConfigPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private maestroAppConfigPopupService: MaestroAppConfigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.maestroAppConfigPopupService
                    .open(MaestroAppConfigDialogComponent, params['id']);
            } else {
                this.modalRef = this.maestroAppConfigPopupService
                    .open(MaestroAppConfigDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
