import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ServicoApp } from './servico-app.model';
import { ServicoAppService } from './servico-app.service';

@Component({
    selector: 'jhi-servico-app-detail',
    templateUrl: './servico-app-detail.component.html'
})
export class ServicoAppDetailComponent implements OnInit, OnDestroy {

    servicoApp: ServicoApp;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private servicoAppService: ServicoAppService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['servicoApp']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInServicoApps();
    }

    load(id) {
        this.servicoAppService.find(id).subscribe((servicoApp) => {
            this.servicoApp = servicoApp;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInServicoApps() {
        this.eventSubscriber = this.eventManager.subscribe('servicoAppListModification', (response) => this.load(this.servicoApp.id));
    }
}
