import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { EstagioExecucaoEntrega } from './estagio-execucao-entrega.model';
import { EstagioExecucaoEntregaPopupService } from './estagio-execucao-entrega-popup.service';
import { EstagioExecucaoEntregaService } from './estagio-execucao-entrega.service';

@Component({
    selector: 'jhi-estagio-execucao-entrega-delete-dialog',
    templateUrl: './estagio-execucao-entrega-delete-dialog.component.html'
})
export class EstagioExecucaoEntregaDeleteDialogComponent {

    estagioExecucaoEntrega: EstagioExecucaoEntrega;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private estagioExecucaoEntregaService: EstagioExecucaoEntregaService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['estagioExecucaoEntrega']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.estagioExecucaoEntregaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'estagioExecucaoEntregaListModification',
                content: 'Deleted an estagioExecucaoEntrega'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-estagio-execucao-entrega-delete-popup',
    template: ''
})
export class EstagioExecucaoEntregaDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private estagioExecucaoEntregaPopupService: EstagioExecucaoEntregaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.estagioExecucaoEntregaPopupService
                .open(EstagioExecucaoEntregaDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
