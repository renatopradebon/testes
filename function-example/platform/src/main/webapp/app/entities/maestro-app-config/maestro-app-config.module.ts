import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    MaestroAppConfigService,
    MaestroAppConfigPopupService,
    MaestroAppConfigComponent,
    MaestroAppConfigDetailComponent,
    MaestroAppConfigDialogComponent,
    MaestroAppConfigPopupComponent,
    MaestroAppConfigDeletePopupComponent,
    MaestroAppConfigDeleteDialogComponent,
    maestroAppConfigRoute,
    maestroAppConfigPopupRoute,
    MaestroAppConfigResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...maestroAppConfigRoute,
    ...maestroAppConfigPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MaestroAppConfigComponent,
        MaestroAppConfigDetailComponent,
        MaestroAppConfigDialogComponent,
        MaestroAppConfigDeleteDialogComponent,
        MaestroAppConfigPopupComponent,
        MaestroAppConfigDeletePopupComponent,
    ],
    entryComponents: [
        MaestroAppConfigComponent,
        MaestroAppConfigDialogComponent,
        MaestroAppConfigPopupComponent,
        MaestroAppConfigDeleteDialogComponent,
        MaestroAppConfigDeletePopupComponent,
    ],
    providers: [
        MaestroAppConfigService,
        MaestroAppConfigPopupService,
        MaestroAppConfigResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformMaestroAppConfigModule {}
