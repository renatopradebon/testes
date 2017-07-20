import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ProdutoAppVersao } from './produto-app-versao.model';
import { ProdutoAppVersaoService } from './produto-app-versao.service';
@Injectable()
export class ProdutoAppVersaoPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private produtoAppVersaoService: ProdutoAppVersaoService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.produtoAppVersaoService.find(id).subscribe((produtoAppVersao) => {
                this.produtoAppVersaoModalRef(component, produtoAppVersao);
            });
        } else {
            return this.produtoAppVersaoModalRef(component, new ProdutoAppVersao());
        }
    }

    produtoAppVersaoModalRef(component: Component, produtoAppVersao: ProdutoAppVersao): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.produtoAppVersao = produtoAppVersao;
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
