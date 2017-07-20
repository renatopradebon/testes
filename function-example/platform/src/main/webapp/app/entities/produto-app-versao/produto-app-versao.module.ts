import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { platformSharedModule } from '../../shared';

import {
    ProdutoAppVersaoService,
    ProdutoAppVersaoPopupService,
    ProdutoAppVersaoComponent,
    ProdutoAppVersaoDetailComponent,
    ProdutoAppVersaoDialogComponent,
    ProdutoAppVersaoPopupComponent,
    ProdutoAppVersaoDeletePopupComponent,
    ProdutoAppVersaoDeleteDialogComponent,
    produtoAppVersaoRoute,
    produtoAppVersaoPopupRoute,
    ProdutoAppVersaoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...produtoAppVersaoRoute,
    ...produtoAppVersaoPopupRoute,
];

@NgModule({
    imports: [
        platformSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProdutoAppVersaoComponent,
        ProdutoAppVersaoDetailComponent,
        ProdutoAppVersaoDialogComponent,
        ProdutoAppVersaoDeleteDialogComponent,
        ProdutoAppVersaoPopupComponent,
        ProdutoAppVersaoDeletePopupComponent,
    ],
    entryComponents: [
        ProdutoAppVersaoComponent,
        ProdutoAppVersaoDialogComponent,
        ProdutoAppVersaoPopupComponent,
        ProdutoAppVersaoDeleteDialogComponent,
        ProdutoAppVersaoDeletePopupComponent,
    ],
    providers: [
        ProdutoAppVersaoService,
        ProdutoAppVersaoPopupService,
        ProdutoAppVersaoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformProdutoAppVersaoModule {}
