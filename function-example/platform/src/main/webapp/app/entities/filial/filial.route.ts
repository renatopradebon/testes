import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { FilialComponent } from './filial.component';
import { FilialDetailComponent } from './filial-detail.component';
import { FilialPopupComponent } from './filial-dialog.component';
import { FilialDeletePopupComponent } from './filial-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class FilialResolvePagingParams implements Resolve<any> {

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

export const filialRoute: Routes = [
  {
    path: 'filial',
    component: FilialComponent,
    resolve: {
      'pagingParams': FilialResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.filial.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'filial/:id',
    component: FilialDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.filial.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const filialPopupRoute: Routes = [
  {
    path: 'filial-new',
    component: FilialPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.filial.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'filial/:id/edit',
    component: FilialPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.filial.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'filial/:id/delete',
    component: FilialDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.filial.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
