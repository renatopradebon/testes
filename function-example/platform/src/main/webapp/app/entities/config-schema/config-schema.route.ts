import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConfigSchemaComponent } from './config-schema.component';
import { ConfigSchemaDetailComponent } from './config-schema-detail.component';
import { ConfigSchemaPopupComponent } from './config-schema-dialog.component';
import { ConfigSchemaDeletePopupComponent } from './config-schema-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConfigSchemaResolvePagingParams implements Resolve<any> {

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

export const configSchemaRoute: Routes = [
  {
    path: 'config-schema',
    component: ConfigSchemaComponent,
    resolve: {
      'pagingParams': ConfigSchemaResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'config-schema/:id',
    component: ConfigSchemaDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const configSchemaPopupRoute: Routes = [
  {
    path: 'config-schema-new',
    component: ConfigSchemaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchema.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema/:id/edit',
    component: ConfigSchemaPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchema.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema/:id/delete',
    component: ConfigSchemaDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchema.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
