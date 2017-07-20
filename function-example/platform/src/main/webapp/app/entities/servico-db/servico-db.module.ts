import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ServicoDbService,
    ServicoDbPopupService,
    ServicoDbComponent,
    ServicoDbDetailComponent,
    ServicoDbDialogComponent,
    ServicoDbPopupComponent,
    ServicoDbDeletePopupComponent,
    ServicoDbDeleteDialogComponent,
    servicoDbRoute,
    servicoDbPopupRoute,
    ServicoDbResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...servicoDbRoute,
    ...servicoDbPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ServicoDbComponent,
        ServicoDbDetailComponent,
        ServicoDbDialogComponent,
        ServicoDbDeleteDialogComponent,
        ServicoDbPopupComponent,
        ServicoDbDeletePopupComponent,
    ],
    entryComponents: [
        ServicoDbComponent,
        ServicoDbDialogComponent,
        ServicoDbPopupComponent,
        ServicoDbDeleteDialogComponent,
        ServicoDbDeletePopupComponent,
    ],
    providers: [
        ServicoDbService,
        ServicoDbPopupService,
        ServicoDbResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformServicoDbModule {}
