package py.com.datapar.maestro.domain.produto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.datapar.maestro.domain.entrega.service.EntregaService;
import py.com.datapar.maestro.domain.produto.dto.ProdutoDTO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ProdutoDTOService {

    @Inject
    private ProdutoService produtoService;

    @Inject
    private EntregaService entregaService;

    private Logger logger = LoggerFactory.getLogger(ProdutoDTOService.class);

    public List<ProdutoDTO> getProdutosDTO() {

        return produtoService
                .getAll()
                .stream()
                .parallel()
                .map(produto -> {
                    ProdutoDTO produtoDTO = new ProdutoDTO();

                    produtoDTO.setProduto(produto);
                    produtoDTO.setUltimaEntrega(entregaService.getUltimaEntrega(produto).orElse(null));

//                    logger.info(getClass() + ":: => " + XMaestro.XJson.toJson(produto));

                    return produtoDTO;
                })
                .collect(Collectors.toList());
    }
}
