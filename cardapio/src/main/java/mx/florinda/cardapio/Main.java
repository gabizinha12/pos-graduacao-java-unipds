package mx.florinda.cardapio;

import com.google.gson.Gson;

import javax.xml.stream.events.EntityReference;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();

        itens.forEach(System.out::println);

        System.out.println("-------------------------------------");

        Optional<ItemCardapio> optionalItem = database.buscaItemCardapioPorId(1L);
        String mensagem =  optionalItem.map(ItemCardapio::toString).orElse("Não encontrado");
        if (optionalItem.isPresent()) {
            System.out.println(optionalItem.get());
        } else {
            System.out.println("Item não encontrado");
    }
        System.out.println(mensagem);

        System.out.println("===========================");

        // PRECISA MANTER AS CATEGORIAS QUE ESTAO EM PROMOCAO

        Set<ItemCardapio.CategoriaCardapio> categoriasPromocao  = new TreeSet<>();
        categoriasPromocao.add(SOBREMESA);
        categoriasPromocao.add(ENTRADAS); // ordem natural
        categoriasPromocao.forEach(System.out::println);
        System.out.println("===========================");
        Set<ItemCardapio.CategoriaCardapio> categoriasCardapio = Set.of(SOBREMESA, ENTRADAS);
        categoriasCardapio.forEach(System.out::println); // unmodifiable set não garante a ordem, não se pode adicionar mais itens

        Set<ItemCardapio.CategoriaCardapio>  categoriasCardapio2 = EnumSet.of(SOBREMESA, ENTRADAS);
        categoriasCardapio2.add(PRATOS_PRINCIPAIS);
        categoriasCardapio2.forEach(System.out::println);

        // preciso da descriçao associada as categorias em promocao
        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESA, "O melhor doce da sua vida");
        promocoes.put(ENTRADAS, "Comece sua refeição com um toque de sabor");
        System.out.println(promocoes);

        // PRECISO DE UM HISTORICO DE VISUALIZAÇAO DO CARDAPIO
        HistoricoVisualizacao historicoVisualizacao = new HistoricoVisualizacao(database);
        historicoVisualizacao.registrarVisualizacao(2L);
        historicoVisualizacao.registrarVisualizacao(4L);
        historicoVisualizacao.registrarVisualizacao(6L);
        historicoVisualizacao.listarVisualizacoes();
        historicoVisualizacao.mostrarTotalVisualizados();
        // precisa remover um item do cardapio
        Long idParaRemover = 1L;
        boolean removido =  database.removerItemCardapio(idParaRemover);

        if(removido) {
            System.out.println("Item removido:  " +  idParaRemover);
        } else {
            System.out.println("Item não encontrado:   " + idParaRemover);
        }

        database.listaItensCardapio().forEach(System.out::println);


        System.out.println("Solicitando GC....");
        System.gc();
        Thread.sleep(500);

        historicoVisualizacao.mostrarTotalVisualizados();
        historicoVisualizacao.listarVisualizacoes();










        // dona florinda quer saber quantos itens ela tem de cada categoria no cardapio
        // categoria ==> quantidade

//        itens.forEach((itemCardapio, id) -> itemCardapio.id());

        Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new LinkedHashMap<>();


//           ItemCardapio.CategoriaCardapio categoriaCardapio =  item.categoria();
//           if(!itensPorCategoria.containsKey(categoriaCardapio)) {
//               itensPorCategoria.put(categoriaCardapio, 1);
//               itens.forEach(itemCardapio -> System.out.println(itemCardapio.id()));
//           } else {
//               Integer quantidadeAnterior = itensPorCategoria.get(categoriaCardapio);
//               itensPorCategoria.put(categoriaCardapio, quantidadeAnterior + 1);
//
//           }
//        }
//        System.out.println(itensPorCategoria);


//        Comparator<ItemCardapio.CategoriaCardapio> comparadorPorNome = Comparator.comparing(ItemCardapio.CategoriaCardapio::name);
//        Set<ItemCardapio.CategoriaCardapio> categorias = new TreeSet<>(); // arvore rubro negra
//       for (ItemCardapio itemCardapio : itens) {
//            ItemCardapio.CategoriaCardapio categoria = itemCardapio.categoria();
//            categorias.add(categoria);
//        }
//        for (ItemCardapio.CategoriaCardapio categoria : categorias) {
//            System.out.println(categoria);
//       }
//        System.out.println("----------------------------------------");
//
//        itens.stream()
//                .map(ItemCardapio::categoria)
//                .collect(Collectors.toCollection(TreeSet::new))
//                .forEach(System.out::println);
        //jeito funcional de fazer

//        itens.stream().collect(Collectors.groupingBy(ItemCardapio::categoria,
//                 Collectors.counting()
//                )).forEach((chave, valor) -> System.out.println(chave + "=> " + valor));
//    }


    }
}