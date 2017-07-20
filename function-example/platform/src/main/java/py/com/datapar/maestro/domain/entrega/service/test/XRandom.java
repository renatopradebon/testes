package py.com.datapar.maestro.domain.entrega.service.test;

import py.com.datapar.maestro.domain.commons.model.vo.EntregaAlvoEstado;
import py.com.datapar.maestro.domain.commons.model.vo.EntregaEstado;
import py.com.datapar.maestro.domain.commons.model.vo.EstagioExecucaoEstado;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class XRandom<T> {

    private static Random random_ = new Random();

    private static List<String> autorizadoPor = Arrays.asList("Eliandro", "Rolando Meza", "Lucelio", "Alexandre Bissoloti", "Rafael Nervis", "Victor Arce");

    private static List<String> entregaCriadaPor = Arrays.asList("Julio Florentin", "Carlos Barrientos", "Cristiano Sepe", "Daniel Alencar",
            "Alexandre Sepe, ", "George Bonespirito", "Renato Pradebon");

    private static List<EntregaEstado> entregaEstados = Arrays.asList(EntregaEstado.CONCLUIDA,
            EntregaEstado.INICIADA,
            EntregaEstado.CANCELADA,
            EntregaEstado.EM_EXECUCAO,
            EntregaEstado.INTERROMPIDA);

    private static List<EstagioExecucaoEstado> estagioExecucaoEstados = Arrays.asList(EstagioExecucaoEstado.CONCLUIDA,
            EstagioExecucaoEstado.INICIADA,
            EstagioExecucaoEstado.CANCELADA,
            EstagioExecucaoEstado.EM_EXECUCAO,
            EstagioExecucaoEstado.INTERROMPIDA);

    private static List<EntregaAlvoEstado> entregaAlvoEstados = Arrays.asList(EntregaAlvoEstado.CONCLUIDA,
            EntregaAlvoEstado.EM_EXECUCAO);

    public static Random getRandom_() {
        return random_;
    }

    public static String getRandomAutorizadoPor() {
        return getRandomGeneric(autorizadoPor);
    }

    public static String getRandomEntregaCriadaPor() {
        return getRandomGeneric(entregaCriadaPor);
    }

    public static EntregaEstado getRandomEntregaEstado() {
        return getRandomGeneric(entregaEstados);
    }

    public static EntregaAlvoEstado getRandomEntregaAlvoEstado() {
        return getRandomGeneric(entregaAlvoEstados);
    }

    public static EstagioExecucaoEstado getRandomEstagioExecucaoEstado() {
        return getRandomGeneric(estagioExecucaoEstados);
    }

    public static <T> T getRandomGeneric(List<T> tClass) {
        return tClass.get(random_.nextInt(tClass.size()));
    }

}
