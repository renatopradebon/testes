import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ConfigSchemaDbRevisionService,
    ConfigSchemaDbRevisionPopupService,
    ConfigSchemaDbRevisionComponent,
    ConfigSchemaDbRevisionDetailComponent,
    ConfigSchemaDbRevisionDialogComponent,
    ConfigSchemaDbRevisionPopupComponent,
    ConfigSchemaDbRevisionDeletePopupComponent,
    ConfigSchemaDbRevisionDeleteDialogComponent,
    configSchemaDbRevisionRoute,
    configSchemaDbRevisionPopupRoute,
    ConfigSchemaDbRevisionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...configSchemaDbRevisionRoute,
    ...configSchemaDbRevisionPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ConfigSchemaDbRevisionComponent,
        ConfigSchemaDbRevisionDetailComponent,
        ConfigSchemaDbRevisionDialogComponent,
        ConfigSchemaDbRevisionDeleteDialogComponent,
        ConfigSchemaDbRevisionPopupComponent,
        ConfigSchemaDbRevisionDeletePopupComponent,
    ],
    entryComponents: [
        ConfigSchemaDbRevisionComponent,
        ConfigSchemaDbRevisionDialogComponent,
        ConfigSchemaDbRevisionPopupComponent,
        ConfigSchemaDbRevisionDeleteDialogComponent,
        ConfigSchemaDbRevisionDeletePopupComponent,
    ],
    providers: [
        ConfigSchemaDbRevisionService,
        ConfigSchemaDbRevisionPopupService,
        ConfigSchemaDbRevisionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformConfigSchemaDbRevisionModule {}
