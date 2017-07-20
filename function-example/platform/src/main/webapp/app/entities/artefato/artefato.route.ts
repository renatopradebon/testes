import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ArtefatoComponent } from './artefato.component';
import { ArtefatoDetailComponent } from './artefato-detail.component';
import { ArtefatoPopupComponent } from './artefato-dialog.component';
import { ArtefatoDeletePopupComponent } from './artefato-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ArtefatoResolvePagingParams implements Resolve<any> {

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

export const artefatoRoute: Routes = [
  {
    path: 'artefato',
    component: ArtefatoComponent,
    resolve: {
      'pagingParams': ArtefatoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.artefato.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'artefato/:id',
    component: ArtefatoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.artefato.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const artefatoPopupRoute: Routes = [
  {
    path: 'artefato-new',
    component: ArtefatoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.artefato.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'artefato/:id/edit',
    component: ArtefatoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.artefato.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'artefato/:id/delete',
    component: ArtefatoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.artefato.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
