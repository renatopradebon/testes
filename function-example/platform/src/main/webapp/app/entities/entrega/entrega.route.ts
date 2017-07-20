import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { EntregaComponent } from './entrega.component';
import { EntregaDetailComponent } from './entrega-detail.component';
import { EntregaPopupComponent } from './entrega-dialog.component';
import { EntregaDeletePopupComponent } from './entrega-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EntregaResolvePagingParams implements Resolve<any> {

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

export const entregaRoute: Routes = [
  {
    path: 'entrega',
    component: EntregaComponent,
    resolve: {
      'pagingParams': EntregaResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entrega.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'entrega/:id',
    component: EntregaDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entrega.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entregaPopupRoute: Routes = [
  {
    path: 'entrega-new',
    component: EntregaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'entrega/:id/edit',
    component: EntregaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'entrega/:id/delete',
    component: EntregaDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.entrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
