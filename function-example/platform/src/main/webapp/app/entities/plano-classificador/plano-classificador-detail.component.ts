import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { PlanoClassificador } from './plano-classificador.model';
import { PlanoClassificadorService } from './plano-classificador.service';

@Component({
    selector: 'jhi-plano-classificador-detail',
    templateUrl: './plano-classificador-detail.component.html'
})
export class PlanoClassificadorDetailComponent implements OnInit, OnDestroy {

    planoClassificador: PlanoClassificador;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private planoClassificadorService: PlanoClassificadorService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['planoClassificador']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPlanoClassificadors();
    }

    load(id) {
        this.planoClassificadorService.find(id).subscribe((planoClassificador) => {
            this.planoClassificador = planoClassificador;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPlanoClassificadors() {
        this.eventSubscriber = this.eventManager.subscribe('planoClassificadorListModification', (response) => this.load(this.planoClassificador.id));
    }
}
