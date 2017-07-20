import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { EntregaAlvoComponent } from './entrega-alvo.component';
import { EntregaAlvoDetailComponent } from './entrega-alvo-detail.component';
import { EntregaAlvoPopupComponent } from './entrega-alvo-dialog.component';
import { EntregaAlvoDeletePopupComponent } from './entrega-alvo-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntregaAlvoResolvePagingParams implements Resolve<any> {

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

export const entregaAlvoRoute: Routes = [
  {
    path: 'entrega-alvo',
    component: EntregaAlvoComponent,
    resolve: {
      'pagingParams': EntregaAlvoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entregaAlvo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'entrega-alvo/:id',
    component: EntregaAlvoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entregaAlvo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entregaAlvoPopupRoute: Routes = [
  {
    path: 'entrega-alvo-new',
    component: EntregaAlvoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entregaAlvo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'entrega-alvo/:id/edit',
    component: EntregaAlvoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entregaAlvo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'entrega-alvo/:id/delete',
    component: EntregaAlvoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entregaAlvo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
