import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConteudoApp } from './conteudo-app.model';
import { ConteudoAppService } from './conteudo-app.service';

@Component({
    selector: 'jhi-conteudo-app-detail',
    templateUrl: './conteudo-app-detail.component.html'
})
export class ConteudoAppDetailComponent implements OnInit, OnDestroy {

    conteudoApp: ConteudoApp;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private conteudoAppService: ConteudoAppService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['conteudoApp']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConteudoApps();
    }

    load(id) {
        this.conteudoAppService.find(id).subscribe((conteudoApp) => {
            this.conteudoApp = conteudoApp;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConteudoApps() {
        this.eventSubscriber = this.eventManager.subscribe('conteudoAppListModification', (response) => this.load(this.conteudoApp.id));
    }
}
