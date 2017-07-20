import { ServicoApp } from '../servico-app';
export class MaestroAppConfig {
    constructor(
        public id?: string,
        public maestroplatURI?: string,
        public servicoMaestroApp?: ServicoApp,
    ) {
    }
}
