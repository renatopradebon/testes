import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { platformConfigSchemaAppRevisionModule } from './config-schema-app-revision/config-schema-app-revision.module';import { platformDeliveryOrderAlvoServicoModule } from './delivery-order-alvo-servico/delivery-order-alvo-servico.module';import { platformArtefatoModule } from './artefato/artefato.module';import { platformEstagioExecucaoEntregaModule } from './estagio-execucao-entrega/estagio-execucao-entrega.module';import { platformPlanoClassificadorModule } from './plano-classificador/plano-classificador.module';import { platformClienteModule } from './cliente/cliente.module';import { platformConfigSchemaDbRevisionModule } from './config-schema-db-revision/config-schema-db-revision.module';import { platformServicoDbModule } from './servico-db/servico-db.module';import { platformEmpresaModule } from './empresa/empresa.module';import { platformProdutoModule } from './produto/produto.module';import { platformEntregaModule } from './entrega/entrega.module';import { platformFilialModule } from './filial/filial.module';import { platformServidorModule } from './servidor/servidor.module';import { platformConteudoDbModule } from './conteudo-db/conteudo-db.module';import { platformProdutoDbRevisionModule } from './produto-db-revision/produto-db-revision.module';import { platformProdutoAppVersaoModule } from './produto-app-versao/produto-app-versao.module';import { platformServicoAppModule } from './servico-app/servico-app.module';import { platformConteudoAppModule } from './conteudo-app/conteudo-app.module';import { platformEntregaAlvoModule } from './entrega-alvo/entrega-alvo.module';import { platformConteudoCommandModule } from './conteudo-command/conteudo-command.module';import { platformPlanoModule } from './plano/plano.module';import { platformMaestroAppConfigModule } from './maestro-app-config/maestro-app-config.module';import { platformUsuarioModule } from './usuario/usuario.module';import { platformDeliveryOrderModule } from './delivery-order/delivery-order.module';import { platformConfigSchemaModule } from './config-schema/config-schema.module';
/* needle-add-entity-module-import - add entity modules imports here */

@NgModule({
    imports: [
	platformConfigSchemaAppRevisionModule,	platformDeliveryOrderAlvoServicoModule,	platformArtefatoModule,	platformEstagioExecucaoEntregaModule,	platformPlanoClassificadorModule,	platformClienteModule,	platformConfigSchemaDbRevisionModule,	platformServicoDbModule,	platformEmpresaModule,	platformProdutoModule,	platformEntregaModule,	platformFilialModule,	platformServidorModule,	platformConteudoDbModule,	platformProdutoDbRevisionModule,	platformProdutoAppVersaoModule,	platformServicoAppModule,	platformConteudoAppModule,	platformEntregaAlvoModule,	platformConteudoCommandModule,	platformPlanoModule,	platformMaestroAppConfigModule,	platformUsuarioModule,	platformDeliveryOrderModule,	platformConfigSchemaModule,
        /* needle-add-entity-module - add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class platformEntityModule {}
