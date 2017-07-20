import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    DeliveryOrderService,
    DeliveryOrderPopupService,
    DeliveryOrderComponent,
    DeliveryOrderDetailComponent,
    DeliveryOrderDialogComponent,
    DeliveryOrderPopupComponent,
    DeliveryOrderDeletePopupComponent,
    DeliveryOrderDeleteDialogComponent,
    deliveryOrderRoute,
    deliveryOrderPopupRoute,
    DeliveryOrderResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...deliveryOrderRoute,
    ...deliveryOrderPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DeliveryOrderComponent,
        DeliveryOrderDetailComponent,
        DeliveryOrderDialogComponent,
        DeliveryOrderDeleteDialogComponent,
        DeliveryOrderPopupComponent,
        DeliveryOrderDeletePopupComponent,
    ],
    entryComponents: [
        DeliveryOrderComponent,
        DeliveryOrderDialogComponent,
        DeliveryOrderPopupComponent,
        DeliveryOrderDeleteDialogComponent,
        DeliveryOrderDeletePopupComponent,
    ],
    providers: [
        DeliveryOrderService,
        DeliveryOrderPopupService,
        DeliveryOrderResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformDeliveryOrderModule {}
