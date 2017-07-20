import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConfigSchemaAppRevision } from './config-schema-app-revision.model';
import { ConfigSchemaAppRevisionService } from './config-schema-app-revision.service';
@Injectable()
export class ConfigSchemaAppRevisionPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private configSchemaAppRevisionService: ConfigSchemaAppRevisionService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.configSchemaAppRevisionService.find(id).subscribe((configSchemaAppRevision) => {
                this.configSchemaAppRevisionModalRef(component, configSchemaAppRevision);
            });
        } else {
            return this.configSchemaAppRevisionModalRef(component, new ConfigSchemaAppRevision());
        }
    }

    configSchemaAppRevisionModalRef(component: Component, configSchemaAppRevision: ConfigSchemaAppRevision): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.configSchemaAppRevision = configSchemaAppRevision;
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
