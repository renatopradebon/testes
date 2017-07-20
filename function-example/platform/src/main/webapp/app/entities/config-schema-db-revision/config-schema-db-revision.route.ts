import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConfigSchemaDbRevisionComponent } from './config-schema-db-revision.component';
import { ConfigSchemaDbRevisionDetailComponent } from './config-schema-db-revision-detail.component';
import { ConfigSchemaDbRevisionPopupComponent } from './config-schema-db-revision-dialog.component';
import { ConfigSchemaDbRevisionDeletePopupComponent } from './config-schema-db-revision-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConfigSchemaDbRevisionResolvePagingParams implements Resolve<any> {

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

export const configSchemaDbRevisionRoute: Routes = [
  {
    path: 'config-schema-db-revision',
    component: ConfigSchemaDbRevisionComponent,
    resolve: {
      'pagingParams': ConfigSchemaDbRevisionResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'config-schema-db-revision/:id',
    component: ConfigSchemaDbRevisionDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const configSchemaDbRevisionPopupRoute: Routes = [
  {
    path: 'config-schema-db-revision-new',
    component: ConfigSchemaDbRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema-db-revision/:id/edit',
    component: ConfigSchemaDbRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema-db-revision/:id/delete',
    component: ConfigSchemaDbRevisionDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaDbRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
