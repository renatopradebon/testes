import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { PlanoClassificador } from './plano-classificador.model';
import { PlanoClassificadorService } from './plano-classificador.service';
@Injectable()
export class PlanoClassificadorPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private planoClassificadorService: PlanoClassificadorService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.planoClassificadorService.find(id).subscribe((planoClassificador) => {
                this.planoClassificadorModalRef(component, planoClassificador);
            });
        } else {
            return this.planoClassificadorModalRef(component, new PlanoClassificador());
        }
    }

    planoClassificadorModalRef(component: Component, planoClassificador: PlanoClassificador): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.planoClassificador = planoClassificador;
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
