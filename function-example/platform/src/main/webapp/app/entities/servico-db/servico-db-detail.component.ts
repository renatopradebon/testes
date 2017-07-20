import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ServicoDb } from './servico-db.model';
import { ServicoDbService } from './servico-db.service';

@Component({
    selector: 'jhi-servico-db-detail',
    templateUrl: './servico-db-detail.component.html'
})
export class ServicoDbDetailComponent implements OnInit, OnDestroy {

    servicoDb: ServicoDb;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private servicoDbService: ServicoDbService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['servicoDb']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInServicoDbs();
    }

    load(id) {
        this.servicoDbService.find(id).subscribe((servicoDb) => {
            this.servicoDb = servicoDb;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInServicoDbs() {
        this.eventSubscriber = this.eventManager.subscribe('servicoDbListModification', (response) => this.load(this.servicoDb.id));
    }
}
