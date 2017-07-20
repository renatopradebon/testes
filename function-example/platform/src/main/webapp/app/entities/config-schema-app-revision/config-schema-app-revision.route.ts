import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConfigSchemaAppRevisionComponent } from './config-schema-app-revision.component';
import { ConfigSchemaAppRevisionDetailComponent } from './config-schema-app-revision-detail.component';
import { ConfigSchemaAppRevisionPopupComponent } from './config-schema-app-revision-dialog.component';
import { ConfigSchemaAppRevisionDeletePopupComponent } from './config-schema-app-revision-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConfigSchemaAppRevisionResolvePagingParams implements Resolve<any> {

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

export const configSchemaAppRevisionRoute: Routes = [
  {
    path: 'config-schema-app-revision',
    component: ConfigSchemaAppRevisionComponent,
    resolve: {
      'pagingParams': ConfigSchemaAppRevisionResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaAppRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }, {
    path: 'config-schema-app-revision/:id',
    component: ConfigSchemaAppRevisionDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaAppRevision.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const configSchemaAppRevisionPopupRoute: Routes = [
  {
    path: 'config-schema-app-revision-new',
    component: ConfigSchemaAppRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaAppRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema-app-revision/:id/edit',
    component: ConfigSchemaAppRevisionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaAppRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  },
  {
    path: 'config-schema-app-revision/:id/delete',
    component: ConfigSchemaAppRevisionDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'platform.configSchemaAppRevision.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
