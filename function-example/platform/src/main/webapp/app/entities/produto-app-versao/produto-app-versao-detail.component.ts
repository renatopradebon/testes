import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ProdutoAppVersao } from './produto-app-versao.model';
import { ProdutoAppVersaoService } from './produto-app-versao.service';

@Component({
    selector: 'jhi-produto-app-versao-detail',
    templateUrl: './produto-app-versao-detail.component.html'
})
export class ProdutoAppVersaoDetailComponent implements OnInit, OnDestroy {

    produtoAppVersao: ProdutoAppVersao;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private produtoAppVersaoService: ProdutoAppVersaoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['produtoAppVersao']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProdutoAppVersaoes();
    }

    load(id) {
        this.produtoAppVersaoService.find(id).subscribe((produtoAppVersao) => {
            this.produtoAppVersao = produtoAppVersao;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProdutoAppVersaoes() {
        this.eventSubscriber = this.eventManager.subscribe('produtoAppVersaoListModification', (response) => this.load(this.produtoAppVersao.id));
    }
}
