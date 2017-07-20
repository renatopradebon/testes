import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Produto } from './produto.model';
import { ProdutoService } from './produto.service';

@Component({
    selector: 'jhi-produto-detail',
    templateUrl: './produto-detail.component.html'
})
export class ProdutoDetailComponent implements OnInit, OnDestroy {

    produto: Produto;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private produtoService: ProdutoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['produto']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProdutoes();
    }

    load(id) {
        this.produtoService.find(id).subscribe((produto) => {
            this.produto = produto;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProdutoes() {
        this.eventSubscriber = this.eventManager.subscribe('produtoListModification', (response) => this.load(this.produto.id));
    }
}
