import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Servidor } from './servidor.model';
import { ServidorService } from './servidor.service';

@Component({
    selector: 'jhi-servidor-detail',
    templateUrl: './servidor-detail.component.html'
})
export class ServidorDetailComponent implements OnInit, OnDestroy {

    servidor: Servidor;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private servidorService: ServidorService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['servidor']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInServidors();
    }

    load(id) {
        this.servidorService.find(id).subscribe((servidor) => {
            this.servidor = servidor;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInServidors() {
        this.eventSubscriber = this.eventManager.subscribe('servidorListModification', (response) => this.load(this.servidor.id));
    }
}
