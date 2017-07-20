import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { EstagioExecucaoEntregaComponent } from './estagio-execucao-entrega.component';
import { EstagioExecucaoEntregaDetailComponent } from './estagio-execucao-entrega-detail.component';
import { EstagioExecucaoEntregaPopupComponent } from './estagio-execucao-entrega-dialog.component';
import { EstagioExecucaoEntregaDeletePopupComponent } from './estagio-execucao-entrega-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class EstagioExecucaoEntregaResolvePagingParams implements Resolve<any> {

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

export const estagioExecucaoEntregaRoute: Routes = [
  {
    path: 'estagio-execucao-entrega',
    component: EstagioExecucaoEntregaComponent,
    resolve: {
      'pagingParams': EstagioExecucaoEntregaResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.estagioExecucaoEntrega.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'estagio-execucao-entrega/:id',
    component: EstagioExecucaoEntregaDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.estagioExecucaoEntrega.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const estagioExecucaoEntregaPopupRoute: Routes = [
  {
    path: 'estagio-execucao-entrega-new',
    component: EstagioExecucaoEntregaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.estagioExecucaoEntrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'estagio-execucao-entrega/:id/edit',
    component: EstagioExecucaoEntregaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.estagioExecucaoEntrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'estagio-execucao-entrega/:id/delete',
    component: EstagioExecucaoEntregaDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.estagioExecucaoEntrega.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
