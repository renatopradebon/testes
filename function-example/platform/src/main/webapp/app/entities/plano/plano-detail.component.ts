import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Plano } from './plano.model';
import { PlanoService } from './plano.service';

@Component({
    selector: 'jhi-plano-detail',
    templateUrl: './plano-detail.component.html'
})
export class PlanoDetailComponent implements OnInit, OnDestroy {

    plano: Plano;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private planoService: PlanoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['plano']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPlanoes();
    }

    load(id) {
        this.planoService.find(id).subscribe((plano) => {
            this.plano = plano;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPlanoes() {
        this.eventSubscriber = this.eventManager.subscribe('planoListModification', (response) => this.load(this.plano.id));
    }
}
