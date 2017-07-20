import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ServicoDb } from './servico-db.model';
import { ServicoDbPopupService } from './servico-db-popup.service';
import { ServicoDbService } from './servico-db.service';

@Component({
    selector: 'jhi-servico-db-delete-dialog',
    templateUrl: './servico-db-delete-dialog.component.html'
})
export class ServicoDbDeleteDialogComponent {

    servicoDb: ServicoDb;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private servicoDbService: ServicoDbService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servicoDb']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.servicoDbService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'servicoDbListModification',
                content: 'Deleted an servicoDb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-servico-db-delete-popup',
    template: ''
})
export class ServicoDbDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servicoDbPopupService: ServicoDbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.servicoDbPopupService
                .open(ServicoDbDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
