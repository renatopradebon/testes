import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ProdutoDbRevisionService,
    ProdutoDbRevisionPopupService,
    ProdutoDbRevisionComponent,
    ProdutoDbRevisionDetailComponent,
    ProdutoDbRevisionDialogComponent,
    ProdutoDbRevisionPopupComponent,
    ProdutoDbRevisionDeletePopupComponent,
    ProdutoDbRevisionDeleteDialogComponent,
    produtoDbRevisionRoute,
    produtoDbRevisionPopupRoute,
    ProdutoDbRevisionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...produtoDbRevisionRoute,
    ...produtoDbRevisionPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProdutoDbRevisionComponent,
        ProdutoDbRevisionDetailComponent,
        ProdutoDbRevisionDialogComponent,
        ProdutoDbRevisionDeleteDialogComponent,
        ProdutoDbRevisionPopupComponent,
        ProdutoDbRevisionDeletePopupComponent,
    ],
    entryComponents: [
        ProdutoDbRevisionComponent,
        ProdutoDbRevisionDialogComponent,
        ProdutoDbRevisionPopupComponent,
        ProdutoDbRevisionDeleteDialogComponent,
        ProdutoDbRevisionDeletePopupComponent,
    ],
    providers: [
        ProdutoDbRevisionService,
        ProdutoDbRevisionPopupService,
        ProdutoDbRevisionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformProdutoDbRevisionModule {}
