import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { Filial } from './filial.model';
import { FilialPopupService } from './filial-popup.service';
import { FilialService } from './filial.service';

@Component({
    selector: 'jhi-filial-delete-dialog',
    templateUrl: './filial-delete-dialog.component.html'
})
export class FilialDeleteDialogComponent {

    filial: Filial;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private filialService: FilialService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['filial']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.filialService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'filialListModification',
                content: 'Deleted an filial'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-filial-delete-popup',
    template: ''
})
export class FilialDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private filialPopupService: FilialPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.filialPopupService
                .open(FilialDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
