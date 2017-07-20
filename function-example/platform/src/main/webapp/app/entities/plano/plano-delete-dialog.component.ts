import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Plano } from './plano.model';
import { PlanoPopupService } from './plano-popup.service';
import { PlanoService } from './plano.service';

@Component({
    selector: 'jhi-plano-delete-dialog',
    templateUrl: './plano-delete-dialog.component.html'
})
export class PlanoDeleteDialogComponent {

    plano: Plano;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private planoService: PlanoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['plano']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.planoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'planoListModification',
                content: 'Deleted an plano'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plano-delete-popup',
    template: ''
})
export class PlanoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private planoPopupService: PlanoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.planoPopupService
                .open(PlanoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
