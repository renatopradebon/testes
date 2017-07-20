import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ProdutoAppVersaoComponent } from './produto-app-versao.component';
import { ProdutoAppVersaoDetailComponent } from './produto-app-versao-detail.component';
import { ProdutoAppVersaoPopupComponent } from './produto-app-versao-dialog.component';
import { ProdutoAppVersaoDeletePopupComponent } from './produto-app-versao-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ProdutoAppVersaoResolvePagingParams implements Resolve<any> {

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

export const produtoAppVersaoRoute: Routes = [
  {
    path: 'produto-app-versao',
    component: ProdutoAppVersaoComponent,
    resolve: {
      'pagingParams': ProdutoAppVersaoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoAppVersao.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'produto-app-versao/:id',
    component: ProdutoAppVersaoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoAppVersao.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const produtoAppVersaoPopupRoute: Routes = [
  {
    path: 'produto-app-versao-new',
    component: ProdutoAppVersaoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoAppVersao.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'produto-app-versao/:id/edit',
    component: ProdutoAppVersaoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoAppVersao.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'produto-app-versao/:id/delete',
    component: ProdutoAppVersaoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoAppVersao.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
