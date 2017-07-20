import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { EntregaAlvo } from './entrega-alvo.model';
import { EntregaAlvoPopupService } from './entrega-alvo-popup.service';
import { EntregaAlvoService } from './entrega-alvo.service';

@Component({
    selector: 'jhi-entrega-alvo-delete-dialog',
    templateUrl: './entrega-alvo-delete-dialog.component.html'
})
export class EntregaAlvoDeleteDialogComponent {

    entregaAlvo: EntregaAlvo;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private entregaAlvoService: EntregaAlvoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['entregaAlvo']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entregaAlvoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'entregaAlvoListModification',
                content: 'Deleted an entregaAlvo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entrega-alvo-delete-popup',
    template: ''
})
export class EntregaAlvoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private entregaAlvoPopupService: EntregaAlvoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.entregaAlvoPopupService
                .open(EntregaAlvoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
