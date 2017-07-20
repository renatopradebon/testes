import { ConfigSchema } from '../config-schema';
export class ServicoDb {
    constructor(
        public id?: string,
        public userdbaLogin?: string,
        public userdbaPassword?: string,
        public connectionURL?: string,
        public configSchemas?: ConfigSchema,
    ) {
    }
}
