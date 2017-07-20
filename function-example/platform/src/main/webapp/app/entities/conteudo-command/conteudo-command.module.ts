import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConteudoCommandService,
    ConteudoCommandPopupService,
    ConteudoCommandComponent,
    ConteudoCommandDetailComponent,
    ConteudoCommandDialogComponent,
    ConteudoCommandPopupComponent,
    ConteudoCommandDeletePopupComponent,
    ConteudoCommandDeleteDialogComponent,
    conteudoCommandRoute,
    conteudoCommandPopupRoute,
    ConteudoCommandResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...conteudoCommandRoute,
    ...conteudoCommandPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConteudoCommandComponent,
        ConteudoCommandDetailComponent,
        ConteudoCommandDialogComponent,
        ConteudoCommandDeleteDialogComponent,
        ConteudoCommandPopupComponent,
        ConteudoCommandDeletePopupComponent,
    ],
    entryComponents: [
        ConteudoCommandComponent,
        ConteudoCommandDialogComponent,
        ConteudoCommandPopupComponent,
        ConteudoCommandDeleteDialogComponent,
        ConteudoCommandDeletePopupComponent,
    ],
    providers: [
        ConteudoCommandService,
        ConteudoCommandPopupService,
        ConteudoCommandResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConteudoCommandModule {}
