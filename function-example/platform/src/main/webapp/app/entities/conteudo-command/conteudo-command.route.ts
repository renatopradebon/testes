import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConteudoCommandComponent } from './conteudo-command.component';
import { ConteudoCommandDetailComponent } from './conteudo-command-detail.component';
import { ConteudoCommandPopupComponent } from './conteudo-command-dialog.component';
import { ConteudoCommandDeletePopupComponent } from './conteudo-command-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConteudoCommandResolvePagingParams implements Resolve<any> {

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

export const conteudoCommandRoute: Routes = [
  {
    path: 'conteudo-command',
    component: ConteudoCommandComponent,
    resolve: {
      'pagingParams': ConteudoCommandResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoCommand.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'conteudo-command/:id',
    component: ConteudoCommandDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoCommand.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conteudoCommandPopupRoute: Routes = [
  {
    path: 'conteudo-command-new',
    component: ConteudoCommandPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoCommand.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-command/:id/edit',
    component: ConteudoCommandPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoCommand.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'conteudo-command/:id/delete',
    component: ConteudoCommandDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.conteudoCommand.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
