import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConteudoDb } from './conteudo-db.model';
import { ConteudoDbService } from './conteudo-db.service';
@Injectable()
export class ConteudoDbPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private conteudoDbService: ConteudoDbService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.conteudoDbService.find(id).subscribe((conteudoDb) => {
                this.conteudoDbModalRef(component, conteudoDb);
            });
        } else {
            return this.conteudoDbModalRef(component, new ConteudoDb());
        }
    }

    conteudoDbModalRef(component: Component, conteudoDb: ConteudoDb): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.conteudoDb = conteudoDb;
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
