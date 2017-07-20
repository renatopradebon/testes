import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ServicoDbComponent } from './servico-db.component';
import { ServicoDbDetailComponent } from './servico-db-detail.component';
import { ServicoDbPopupComponent } from './servico-db-dialog.component';
import { ServicoDbDeletePopupComponent } from './servico-db-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ServicoDbResolvePagingParams implements Resolve<any> {

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

export const servicoDbRoute: Routes = [
  {
    path: 'servico-db',
    component: ServicoDbComponent,
    resolve: {
      'pagingParams': ServicoDbResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoDb.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'servico-db/:id',
    component: ServicoDbDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoDb.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const servicoDbPopupRoute: Routes = [
  {
    path: 'servico-db-new',
    component: ServicoDbPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servico-db/:id/edit',
    component: ServicoDbPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servico-db/:id/delete',
    component: ServicoDbDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
