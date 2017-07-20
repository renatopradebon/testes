import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConfigSchemaService,
    ConfigSchemaPopupService,
    ConfigSchemaComponent,
    ConfigSchemaDetailComponent,
    ConfigSchemaDialogComponent,
    ConfigSchemaPopupComponent,
    ConfigSchemaDeletePopupComponent,
    ConfigSchemaDeleteDialogComponent,
    configSchemaRoute,
    configSchemaPopupRoute,
    ConfigSchemaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configSchemaRoute,
    ...configSchemaPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConfigSchemaComponent,
        ConfigSchemaDetailComponent,
        ConfigSchemaDialogComponent,
        ConfigSchemaDeleteDialogComponent,
        ConfigSchemaPopupComponent,
        ConfigSchemaDeletePopupComponent,
    ],
    entryComponents: [
        ConfigSchemaComponent,
        ConfigSchemaDialogComponent,
        ConfigSchemaPopupComponent,
        ConfigSchemaDeleteDialogComponent,
        ConfigSchemaDeletePopupComponent,
    ],
    providers: [
        ConfigSchemaService,
        ConfigSchemaPopupService,
        ConfigSchemaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConfigSchemaModule {}
