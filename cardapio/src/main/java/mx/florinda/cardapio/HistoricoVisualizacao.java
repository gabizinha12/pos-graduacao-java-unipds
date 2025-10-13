package mx.florinda.cardapio;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class HistoricoVisualizacao {

    private final Database database;

    private final Map<ItemCardapio, LocalDateTime> visualizacoes = new WeakHashMap<>();

    // ItemCardapio => Data e hora
    public HistoricoVisualizacao(Database database) {
        this.database = database;
    }

    public void registrarVisualizacao(Long itemId) {
        Optional<ItemCardapio> optionalItemCardapio = database.buscaItemCardapioPorId(itemId);
        if(optionalItemCardapio.isEmpty()) {
            System.out.println("Item não foi encontrado");
            return;
        }
        ItemCardapio itemCardapio = optionalItemCardapio.get();
        LocalDateTime now = LocalDateTime.now();
        visualizacoes.put(itemCardapio, now);
        System.out.printf("'%s' visualizado em '%s'\n", itemCardapio.nome(), now);
    }

    public void mostrarTotalVisualizados() {
        System.out.println("\nTotal itens visualizados:  " + visualizacoes.size());
    }

    public void listarVisualizacoes() {
        if(visualizacoes.isEmpty()) {
            System.out.println("Nenhum item foi visualizado ainda.");
            return;
        }
        System.out.println("\nHistórico de visualizações");
        visualizacoes.forEach((item, hora) ->
                System.out.printf(" - '%s' (id %d) em '%s'\n", item.nome(), item.id(), hora));
        System.out.println();
    }
}

