import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConteudoApp } from './conteudo-app.model';
import { ConteudoAppPopupService } from './conteudo-app-popup.service';
import { ConteudoAppService } from './conteudo-app.service';

@Component({
    selector: 'jhi-conteudo-app-delete-dialog',
    templateUrl: './conteudo-app-delete-dialog.component.html'
})
export class ConteudoAppDeleteDialogComponent {

    conteudoApp: ConteudoApp;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private conteudoAppService: ConteudoAppService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoApp']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conteudoAppService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'conteudoAppListModification',
                content: 'Deleted an conteudoApp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conteudo-app-delete-popup',
    template: ''
})
export class ConteudoAppDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoAppPopupService: ConteudoAppPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.conteudoAppPopupService
                .open(ConteudoAppDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
