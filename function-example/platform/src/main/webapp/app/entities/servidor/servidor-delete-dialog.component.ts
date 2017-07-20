import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Servidor } from './servidor.model';
import { ServidorPopupService } from './servidor-popup.service';
import { ServidorService } from './servidor.service';

@Component({
    selector: 'jhi-servidor-delete-dialog',
    templateUrl: './servidor-delete-dialog.component.html'
})
export class ServidorDeleteDialogComponent {

    servidor: Servidor;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private servidorService: ServidorService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['servidor']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.servidorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'servidorListModification',
                content: 'Deleted an servidor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-servidor-delete-popup',
    template: ''
})
export class ServidorDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private servidorPopupService: ServidorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.servidorPopupService
                .open(ServidorDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
