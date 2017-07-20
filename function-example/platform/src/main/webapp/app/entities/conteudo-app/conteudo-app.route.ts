import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConteudoAppComponent } from './conteudo-app.component';
import { ConteudoAppDetailComponent } from './conteudo-app-detail.component';
import { ConteudoAppPopupComponent } from './conteudo-app-dialog.component';
import { ConteudoAppDeletePopupComponent } from './conteudo-app-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConteudoAppResolvePagingParams implements Resolve<any> {

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

export const conteudoAppRoute: Routes = [
  {
    path: 'conteudo-app',
    component: ConteudoAppComponent,
    resolve: {
      'pagingParams': ConteudoAppResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoApp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'conteudo-app/:id',
    component: ConteudoAppDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoApp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conteudoAppPopupRoute: Routes = [
  {
    path: 'conteudo-app-new',
    component: ConteudoAppPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-app/:id/edit',
    component: ConteudoAppPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-app/:id/delete',
    component: ConteudoAppDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoApp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
