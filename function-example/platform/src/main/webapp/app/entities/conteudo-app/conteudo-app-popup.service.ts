import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConteudoApp } from './conteudo-app.model';
import { ConteudoAppService } from './conteudo-app.service';
@Injectable()
export class ConteudoAppPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private conteudoAppService: ConteudoAppService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.conteudoAppService.find(id).subscribe((conteudoApp) => {
                this.conteudoAppModalRef(component, conteudoApp);
            });
        } else {
            return this.conteudoAppModalRef(component, new ConteudoApp());
        }
    }

    conteudoAppModalRef(component: Component, conteudoApp: ConteudoApp): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.conteudoApp = conteudoApp;
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
