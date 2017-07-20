import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConfigSchema } from './config-schema.model';
import { ConfigSchemaService } from './config-schema.service';
@Injectable()
export class ConfigSchemaPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private configSchemaService: ConfigSchemaService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.configSchemaService.find(id).subscribe((configSchema) => {
                this.configSchemaModalRef(component, configSchema);
            });
        } else {
            return this.configSchemaModalRef(component, new ConfigSchema());
        }
    }

    configSchemaModalRef(component: Component, configSchema: ConfigSchema): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.configSchema = configSchema;
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
