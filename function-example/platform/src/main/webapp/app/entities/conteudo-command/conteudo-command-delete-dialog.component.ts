import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConteudoCommand } from './conteudo-command.model';
import { ConteudoCommandPopupService } from './conteudo-command-popup.service';
import { ConteudoCommandService } from './conteudo-command.service';

@Component({
    selector: 'jhi-conteudo-command-delete-dialog',
    templateUrl: './conteudo-command-delete-dialog.component.html'
})
export class ConteudoCommandDeleteDialogComponent {

    conteudoCommand: ConteudoCommand;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private conteudoCommandService: ConteudoCommandService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['conteudoCommand']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conteudoCommandService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'conteudoCommandListModification',
                content: 'Deleted an conteudoCommand'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conteudo-command-delete-popup',
    template: ''
})
export class ConteudoCommandDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private conteudoCommandPopupService: ConteudoCommandPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.conteudoCommandPopupService
                .open(ConteudoCommandDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
