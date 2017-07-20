import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConteudoDb } from './conteudo-db.model';
import { ConteudoDbPopupService } from './conteudo-db-popup.service';
import { ConteudoDbService } from './conteudo-db.service';

@Component({
    selector: 'jhi-conteudo-db-delete-dialog',
    templateUrl: './conteudo-db-delete-dialog.component.html'
})
export class ConteudoDbDeleteDialogComponent {

    conteudoDb: ConteudoDb;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private conteudoDbService: ConteudoDbService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoDb']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conteudoDbService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'conteudoDbListModification',
                content: 'Deleted an conteudoDb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conteudo-db-delete-popup',
    template: ''
})
export class ConteudoDbDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoDbPopupService: ConteudoDbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.conteudoDbPopupService
                .open(ConteudoDbDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
