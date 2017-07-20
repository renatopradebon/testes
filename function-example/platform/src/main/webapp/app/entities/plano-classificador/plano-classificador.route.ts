import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { PlanoClassificadorComponent } from './plano-classificador.component';
import { PlanoClassificadorDetailComponent } from './plano-classificador-detail.component';
import { PlanoClassificadorPopupComponent } from './plano-classificador-dialog.component';
import { PlanoClassificadorDeletePopupComponent } from './plano-classificador-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class PlanoClassificadorResolvePagingParams implements Resolve<any> {

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

export const planoClassificadorRoute: Routes = [
  {
    path: 'plano-classificador',
    component: PlanoClassificadorComponent,
    resolve: {
      'pagingParams': PlanoClassificadorResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.planoClassificador.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'plano-classificador/:id',
    component: PlanoClassificadorDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.planoClassificador.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const planoClassificadorPopupRoute: Routes = [
  {
    path: 'plano-classificador-new',
    component: PlanoClassificadorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.planoClassificador.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'plano-classificador/:id/edit',
    component: PlanoClassificadorPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.planoClassificador.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'plano-classificador/:id/delete',
    component: PlanoClassificadorDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.planoClassificador.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
