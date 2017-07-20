import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { EntregaAlvo } from './entrega-alvo.model';
import { EntregaAlvoService } from './entrega-alvo.service';

@Component({
    selector: 'jhi-entrega-alvo-detail',
    templateUrl: './entrega-alvo-detail.component.html'
})
export class EntregaAlvoDetailComponent implements OnInit, OnDestroy {

    entregaAlvo: EntregaAlvo;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private entregaAlvoService: EntregaAlvoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['entregaAlvo']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEntregaAlvoes();
    }

    load(id) {
        this.entregaAlvoService.find(id).subscribe((entregaAlvo) => {
            this.entregaAlvo = entregaAlvo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEntregaAlvoes() {
        this.eventSubscriber = this.eventManager.subscribe('entregaAlvoListModification', (response) => this.load(this.entregaAlvo.id));
    }
}
