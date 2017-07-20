import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    PlanoService,
    PlanoPopupService,
    PlanoComponent,
    PlanoDetailComponent,
    PlanoDialogComponent,
    PlanoPopupComponent,
    PlanoDeletePopupComponent,
    PlanoDeleteDialogComponent,
    planoRoute,
    planoPopupRoute,
    PlanoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...planoRoute,
    ...planoPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PlanoComponent,
        PlanoDetailComponent,
        PlanoDialogComponent,
        PlanoDeleteDialogComponent,
        PlanoPopupComponent,
        PlanoDeletePopupComponent,
    ],
    entryComponents: [
        PlanoComponent,
        PlanoDialogComponent,
        PlanoPopupComponent,
        PlanoDeleteDialogComponent,
        PlanoDeletePopupComponent,
    ],
    providers: [
        PlanoService,
        PlanoPopupService,
        PlanoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformPlanoModule {}
