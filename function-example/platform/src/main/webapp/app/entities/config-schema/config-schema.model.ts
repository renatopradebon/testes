import { ConfigSchemaAppRevision } from '../config-schema-app-revision';
import { ConfigSchemaDbRevision } from '../config-schema-db-revision';
import { ServicoApp } from '../servico-app';
import { Produto } from '../produto';
import { Filial } from '../filial';
import { ServicoDb } from '../servico-db';
export class ConfigSchema {
    constructor(
        public id?: string,
        public schemaName?: string,
        public schemaTestEnabled?: boolean,
        public latestAppRevision?: ConfigSchemaAppRevision,
        public latestDbRevision?: ConfigSchemaDbRevision,
        public servicoAPP?: ServicoApp,
        public produto?: Produto,
        public filial?: Filial,
        public servicoDB?: ServicoDb,
        public appRevisions?: ConfigSchemaAppRevision,
        public dbRevisions?: ConfigSchemaDbRevision,
    ) {
        this.schemaTestEnabled = false;
    }
}
