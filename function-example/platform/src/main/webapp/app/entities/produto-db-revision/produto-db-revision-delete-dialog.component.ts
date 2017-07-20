import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ProdutoDbRevision } from './produto-db-revision.model';
import { ProdutoDbRevisionPopupService } from './produto-db-revision-popup.service';
import { ProdutoDbRevisionService } from './produto-db-revision.service';

@Component({
    selector: 'jhi-produto-db-revision-delete-dialog',
    templateUrl: './produto-db-revision-delete-dialog.component.html'
})
export class ProdutoDbRevisionDeleteDialogComponent {

    produtoDbRevision: ProdutoDbRevision;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private produtoDbRevisionService: ProdutoDbRevisionService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['produtoDbRevision']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.produtoDbRevisionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'produtoDbRevisionListModification',
                content: 'Deleted an produtoDbRevision'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-produto-db-revision-delete-popup',
    template: ''
})
export class ProdutoDbRevisionDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoDbRevisionPopupService: ProdutoDbRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.produtoDbRevisionPopupService
                .open(ProdutoDbRevisionDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
