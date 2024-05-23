package org.example.store.processor;

import org.example.store.model.Product;
import org.example.store.service.ProductService;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class StoreIOProcessor implements IOProcessor {
    private static final String EXCEPTION_USER_MESSAGE = "Problem with operation, check input information and try again";
    private final ProductService productService;
    private final Scanner scanner;

    public StoreIOProcessor(ProductService productService, InputStream inputStream) {
        this.productService = productService;
        scanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        boolean exitFlag = false;
        while (!exitFlag) {
            printMenu();
            String option = scanner.nextLine();
            try {
                switch(Integer.parseInt(option)) {
                    case 1 -> processAdd();
                    case 2 -> processUpdate();
                    case 3 -> processFind();
                    case 4 -> processFindAll();
                    case 5 -> processDelete();
                    case 6 -> exitFlag = true;
                    default -> throw new NumberFormatException();
                }
                Thread.sleep(500);
            } catch (NumberFormatException e) {
                System.out.println("Only numbers, please, from 1 to 6");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printMenu() {
        System.out.println("Murka Ceramica store menu");
        System.out.println("--------------------------");
        System.out.println("Write number of the option to do:");
        System.out.println("1. Add new product");
        System.out.println("2. Update product");
        System.out.println("3. Find product by name");
        System.out.println("4. Print all stored products");
        System.out.println("5. Delete product");
        System.out.println("6. Exit");
    }

    private void processAdd() {

        try {
            System.out.println("Input name of the product:");
            String name = scanner.nextLine();
            System.out.println("Input price and quantity");
            BigDecimal price = scanner.nextBigDecimal();
            int quantity = scanner.nextInt();

            Product product = new Product(null, name, price, quantity);
            productService.addNewProduct(product);
            System.out.printf("Product added with id = %d\n", product.getId());
        } catch(Exception e) {
            System.out.println(EXCEPTION_USER_MESSAGE);
        } finally {
            scanner.nextLine();
        }

    }

    private void processUpdate() {
        try {
            System.out.println("Choose product for updating and input id");
            prettyProductsOutput();
            Long id = scanner.nextLong();
            Product product = productService.findProductById(id).orElseThrow(NoSuchElementException::new);

            System.out.println("Input new price and quantity");
            product.setPrice(scanner.nextBigDecimal());
            product.setQuantity(scanner.nextInt());
            productService.updateProduct(product);
            System.out.println("Successfully updated");
        } catch (Exception e) {
            System.out.println(EXCEPTION_USER_MESSAGE);
        } finally {
            scanner.nextLine();
        }
    }

    private void processFind() {
        try {
            System.out.println("Input name of the product");
            Optional<Product> optProduct = productService.findProductByName(scanner.nextLine());
            optProduct.ifPresentOrElse(System.out::println,
                    () -> System.out.println("No such product in the store"));
        } catch (Exception e) {
            System.out.println(EXCEPTION_USER_MESSAGE);
        }
    }

    private void processFindAll() {
        prettyProductsOutput();
    }

    private void processDelete() {
        try {
            System.out.println("Choose product for deleting and input id");
            prettyProductsOutput();
            if (productService.deleteProductById(scanner.nextLong())) {
                System.out.println("Successful delete");
            } else {
                System.out.println("No such product");
            }
        } catch (Exception e) {
            System.out.println(EXCEPTION_USER_MESSAGE);
        } finally {
            scanner.nextLine();
        }

    }

    private void prettyProductsOutput() {
        productService.findAllProducts().stream()
                .sorted(Comparator.comparingLong(Product::getId))
                .forEach(System.out::println);
    }
}
