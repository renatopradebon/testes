import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConteudoDb } from './conteudo-db.model';
import { ConteudoDbService } from './conteudo-db.service';

@Component({
    selector: 'jhi-conteudo-db-detail',
    templateUrl: './conteudo-db-detail.component.html'
})
export class ConteudoDbDetailComponent implements OnInit, OnDestroy {

    conteudoDb: ConteudoDb;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private conteudoDbService: ConteudoDbService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['conteudoDb']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConteudoDbs();
    }

    load(id) {
        this.conteudoDbService.find(id).subscribe((conteudoDb) => {
            this.conteudoDb = conteudoDb;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConteudoDbs() {
        this.eventSubscriber = this.eventManager.subscribe('conteudoDbListModification', (response) => this.load(this.conteudoDb.id));
    }
}
