import { ConfigSchema } from '../config-schema';
export class ServicoApp {
    constructor(
        public id?: string,
        public apptoken?: string,
        public hostPort?: number,
        public hostConsolePort?: number,
        public configSchema?: ConfigSchema,
    ) {
    }
}
