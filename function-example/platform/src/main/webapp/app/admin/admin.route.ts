import { Routes } from '@angular/router';

import {
    docsRoute,
    logsRoute,
    metricsRoute,
    userMgmtRoute,
    userDialogRoute
} from './';

import { UserRouteAccessService } from '../shared';

let ADMIN_ROUTES = [
    docsRoute,
    logsRoute,
    ...userMgmtRoute,
    metricsRoute
];


export const adminState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: ADMIN_ROUTES
},
    ...userDialogRoute
];
