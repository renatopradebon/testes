import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConfigSchemaAppRevision } from './config-schema-app-revision.model';
import { ConfigSchemaAppRevisionService } from './config-schema-app-revision.service';

@Component({
    selector: 'jhi-config-schema-app-revision-detail',
    templateUrl: './config-schema-app-revision-detail.component.html'
})
export class ConfigSchemaAppRevisionDetailComponent implements OnInit, OnDestroy {

    configSchemaAppRevision: ConfigSchemaAppRevision;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private configSchemaAppRevisionService: ConfigSchemaAppRevisionService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['configSchemaAppRevision']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfigSchemaAppRevisions();
    }

    load(id) {
        this.configSchemaAppRevisionService.find(id).subscribe((configSchemaAppRevision) => {
            this.configSchemaAppRevision = configSchemaAppRevision;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfigSchemaAppRevisions() {
        this.eventSubscriber = this.eventManager.subscribe('configSchemaAppRevisionListModification', (response) => this.load(this.configSchemaAppRevision.id));
    }
}
