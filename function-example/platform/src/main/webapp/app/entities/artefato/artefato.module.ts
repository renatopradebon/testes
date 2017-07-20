import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ArtefatoService,
    ArtefatoPopupService,
    ArtefatoComponent,
    ArtefatoDetailComponent,
    ArtefatoDialogComponent,
    ArtefatoPopupComponent,
    ArtefatoDeletePopupComponent,
    ArtefatoDeleteDialogComponent,
    artefatoRoute,
    artefatoPopupRoute,
    ArtefatoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...artefatoRoute,
    ...artefatoPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ArtefatoComponent,
        ArtefatoDetailComponent,
        ArtefatoDialogComponent,
        ArtefatoDeleteDialogComponent,
        ArtefatoPopupComponent,
        ArtefatoDeletePopupComponent,
    ],
    entryComponents: [
        ArtefatoComponent,
        ArtefatoDialogComponent,
        ArtefatoPopupComponent,
        ArtefatoDeleteDialogComponent,
        ArtefatoDeletePopupComponent,
    ],
    providers: [
        ArtefatoService,
        ArtefatoPopupService,
        ArtefatoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformArtefatoModule {}
