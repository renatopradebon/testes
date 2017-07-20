import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { MaestroAppConfigComponent } from './maestro-app-config.component';
import { MaestroAppConfigDetailComponent } from './maestro-app-config-detail.component';
import { MaestroAppConfigPopupComponent } from './maestro-app-config-dialog.component';
import { MaestroAppConfigDeletePopupComponent } from './maestro-app-config-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class MaestroAppConfigResolvePagingParams implements Resolve<any> {

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

export const maestroAppConfigRoute: Routes = [
  {
    path: 'maestro-app-config',
    component: MaestroAppConfigComponent,
    resolve: {
      'pagingParams': MaestroAppConfigResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.maestroAppConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'maestro-app-config/:id',
    component: MaestroAppConfigDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.maestroAppConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const maestroAppConfigPopupRoute: Routes = [
  {
    path: 'maestro-app-config-new',
    component: MaestroAppConfigPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.maestroAppConfig.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'maestro-app-config/:id/edit',
    component: MaestroAppConfigPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.maestroAppConfig.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'maestro-app-config/:id/delete',
    component: MaestroAppConfigDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.maestroAppConfig.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
