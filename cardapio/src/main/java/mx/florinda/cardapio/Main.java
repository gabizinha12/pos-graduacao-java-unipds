package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
        List<ItemCardapio>itens = database.listaItensCardapio();
        Optional<ItemCardapio> itensPorId = database.buscaItemCardapioPorId(1L);
        if(itensPorId.isPresent()) {
            System.out.println(itensPorId.get());

        }



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