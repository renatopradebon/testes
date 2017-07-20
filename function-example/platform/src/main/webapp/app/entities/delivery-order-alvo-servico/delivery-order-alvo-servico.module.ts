import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    DeliveryOrderAlvoServicoService,
    DeliveryOrderAlvoServicoPopupService,
    DeliveryOrderAlvoServicoComponent,
    DeliveryOrderAlvoServicoDetailComponent,
    DeliveryOrderAlvoServicoDialogComponent,
    DeliveryOrderAlvoServicoPopupComponent,
    DeliveryOrderAlvoServicoDeletePopupComponent,
    DeliveryOrderAlvoServicoDeleteDialogComponent,
    deliveryOrderAlvoServicoRoute,
    deliveryOrderAlvoServicoPopupRoute,
    DeliveryOrderAlvoServicoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...deliveryOrderAlvoServicoRoute,
    ...deliveryOrderAlvoServicoPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DeliveryOrderAlvoServicoComponent,
        DeliveryOrderAlvoServicoDetailComponent,
        DeliveryOrderAlvoServicoDialogComponent,
        DeliveryOrderAlvoServicoDeleteDialogComponent,
        DeliveryOrderAlvoServicoPopupComponent,
        DeliveryOrderAlvoServicoDeletePopupComponent,
    ],
    entryComponents: [
        DeliveryOrderAlvoServicoComponent,
        DeliveryOrderAlvoServicoDialogComponent,
        DeliveryOrderAlvoServicoPopupComponent,
        DeliveryOrderAlvoServicoDeleteDialogComponent,
        DeliveryOrderAlvoServicoDeletePopupComponent,
    ],
    providers: [
        DeliveryOrderAlvoServicoService,
        DeliveryOrderAlvoServicoPopupService,
        DeliveryOrderAlvoServicoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformDeliveryOrderAlvoServicoModule {}
