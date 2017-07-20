import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ServicoApp } from './servico-app.model';
import { ServicoAppPopupService } from './servico-app-popup.service';
import { ServicoAppService } from './servico-app.service';

@Component({
    selector: 'jhi-servico-app-delete-dialog',
    templateUrl: './servico-app-delete-dialog.component.html'
})
export class ServicoAppDeleteDialogComponent {

    servicoApp: ServicoApp;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private servicoAppService: ServicoAppService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servicoApp']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.servicoAppService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'servicoAppListModification',
                content: 'Deleted an servicoApp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-servico-app-delete-popup',
    template: ''
})
export class ServicoAppDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servicoAppPopupService: ServicoAppPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.servicoAppPopupService
                .open(ServicoAppDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
