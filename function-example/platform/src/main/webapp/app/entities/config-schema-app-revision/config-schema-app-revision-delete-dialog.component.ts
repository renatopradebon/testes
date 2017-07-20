import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchemaAppRevision } from './config-schema-app-revision.model';
import { ConfigSchemaAppRevisionPopupService } from './config-schema-app-revision-popup.service';
import { ConfigSchemaAppRevisionService } from './config-schema-app-revision.service';

@Component({
    selector: 'jhi-config-schema-app-revision-delete-dialog',
    templateUrl: './config-schema-app-revision-delete-dialog.component.html'
})
export class ConfigSchemaAppRevisionDeleteDialogComponent {

    configSchemaAppRevision: ConfigSchemaAppRevision;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private configSchemaAppRevisionService: ConfigSchemaAppRevisionService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchemaAppRevision']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configSchemaAppRevisionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configSchemaAppRevisionListModification',
                content: 'Deleted an configSchemaAppRevision'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-config-schema-app-revision-delete-popup',
    template: ''
})
export class ConfigSchemaAppRevisionDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaAppRevisionPopupService: ConfigSchemaAppRevisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.configSchemaAppRevisionPopupService
                .open(ConfigSchemaAppRevisionDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
