import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { ConfigSchema } from './config-schema.model';
import { ConfigSchemaService } from './config-schema.service';

@Component({
    selector: 'jhi-config-schema-detail',
    templateUrl: './config-schema-detail.component.html'
})
export class ConfigSchemaDetailComponent implements OnInit, OnDestroy {

    configSchema: ConfigSchema;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private configSchemaService: ConfigSchemaService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['configSchema']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConfigSchemas();
    }

    load(id) {
        this.configSchemaService.find(id).subscribe((configSchema) => {
            this.configSchema = configSchema;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConfigSchemas() {
        this.eventSubscriber = this.eventManager.subscribe('configSchemaListModification', (response) => this.load(this.configSchema.id));
    }
}
