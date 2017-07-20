import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ServidorComponent } from './servidor.component';
import { ServidorDetailComponent } from './servidor-detail.component';
import { ServidorPopupComponent } from './servidor-dialog.component';
import { ServidorDeletePopupComponent } from './servidor-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ServidorResolvePagingParams implements Resolve<any> {

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

export const servidorRoute: Routes = [
  {
    path: 'servidor',
    component: ServidorComponent,
    resolve: {
      'pagingParams': ServidorResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'servidor/:id',
    component: ServidorDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servidor.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const servidorPopupRoute: Routes = [
  {
    path: 'servidor-new',
    component: ServidorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servidor.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servidor/:id/edit',
    component: ServidorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servidor.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servidor/:id/delete',
    component: ServidorDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servidor.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
