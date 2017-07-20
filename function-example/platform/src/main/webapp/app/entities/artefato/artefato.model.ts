import { ProdutoAppVersao } from '../produto-app-versao';
export class Artefato {
    constructor(
        public id?: string,
        public nome?: string,
        public extensao?: string,
        public fileUri?: string,
        public versao?: string,
        public sha1?: string,
        public versoes?: ProdutoAppVersao,
    ) {
    }
}
