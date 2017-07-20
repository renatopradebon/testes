import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ProdutoDbRevision } from './produto-db-revision.model';
import { ProdutoDbRevisionService } from './produto-db-revision.service';

@Component({
    selector: 'jhi-produto-db-revision-detail',
    templateUrl: './produto-db-revision-detail.component.html'
})
export class ProdutoDbRevisionDetailComponent implements OnInit, OnDestroy {

    produtoDbRevision: ProdutoDbRevision;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private produtoDbRevisionService: ProdutoDbRevisionService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['produtoDbRevision']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProdutoDbRevisions();
    }

    load(id) {
        this.produtoDbRevisionService.find(id).subscribe((produtoDbRevision) => {
            this.produtoDbRevision = produtoDbRevision;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProdutoDbRevisions() {
        this.eventSubscriber = this.eventManager.subscribe('produtoDbRevisionListModification', (response) => this.load(this.produtoDbRevision.id));
    }
}
