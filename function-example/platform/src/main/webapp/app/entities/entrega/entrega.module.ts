import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    EntregaService,
    EntregaPopupService,
    EntregaComponent,
    EntregaDetailComponent,
    EntregaDialogComponent,
    EntregaPopupComponent,
    EntregaDeletePopupComponent,
    EntregaDeleteDialogComponent,
    entregaRoute,
    entregaPopupRoute,
    EntregaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...entregaRoute,
    ...entregaPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EntregaComponent,
        EntregaDetailComponent,
        EntregaDialogComponent,
        EntregaDeleteDialogComponent,
        EntregaPopupComponent,
        EntregaDeletePopupComponent,
    ],
    entryComponents: [
        EntregaComponent,
        EntregaDialogComponent,
        EntregaPopupComponent,
        EntregaDeleteDialogComponent,
        EntregaDeletePopupComponent,
    ],
    providers: [
        EntregaService,
        EntregaPopupService,
        EntregaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformEntregaModule {}
