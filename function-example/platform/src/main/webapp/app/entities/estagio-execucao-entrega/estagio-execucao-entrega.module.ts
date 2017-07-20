import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    EstagioExecucaoEntregaService,
    EstagioExecucaoEntregaPopupService,
    EstagioExecucaoEntregaComponent,
    EstagioExecucaoEntregaDetailComponent,
    EstagioExecucaoEntregaDialogComponent,
    EstagioExecucaoEntregaPopupComponent,
    EstagioExecucaoEntregaDeletePopupComponent,
    EstagioExecucaoEntregaDeleteDialogComponent,
    estagioExecucaoEntregaRoute,
    estagioExecucaoEntregaPopupRoute,
    EstagioExecucaoEntregaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...estagioExecucaoEntregaRoute,
    ...estagioExecucaoEntregaPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        EstagioExecucaoEntregaComponent,
        EstagioExecucaoEntregaDetailComponent,
        EstagioExecucaoEntregaDialogComponent,
        EstagioExecucaoEntregaDeleteDialogComponent,
        EstagioExecucaoEntregaPopupComponent,
        EstagioExecucaoEntregaDeletePopupComponent,
    ],
    entryComponents: [
        EstagioExecucaoEntregaComponent,
        EstagioExecucaoEntregaDialogComponent,
        EstagioExecucaoEntregaPopupComponent,
        EstagioExecucaoEntregaDeleteDialogComponent,
        EstagioExecucaoEntregaDeletePopupComponent,
    ],
    providers: [
        EstagioExecucaoEntregaService,
        EstagioExecucaoEntregaPopupService,
        EstagioExecucaoEntregaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformEstagioExecucaoEntregaModule {}
