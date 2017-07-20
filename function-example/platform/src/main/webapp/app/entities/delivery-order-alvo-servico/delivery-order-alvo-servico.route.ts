import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { DeliveryOrderAlvoServicoComponent } from './delivery-order-alvo-servico.component';
import { DeliveryOrderAlvoServicoDetailComponent } from './delivery-order-alvo-servico-detail.component';
import { DeliveryOrderAlvoServicoPopupComponent } from './delivery-order-alvo-servico-dialog.component';
import { DeliveryOrderAlvoServicoDeletePopupComponent } from './delivery-order-alvo-servico-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class DeliveryOrderAlvoServicoResolvePagingParams implements Resolve<any> {

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

export const deliveryOrderAlvoServicoRoute: Routes = [
  {
    path: 'delivery-order-alvo-servico',
    component: DeliveryOrderAlvoServicoComponent,
    resolve: {
      'pagingParams': DeliveryOrderAlvoServicoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrderAlvoServico.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'delivery-order-alvo-servico/:id',
    component: DeliveryOrderAlvoServicoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrderAlvoServico.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const deliveryOrderAlvoServicoPopupRoute: Routes = [
  {
    path: 'delivery-order-alvo-servico-new',
    component: DeliveryOrderAlvoServicoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrderAlvoServico.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'delivery-order-alvo-servico/:id/edit',
    component: DeliveryOrderAlvoServicoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrderAlvoServico.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'delivery-order-alvo-servico/:id/delete',
    component: DeliveryOrderAlvoServicoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.deliveryOrderAlvoServico.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
