import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ServidorService,
    ServidorPopupService,
    ServidorComponent,
    ServidorDetailComponent,
    ServidorDialogComponent,
    ServidorPopupComponent,
    ServidorDeletePopupComponent,
    ServidorDeleteDialogComponent,
    servidorRoute,
    servidorPopupRoute,
    ServidorResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...servidorRoute,
    ...servidorPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ServidorComponent,
        ServidorDetailComponent,
        ServidorDialogComponent,
        ServidorDeleteDialogComponent,
        ServidorPopupComponent,
        ServidorDeletePopupComponent,
    ],
    entryComponents: [
        ServidorComponent,
        ServidorDialogComponent,
        ServidorPopupComponent,
        ServidorDeleteDialogComponent,
        ServidorDeletePopupComponent,
    ],
    providers: [
        ServidorService,
        ServidorPopupService,
        ServidorResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformServidorModule {}
