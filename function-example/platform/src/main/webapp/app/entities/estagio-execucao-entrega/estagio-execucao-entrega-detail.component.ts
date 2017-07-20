import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { EstagioExecucaoEntrega } from './estagio-execucao-entrega.model';
import { EstagioExecucaoEntregaService } from './estagio-execucao-entrega.service';

@Component({
    selector: 'jhi-estagio-execucao-entrega-detail',
    templateUrl: './estagio-execucao-entrega-detail.component.html'
})
export class EstagioExecucaoEntregaDetailComponent implements OnInit, OnDestroy {

    estagioExecucaoEntrega: EstagioExecucaoEntrega;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private estagioExecucaoEntregaService: EstagioExecucaoEntregaService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['estagioExecucaoEntrega']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInEstagioExecucaoEntregas();
    }

    load(id) {
        this.estagioExecucaoEntregaService.find(id).subscribe((estagioExecucaoEntrega) => {
            this.estagioExecucaoEntrega = estagioExecucaoEntrega;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInEstagioExecucaoEntregas() {
        this.eventSubscriber = this.eventManager.subscribe('estagioExecucaoEntregaListModification', (response) => this.load(this.estagioExecucaoEntrega.id));
    }
}
