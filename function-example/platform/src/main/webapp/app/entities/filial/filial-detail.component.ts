import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { Filial } from './filial.model';
import { FilialService } from './filial.service';

@Component({
    selector: 'jhi-filial-detail',
    templateUrl: './filial-detail.component.html'
})
export class FilialDetailComponent implements OnInit, OnDestroy {

    filial: Filial;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private filialService: FilialService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['filial']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFilials();
    }

    load(id) {
        this.filialService.find(id).subscribe((filial) => {
            this.filial = filial;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFilials() {
        this.eventSubscriber = this.eventManager.subscribe('filialListModification', (response) => this.load(this.filial.id));
    }
}
