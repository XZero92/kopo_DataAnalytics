package com.ecommerce.finalproject.display;

import com.ecommerce.finalproject.product.ProductData;
import com.ecommerce.finalproject.util.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DisplayProducts {
    private static final String PRODUCT_DB_PATH = "src/com/ecommerce/finalproject/data/productdb.json";

    public void run() {
        while (true) {
            showMenu();
            if (!selectMenu()) {
                break;
            }
        }
    }

    public void showMenu() {
        System.out.println("============================================");
        System.out.println("| 1. 상품 검색 | 2. 상품 목록 조회 | 0. 뒤로가기 |");
        System.out.println("============================================");
        System.out.print("메뉴를 선택하세요> ");
    }

    public boolean selectMenu() {
        Scanner scanner = new Scanner(System.in);
        String menuIdx = scanner.nextLine();

        switch (menuIdx) {
            case "1":
                searchProduct();
                break;
            case "2":
                showProductList();
                break;
            case "0":
                return false;
            default:
                System.out.println("해당하는 메뉴가 없습니다.");
        }
        return true;
    }

    private List<ProductData> loadProductsFromFile() {
        Type productListType = new TypeToken<ArrayList<ProductData>>() {}.getType();
        List<ProductData> products = JsonUtils.readListFromFile(PRODUCT_DB_PATH, productListType);
        return products != null ? products : new ArrayList<>();
    }

    public void showProductList() {
        List<ProductData> products = loadProductsFromFile();

        if (products.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 가격 오름차순 | 2. 가격 내림차순");
        System.out.print("정렬 방식을 선택하세요> ");
        String sortOption = scanner.nextLine();

        if ("1".equals(sortOption)) {
            products = products.stream()
                    .sorted(Comparator.comparingInt(ProductData::getProductPrice))
                    .collect(Collectors.toList());
        } else if ("2".equals(sortOption)) {
            products = products.stream()
                    .sorted(Comparator.comparingInt(ProductData::getProductPrice).reversed())
                    .collect(Collectors.toList());
        }
        System.out.println("=========================================");
        System.out.println("상품명\t|\t가격\t|\t재고");
        System.out.println("=========================================");
        for (ProductData product : products) {
            System.out.printf("%-10s\t|\t%-10d\t|\t%-10d\t\n",
                    product.getProductName(), product.getProductPrice(), product.getDeliveryFee());
        }
        System.out.println("=========================================");
    }

    public void searchProduct() {
        List<ProductData> products = loadProductsFromFile();

        Scanner scanner = new Scanner(System.in);
        System.out.print("검색할 키워드를 입력하세요> ");
        String keyword = scanner.nextLine();

        List<ProductData> filteredProducts = products.stream()
                .filter(product -> product.getProductName().contains(keyword) ||
                        product.getProductDescription().contains(keyword))
                .collect(Collectors.toList());

        if (filteredProducts.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return;
        }

        for (ProductData product : filteredProducts) {
            System.out.println("###############################");
            System.out.println("[상품 상세 정보]");
            System.out.println("상품명: " + product.getProductName());
            System.out.println("상세 설명: " + product.getProductDescription());
            System.out.println("판매 시작일: " + product.getProductStartDate());
            System.out.println("판매 종료일: " + product.getProductEndDate());
            System.out.println("가격: " + product.getProductPrice());
            System.out.println("재고: " + product.getProductStock());
            System.out.println("배송비: " + product.getDeliveryFee());
            System.out.println("###############################");
        }
    }
}