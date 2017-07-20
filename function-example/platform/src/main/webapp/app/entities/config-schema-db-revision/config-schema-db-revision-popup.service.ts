import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConfigSchemaDbRevision } from './config-schema-db-revision.model';
import { ConfigSchemaDbRevisionService } from './config-schema-db-revision.service';
@Injectable()
export class ConfigSchemaDbRevisionPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private configSchemaDbRevisionService: ConfigSchemaDbRevisionService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.configSchemaDbRevisionService.find(id).subscribe((configSchemaDbRevision) => {
                this.configSchemaDbRevisionModalRef(component, configSchemaDbRevision);
            });
        } else {
            return this.configSchemaDbRevisionModalRef(component, new ConfigSchemaDbRevision());
        }
    }

    configSchemaDbRevisionModalRef(component: Component, configSchemaDbRevision: ConfigSchemaDbRevision): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.configSchemaDbRevision = configSchemaDbRevision;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
