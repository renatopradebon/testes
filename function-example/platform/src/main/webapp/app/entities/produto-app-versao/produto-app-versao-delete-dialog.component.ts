import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, JhiLanguageService } from 'ng-jhipster';

import { ProdutoAppVersao } from './produto-app-versao.model';
import { ProdutoAppVersaoPopupService } from './produto-app-versao-popup.service';
import { ProdutoAppVersaoService } from './produto-app-versao.service';

@Component({
    selector: 'jhi-produto-app-versao-delete-dialog',
    templateUrl: './produto-app-versao-delete-dialog.component.html'
})
export class ProdutoAppVersaoDeleteDialogComponent {

    produtoAppVersao: ProdutoAppVersao;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['produtoAppVersao']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.produtoAppVersaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'produtoAppVersaoListModification',
                content: 'Deleted an produtoAppVersao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-produto-app-versao-delete-popup',
    template: ''
})
export class ProdutoAppVersaoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produtoAppVersaoPopupService: ProdutoAppVersaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.produtoAppVersaoPopupService
                .open(ProdutoAppVersaoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
