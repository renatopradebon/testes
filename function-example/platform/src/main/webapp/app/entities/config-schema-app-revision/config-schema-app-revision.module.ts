import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConfigSchemaAppRevisionService,
    ConfigSchemaAppRevisionPopupService,
    ConfigSchemaAppRevisionComponent,
    ConfigSchemaAppRevisionDetailComponent,
    ConfigSchemaAppRevisionDialogComponent,
    ConfigSchemaAppRevisionPopupComponent,
    ConfigSchemaAppRevisionDeletePopupComponent,
    ConfigSchemaAppRevisionDeleteDialogComponent,
    configSchemaAppRevisionRoute,
    configSchemaAppRevisionPopupRoute,
    ConfigSchemaAppRevisionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configSchemaAppRevisionRoute,
    ...configSchemaAppRevisionPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConfigSchemaAppRevisionComponent,
        ConfigSchemaAppRevisionDetailComponent,
        ConfigSchemaAppRevisionDialogComponent,
        ConfigSchemaAppRevisionDeleteDialogComponent,
        ConfigSchemaAppRevisionPopupComponent,
        ConfigSchemaAppRevisionDeletePopupComponent,
    ],
    entryComponents: [
        ConfigSchemaAppRevisionComponent,
        ConfigSchemaAppRevisionDialogComponent,
        ConfigSchemaAppRevisionPopupComponent,
        ConfigSchemaAppRevisionDeleteDialogComponent,
        ConfigSchemaAppRevisionDeletePopupComponent,
    ],
    providers: [
        ConfigSchemaAppRevisionService,
        ConfigSchemaAppRevisionPopupService,
        ConfigSchemaAppRevisionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConfigSchemaAppRevisionModule {}
