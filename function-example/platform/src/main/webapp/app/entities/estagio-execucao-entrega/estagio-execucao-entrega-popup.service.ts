import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstagioExecucaoEntrega } from './estagio-execucao-entrega.model';
import { EstagioExecucaoEntregaService } from './estagio-execucao-entrega.service';
@Injectable()
export class EstagioExecucaoEntregaPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private estagioExecucaoEntregaService: EstagioExecucaoEntregaService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.estagioExecucaoEntregaService.find(id).subscribe((estagioExecucaoEntrega) => {
                this.estagioExecucaoEntregaModalRef(component, estagioExecucaoEntrega);
            });
        } else {
            return this.estagioExecucaoEntregaModalRef(component, new EstagioExecucaoEntrega());
        }
    }

    estagioExecucaoEntregaModalRef(component: Component, estagioExecucaoEntrega: EstagioExecucaoEntrega): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.estagioExecucaoEntrega = estagioExecucaoEntrega;
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
