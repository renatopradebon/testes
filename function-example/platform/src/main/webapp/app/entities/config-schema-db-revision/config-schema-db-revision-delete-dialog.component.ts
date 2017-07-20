import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchemaDbRevision } from './config-schema-db-revision.model';
import { ConfigSchemaDbRevisionPopupService } from './config-schema-db-revision-popup.service';
import { ConfigSchemaDbRevisionService } from './config-schema-db-revision.service';

@Component({
    selector: 'jhi-config-schema-db-revision-delete-dialog',
    templateUrl: './config-schema-db-revision-delete-dialog.component.html'
})
export class ConfigSchemaDbRevisionDeleteDialogComponent {

    configSchemaDbRevision: ConfigSchemaDbRevision;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private configSchemaDbRevisionService: ConfigSchemaDbRevisionService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchemaDbRevision']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configSchemaDbRevisionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configSchemaDbRevisionListModification',
                content: 'Deleted an configSchemaDbRevision'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-config-schema-db-revision-delete-popup',
    template: ''
})
export class ConfigSchemaDbRevisionDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaDbRevisionPopupService: ConfigSchemaDbRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.configSchemaDbRevisionPopupService
                .open(ConfigSchemaDbRevisionDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
