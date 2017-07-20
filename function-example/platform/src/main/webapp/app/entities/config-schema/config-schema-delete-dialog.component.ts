import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ConfigSchema } from './config-schema.model';
import { ConfigSchemaPopupService } from './config-schema-popup.service';
import { ConfigSchemaService } from './config-schema.service';

@Component({
    selector: 'jhi-config-schema-delete-dialog',
    templateUrl: './config-schema-delete-dialog.component.html'
})
export class ConfigSchemaDeleteDialogComponent {

    configSchema: ConfigSchema;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private configSchemaService: ConfigSchemaService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['configSchema']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configSchemaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configSchemaListModification',
                content: 'Deleted an configSchema'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-config-schema-delete-popup',
    template: ''
})
export class ConfigSchemaDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configSchemaPopupService: ConfigSchemaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.configSchemaPopupService
                .open(ConfigSchemaDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
