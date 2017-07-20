import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ServicoDb } from './servico-db.model';
import { ServicoDbService } from './servico-db.service';
@Injectable()
export class ServicoDbPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private servicoDbService: ServicoDbService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.servicoDbService.find(id).subscribe((servicoDb) => {
                this.servicoDbModalRef(component, servicoDb);
            });
        } else {
            return this.servicoDbModalRef(component, new ServicoDb());
        }
    }

    servicoDbModalRef(component: Component, servicoDb: ServicoDb): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.servicoDb = servicoDb;
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
