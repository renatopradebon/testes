import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    PlanoClassificadorService,
    PlanoClassificadorPopupService,
    PlanoClassificadorComponent,
    PlanoClassificadorDetailComponent,
    PlanoClassificadorDialogComponent,
    PlanoClassificadorPopupComponent,
    PlanoClassificadorDeletePopupComponent,
    PlanoClassificadorDeleteDialogComponent,
    planoClassificadorRoute,
    planoClassificadorPopupRoute,
    PlanoClassificadorResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...planoClassificadorRoute,
    ...planoClassificadorPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PlanoClassificadorComponent,
        PlanoClassificadorDetailComponent,
        PlanoClassificadorDialogComponent,
        PlanoClassificadorDeleteDialogComponent,
        PlanoClassificadorPopupComponent,
        PlanoClassificadorDeletePopupComponent,
    ],
    entryComponents: [
        PlanoClassificadorComponent,
        PlanoClassificadorDialogComponent,
        PlanoClassificadorPopupComponent,
        PlanoClassificadorDeleteDialogComponent,
        PlanoClassificadorDeletePopupComponent,
    ],
    providers: [
        PlanoClassificadorService,
        PlanoClassificadorPopupService,
        PlanoClassificadorResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformPlanoClassificadorModule {}
