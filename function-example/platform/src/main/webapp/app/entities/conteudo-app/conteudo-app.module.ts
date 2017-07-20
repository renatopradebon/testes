import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConteudoAppService,
    ConteudoAppPopupService,
    ConteudoAppComponent,
    ConteudoAppDetailComponent,
    ConteudoAppDialogComponent,
    ConteudoAppPopupComponent,
    ConteudoAppDeletePopupComponent,
    ConteudoAppDeleteDialogComponent,
    conteudoAppRoute,
    conteudoAppPopupRoute,
    ConteudoAppResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...conteudoAppRoute,
    ...conteudoAppPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConteudoAppComponent,
        ConteudoAppDetailComponent,
        ConteudoAppDialogComponent,
        ConteudoAppDeleteDialogComponent,
        ConteudoAppPopupComponent,
        ConteudoAppDeletePopupComponent,
    ],
    entryComponents: [
        ConteudoAppComponent,
        ConteudoAppDialogComponent,
        ConteudoAppPopupComponent,
        ConteudoAppDeleteDialogComponent,
        ConteudoAppDeletePopupComponent,
    ],
    providers: [
        ConteudoAppService,
        ConteudoAppPopupService,
        ConteudoAppResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConteudoAppModule {}
