import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Filial } from './filial.model';
import { FilialPopupService } from './filial-popup.service';
import { FilialService } from './filial.service';
import { Empresa, EmpresaService } from '../empresa';

@Component({
    selector: 'jhi-filial-dialog',
    templateUrl: './filial-dialog.component.html'
})
export class FilialDialogComponent implements OnInit {

    filial: Filial;
    authorities: any[];
    isSaving: boolean;

    empresas: Empresa[];

    empresas: ConfigSchema[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private filialService: FilialService,
        private empresaService: EmpresaService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['filial']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.empresaService.query().subscribe(
            (res: Response) => { this.empresas = res.json(); }, (res: Response) => this.onError(res.json()));
        this.empresaService.query().subscribe(
            (res: Response) => { this.empresas = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.filial.id !== undefined) {
            this.filialService.update(this.filial)
                .subscribe((res: Filial) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.filialService.create(this.filial)
                .subscribe((res: Filial) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Filial) {
        this.eventManager.broadcast({ name: 'filialListModification', content: 'OK'});
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

    trackEmpresaById(index: number, item: Empresa) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-filial-popup',
    template: ''
})
export class FilialPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private filialPopupService: FilialPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.filialPopupService
                    .open(FilialDialogComponent, params['id']);
            } else {
                this.modalRef = this.filialPopupService
                    .open(FilialDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
