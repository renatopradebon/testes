import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConteudoDbService,
    ConteudoDbPopupService,
    ConteudoDbComponent,
    ConteudoDbDetailComponent,
    ConteudoDbDialogComponent,
    ConteudoDbPopupComponent,
    ConteudoDbDeletePopupComponent,
    ConteudoDbDeleteDialogComponent,
    conteudoDbRoute,
    conteudoDbPopupRoute,
    ConteudoDbResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...conteudoDbRoute,
    ...conteudoDbPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConteudoDbComponent,
        ConteudoDbDetailComponent,
        ConteudoDbDialogComponent,
        ConteudoDbDeleteDialogComponent,
        ConteudoDbPopupComponent,
        ConteudoDbDeletePopupComponent,
    ],
    entryComponents: [
        ConteudoDbComponent,
        ConteudoDbDialogComponent,
        ConteudoDbPopupComponent,
        ConteudoDbDeleteDialogComponent,
        ConteudoDbDeletePopupComponent,
    ],
    providers: [
        ConteudoDbService,
        ConteudoDbPopupService,
        ConteudoDbResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConteudoDbModule {}
