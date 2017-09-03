package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.*;

/**
 * Created by bartek on 03.09.2017.
 */
public class SearchScreen {

    private AuthenticationManager authenticationManager;
    private ProductCatalog productCatalog;
    private Scanner scanner;

    public SearchScreen(Scanner scanner, AuthenticationManager authenticationManager, ProductCatalog productsCatalog) {
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
        this.productCatalog = productsCatalog;
    }

    public void show() {
        System.out.println("Podaj Kryteria wyszukiwania");
        System.out.print("Tagi:  ");
        Set<String> tags = getTags();
        System.out.print("Cena od: ");
        Money priceFrom = getMoney();
        System.out.print("Cena do: ");
        Money priceTo = getMoney();

        List<Product> productList = productCatalog.find(authenticationManager.getClient(), tags, priceFrom, priceTo);
        for (Product product : productList) {
            showProduct(product);
        }

    }

    private void showProduct(Product product) {
        String productType = product instanceof Picture ? "OBRAZEK" : "CLIP";
        String tags = "";
        if (product instanceof Picture)
            tags = ((Picture) product).getTags().toString();
        Money price = product.calculatePrice(authenticationManager.getClient());
        System.out.println(String.format("%d - %s - %s %s", product.getNumber(), productType, tags, price));
    }

    private Money getMoney() {
        String moneyString = scanner.nextLine();
        try {
            Integer moneyInteger = Integer.parseInt(moneyString);
            return Money.valueOf(scanner.nextInt());
        } catch (Exception ex) {
            return null;
        }
    }

    /*for (Iterator<String> i = tagsList.iterator(); i.hasNext(); )
            if (i.next().length() == 0)
                i.remove();*/
    //zastosowanie Iteratora do przypadku poniżej

    public Set<String> getTags() {
        String line = scanner.nextLine();
        String[] tagsArray = line.split(" ");
        List<String> tagsList = Arrays.asList(tagsArray);
        Set<String> tags = new HashSet<>(tagsList);
        tags.remove(" ");
        return tags;
    }
}
