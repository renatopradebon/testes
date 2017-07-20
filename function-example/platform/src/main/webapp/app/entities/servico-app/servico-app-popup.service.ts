import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ServicoApp } from './servico-app.model';
import { ServicoAppService } from './servico-app.service';
@Injectable()
export class ServicoAppPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private servicoAppService: ServicoAppService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.servicoAppService.find(id).subscribe((servicoApp) => {
                this.servicoAppModalRef(component, servicoApp);
            });
        } else {
            return this.servicoAppModalRef(component, new ServicoApp());
        }
    }

    servicoAppModalRef(component: Component, servicoApp: ServicoApp): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.servicoApp = servicoApp;
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
