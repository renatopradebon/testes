import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MaestroAppConfig } from './maestro-app-config.model';
import { MaestroAppConfigService } from './maestro-app-config.service';
@Injectable()
export class MaestroAppConfigPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private maestroAppConfigService: MaestroAppConfigService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.maestroAppConfigService.find(id).subscribe((maestroAppConfig) => {
                this.maestroAppConfigModalRef(component, maestroAppConfig);
            });
        } else {
            return this.maestroAppConfigModalRef(component, new MaestroAppConfig());
        }
    }

    maestroAppConfigModalRef(component: Component, maestroAppConfig: MaestroAppConfig): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.maestroAppConfig = maestroAppConfig;
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
