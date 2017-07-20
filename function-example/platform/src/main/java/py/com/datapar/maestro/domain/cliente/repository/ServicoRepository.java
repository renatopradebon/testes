/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.datapar.maestro.domain.cliente.repository;

import py.com.datapar.maestro.domain.cliente.model.Servico;
import py.com.datapar.maestro.domain.generico.JpaRepository;

public class ServicoRepository extends JpaRepository<Servico> {

    public ServicoRepository() {
        super(Servico.class);
    }

}