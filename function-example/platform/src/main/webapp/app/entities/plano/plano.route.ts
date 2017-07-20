import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { PlanoComponent } from './plano.component';
import { PlanoDetailComponent } from './plano-detail.component';
import { PlanoPopupComponent } from './plano-dialog.component';
import { PlanoDeletePopupComponent } from './plano-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class PlanoResolvePagingParams implements Resolve<any> {

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

export const planoRoute: Routes = [
  {
    path: 'plano',
    component: PlanoComponent,
    resolve: {
      'pagingParams': PlanoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.plano.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'plano/:id',
    component: PlanoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.plano.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const planoPopupRoute: Routes = [
  {
    path: 'plano-new',
    component: PlanoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.plano.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'plano/:id/edit',
    component: PlanoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.plano.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'plano/:id/delete',
    component: PlanoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.plano.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
