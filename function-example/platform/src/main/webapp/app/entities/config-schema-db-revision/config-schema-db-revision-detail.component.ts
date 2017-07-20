import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConfigSchemaDbRevision } from './config-schema-db-revision.model';
import { ConfigSchemaDbRevisionService } from './config-schema-db-revision.service';

@Component({
    selector: 'jhi-config-schema-db-revision-detail',
    templateUrl: './config-schema-db-revision-detail.component.html'
})
export class ConfigSchemaDbRevisionDetailComponent implements OnInit, OnDestroy {

    configSchemaDbRevision: ConfigSchemaDbRevision;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private configSchemaDbRevisionService: ConfigSchemaDbRevisionService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['configSchemaDbRevision']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfigSchemaDbRevisions();
    }

    load(id) {
        this.configSchemaDbRevisionService.find(id).subscribe((configSchemaDbRevision) => {
            this.configSchemaDbRevision = configSchemaDbRevision;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfigSchemaDbRevisions() {
        this.eventSubscriber = this.eventManager.subscribe('configSchemaDbRevisionListModification', (response) => this.load(this.configSchemaDbRevision.id));
    }
}
