import { Plano } from '../plano';
import { EstagioExecucaoEntrega } from '../estagio-execucao-entrega';
import { Conteudo } from '../conteudo';
export class Entrega {
    constructor(
        public id?: string,
        public codinomeEntrega?: string,
        public dataCriacao?: any,
        public dataFinalizacao?: any,
        public usuarioCriacao?: string,
        public plano?: Plano,
        public estagios?: EstagioExecucaoEntrega,
        public conteudos?: Conteudo,
    ) {
    }
}
