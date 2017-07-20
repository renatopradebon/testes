import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ProdutoDbRevision } from './produto-db-revision.model';
import { ProdutoDbRevisionService } from './produto-db-revision.service';
@Injectable()
export class ProdutoDbRevisionPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private produtoDbRevisionService: ProdutoDbRevisionService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.produtoDbRevisionService.find(id).subscribe((produtoDbRevision) => {
                this.produtoDbRevisionModalRef(component, produtoDbRevision);
            });
        } else {
            return this.produtoDbRevisionModalRef(component, new ProdutoDbRevision());
        }
    }

    produtoDbRevisionModalRef(component: Component, produtoDbRevision: ProdutoDbRevision): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.produtoDbRevision = produtoDbRevision;
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
