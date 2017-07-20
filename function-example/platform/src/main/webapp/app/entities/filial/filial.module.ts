import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    FilialService,
    FilialPopupService,
    FilialComponent,
    FilialDetailComponent,
    FilialDialogComponent,
    FilialPopupComponent,
    FilialDeletePopupComponent,
    FilialDeleteDialogComponent,
    filialRoute,
    filialPopupRoute,
    FilialResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...filialRoute,
    ...filialPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FilialComponent,
        FilialDetailComponent,
        FilialDialogComponent,
        FilialDeleteDialogComponent,
        FilialPopupComponent,
        FilialDeletePopupComponent,
    ],
    entryComponents: [
        FilialComponent,
        FilialDialogComponent,
        FilialPopupComponent,
        FilialDeleteDialogComponent,
        FilialDeletePopupComponent,
    ],
    providers: [
        FilialService,
        FilialPopupService,
        FilialResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformFilialModule {}
