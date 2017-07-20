import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ServicoAppComponent } from './servico-app.component';
import { ServicoAppDetailComponent } from './servico-app-detail.component';
import { ServicoAppPopupComponent } from './servico-app-dialog.component';
import { ServicoAppDeletePopupComponent } from './servico-app-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ServicoAppResolvePagingParams implements Resolve<any> {

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

export const servicoAppRoute: Routes = [
  {
    path: 'servico-app',
    component: ServicoAppComponent,
    resolve: {
      'pagingParams': ServicoAppResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoApp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'servico-app/:id',
    component: ServicoAppDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoApp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const servicoAppPopupRoute: Routes = [
  {
    path: 'servico-app-new',
    component: ServicoAppPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servico-app/:id/edit',
    component: ServicoAppPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'servico-app/:id/delete',
    component: ServicoAppDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.servicoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
