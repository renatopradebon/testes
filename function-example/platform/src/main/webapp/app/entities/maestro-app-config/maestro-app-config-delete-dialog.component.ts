import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { MaestroAppConfig } from './maestro-app-config.model';
import { MaestroAppConfigPopupService } from './maestro-app-config-popup.service';
import { MaestroAppConfigService } from './maestro-app-config.service';

@Component({
    selector: 'jhi-maestro-app-config-delete-dialog',
    templateUrl: './maestro-app-config-delete-dialog.component.html'
})
export class MaestroAppConfigDeleteDialogComponent {

    maestroAppConfig: MaestroAppConfig;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private maestroAppConfigService: MaestroAppConfigService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['maestroAppConfig']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.maestroAppConfigService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'maestroAppConfigListModification',
                content: 'Deleted an maestroAppConfig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-maestro-app-config-delete-popup',
    template: ''
})
export class MaestroAppConfigDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private maestroAppConfigPopupService: MaestroAppConfigPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.maestroAppConfigPopupService
                .open(MaestroAppConfigDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
