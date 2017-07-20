import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { DeliveryOrderComponent } from './delivery-order.component';
import { DeliveryOrderDetailComponent } from './delivery-order-detail.component';
import { DeliveryOrderPopupComponent } from './delivery-order-dialog.component';
import { DeliveryOrderDeletePopupComponent } from './delivery-order-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class DeliveryOrderResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: PaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
      const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
      return {
          page: this.paginationUtil.parsePage(page),
          predicate: this.paginationUtil.parsePredicate(sort),
          ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}

export const deliveryOrderRoute: Routes = [
  {
    path: 'delivery-order',
    component: DeliveryOrderComponent,
    resolve: {
      'pagingParams': DeliveryOrderResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'delivery-order/:id',
    component: DeliveryOrderDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const deliveryOrderPopupRoute: Routes = [
  {
    path: 'delivery-order-new',
    component: DeliveryOrderPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'delivery-order/:id/edit',
    component: DeliveryOrderPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'delivery-order/:id/delete',
    component: DeliveryOrderDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
