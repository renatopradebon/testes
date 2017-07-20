import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Artefato } from './artefato.model';
import { ArtefatoPopupService } from './artefato-popup.service';
import { ArtefatoService } from './artefato.service';

@Component({
    selector: 'jhi-artefato-delete-dialog',
    templateUrl: './artefato-delete-dialog.component.html'
})
export class ArtefatoDeleteDialogComponent {

    artefato: Artefato;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private artefatoService: ArtefatoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['artefato']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.artefatoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'artefatoListModification',
                content: 'Deleted an artefato'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-artefato-delete-popup',
    template: ''
})
export class ArtefatoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private artefatoPopupService: ArtefatoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.artefatoPopupService
                .open(ArtefatoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
