import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ProdutoDbRevisionComponent } from './produto-db-revision.component';
import { ProdutoDbRevisionDetailComponent } from './produto-db-revision-detail.component';
import { ProdutoDbRevisionPopupComponent } from './produto-db-revision-dialog.component';
import { ProdutoDbRevisionDeletePopupComponent } from './produto-db-revision-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ProdutoDbRevisionResolvePagingParams implements Resolve<any> {

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

export const produtoDbRevisionRoute: Routes = [
  {
    path: 'produto-db-revision',
    component: ProdutoDbRevisionComponent,
    resolve: {
      'pagingParams': ProdutoDbRevisionResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'produto-db-revision/:id',
    component: ProdutoDbRevisionDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const produtoDbRevisionPopupRoute: Routes = [
  {
    path: 'produto-db-revision-new',
    component: ProdutoDbRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'produto-db-revision/:id/edit',
    component: ProdutoDbRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'produto-db-revision/:id/delete',
    component: ProdutoDbRevisionDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.produtoDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
