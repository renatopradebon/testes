import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EntregaAlvo } from './entrega-alvo.model';
import { EntregaAlvoService } from './entrega-alvo.service';
@Injectable()
export class EntregaAlvoPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private entregaAlvoService: EntregaAlvoService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.entregaAlvoService.find(id).subscribe((entregaAlvo) => {
                this.entregaAlvoModalRef(component, entregaAlvo);
            });
        } else {
            return this.entregaAlvoModalRef(component, new EntregaAlvo());
        }
    }

    entregaAlvoModalRef(component: Component, entregaAlvo: EntregaAlvo): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.entregaAlvo = entregaAlvo;
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
