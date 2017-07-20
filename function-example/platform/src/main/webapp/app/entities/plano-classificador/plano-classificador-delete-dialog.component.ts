import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { PlanoClassificador } from './plano-classificador.model';
import { PlanoClassificadorPopupService } from './plano-classificador-popup.service';
import { PlanoClassificadorService } from './plano-classificador.service';

@Component({
    selector: 'jhi-plano-classificador-delete-dialog',
    templateUrl: './plano-classificador-delete-dialog.component.html'
})
export class PlanoClassificadorDeleteDialogComponent {

    planoClassificador: PlanoClassificador;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private planoClassificadorService: PlanoClassificadorService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['planoClassificador']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planoClassificadorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'planoClassificadorListModification',
                content: 'Deleted an planoClassificador'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plano-classificador-delete-popup',
    template: ''
})
export class PlanoClassificadorDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private planoClassificadorPopupService: PlanoClassificadorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.planoClassificadorPopupService
                .open(PlanoClassificadorDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
