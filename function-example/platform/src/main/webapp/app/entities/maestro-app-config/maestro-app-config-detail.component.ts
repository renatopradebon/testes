import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { MaestroAppConfig } from './maestro-app-config.model';
import { MaestroAppConfigService } from './maestro-app-config.service';

@Component({
    selector: 'jhi-maestro-app-config-detail',
    templateUrl: './maestro-app-config-detail.component.html'
})
export class MaestroAppConfigDetailComponent implements OnInit, OnDestroy {

    maestroAppConfig: MaestroAppConfig;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private maestroAppConfigService: MaestroAppConfigService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['maestroAppConfig']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMaestroAppConfigs();
    }

    load(id) {
        this.maestroAppConfigService.find(id).subscribe((maestroAppConfig) => {
            this.maestroAppConfig = maestroAppConfig;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMaestroAppConfigs() {
        this.eventSubscriber = this.eventManager.subscribe('maestroAppConfigListModification', (response) => this.load(this.maestroAppConfig.id));
    }
}
