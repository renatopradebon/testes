import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConteudoDbComponent } from './conteudo-db.component';
import { ConteudoDbDetailComponent } from './conteudo-db-detail.component';
import { ConteudoDbPopupComponent } from './conteudo-db-dialog.component';
import { ConteudoDbDeletePopupComponent } from './conteudo-db-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConteudoDbResolvePagingParams implements Resolve<any> {

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

export const conteudoDbRoute: Routes = [
  {
    path: 'conteudo-db',
    component: ConteudoDbComponent,
    resolve: {
      'pagingParams': ConteudoDbResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoDb.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'conteudo-db/:id',
    component: ConteudoDbDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoDb.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conteudoDbPopupRoute: Routes = [
  {
    path: 'conteudo-db-new',
    component: ConteudoDbPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-db/:id/edit',
    component: ConteudoDbPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-db/:id/delete',
    component: ConteudoDbDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoDb.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
