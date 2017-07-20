import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    EntregaAlvoService,
    EntregaAlvoPopupService,
    EntregaAlvoComponent,
    EntregaAlvoDetailComponent,
    EntregaAlvoDialogComponent,
    EntregaAlvoPopupComponent,
    EntregaAlvoDeletePopupComponent,
    EntregaAlvoDeleteDialogComponent,
    entregaAlvoRoute,
    entregaAlvoPopupRoute,
    EntregaAlvoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entregaAlvoRoute,
    ...entregaAlvoPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntregaAlvoComponent,
        EntregaAlvoDetailComponent,
        EntregaAlvoDialogComponent,
        EntregaAlvoDeleteDialogComponent,
        EntregaAlvoPopupComponent,
        EntregaAlvoDeletePopupComponent,
    ],
    entryComponents: [
        EntregaAlvoComponent,
        EntregaAlvoDialogComponent,
        EntregaAlvoPopupComponent,
        EntregaAlvoDeleteDialogComponent,
        EntregaAlvoDeletePopupComponent,
    ],
    providers: [
        EntregaAlvoService,
        EntregaAlvoPopupService,
        EntregaAlvoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformEntregaAlvoModule {}
