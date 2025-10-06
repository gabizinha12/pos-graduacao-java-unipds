package mx.florinda.cardapio;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
            List<ItemCardapio> itens = database.listaItensCardapio();
  // dona florinda quer saber quantos itens ela tem de cada categoria no cardapio
        // categoria ==> quantidade


        Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new HashMap<>();
        for (ItemCardapio item : itens) {
            ItemCardapio.CategoriaCardapio categoriaCardapio =  item.categoria();
            if(itensPorCategoria.containsKey(categoriaCardapio)) {
                
            }
        }

//        Comparator<ItemCardapio.CategoriaCardapio> comparadorPorNome = Comparator.comparing(ItemCardapio.CategoriaCardapio::name);
//        Set<ItemCardapio.CategoriaCardapio> categorias = new TreeSet<>(comparadorPorNome); // arvore rubro negra
//        for (ItemCardapio itemCardapio : itens) {
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
//                .collect(Collectors.toCollection(() -> new TreeSet<>((comparadorPorNome))))
//                .forEach(System.out::println);
// jeito funcional de fazer
    }

}
