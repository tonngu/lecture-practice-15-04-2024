package se.lexicon.group2;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Atest", 101, 0));
        productList.add(new Product("Btest", 149, 5));
        productList.add(new Product("Ctest", 149, 10));
        productList.add(new Product("Btest", 101, 5));
        productList.add(new Product("btest", 101, 1));

        Conditional conditionalForStockZero = (product) -> product.getStock() == 0;
        Action printProduct = (product) -> System.out.println(product.toString());
        process(productList, conditionalForStockZero, printProduct);

        System.out.println("----------------------------------");

        process(productList,
                (product) -> product.getProductName().startsWith("B"),
                product -> System.out.println(product.toString())
        );

        System.out.println("----------------------------------");

        process(productList,
                product -> product.getPrice() > 100 && product.getPrice() < 150,
                product -> System.out.println(product.toString())
        );

        System.out.println("----------------------------------");

        process(productList,
                product -> product.getStock() < 10 && product.getStock() > 0,
                product -> {product.setPrice(product.getPrice()*1.5);
                System.out.println(product.toString());}
                );


    }

    public static void process(List<Product> list, Conditional conditional, Action action) {
        for (Product product : list) {
            if (conditional.test(product)) {
                action.execute(product);
            }
        }
    }


}
