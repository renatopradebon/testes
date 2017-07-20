import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Artefato } from './artefato.model';
import { ArtefatoService } from './artefato.service';

@Component({
    selector: 'jhi-artefato-detail',
    templateUrl: './artefato-detail.component.html'
})
export class ArtefatoDetailComponent implements OnInit, OnDestroy {

    artefato: Artefato;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private artefatoService: ArtefatoService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['artefato']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInArtefatoes();
    }

    load(id) {
        this.artefatoService.find(id).subscribe((artefato) => {
            this.artefato = artefato;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInArtefatoes() {
        this.eventSubscriber = this.eventManager.subscribe('artefatoListModification', (response) => this.load(this.artefato.id));
    }
}
