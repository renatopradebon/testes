import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ServicoAppService,
    ServicoAppPopupService,
    ServicoAppComponent,
    ServicoAppDetailComponent,
    ServicoAppDialogComponent,
    ServicoAppPopupComponent,
    ServicoAppDeletePopupComponent,
    ServicoAppDeleteDialogComponent,
    servicoAppRoute,
    servicoAppPopupRoute,
    ServicoAppResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...servicoAppRoute,
    ...servicoAppPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ServicoAppComponent,
        ServicoAppDetailComponent,
        ServicoAppDialogComponent,
        ServicoAppDeleteDialogComponent,
        ServicoAppPopupComponent,
        ServicoAppDeletePopupComponent,
    ],
    entryComponents: [
        ServicoAppComponent,
        ServicoAppDialogComponent,
        ServicoAppPopupComponent,
        ServicoAppDeleteDialogComponent,
        ServicoAppDeletePopupComponent,
    ],
    providers: [
        ServicoAppService,
        ServicoAppPopupService,
        ServicoAppResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformServicoAppModule {}
