package com.ecommerce.finalproject.product;

import java.util.Scanner;

public class ProductEx {
    Scanner sc = new Scanner(System.in);
    ProductManager productManager = new ProductManager();

    public void showMenu() {
        System.out.println("================================================================");
        System.out.println("| 1. 상품 등록 | 2. 상품 수정 | 3. 상품 삭제 | 4. 상품 조회 | 0. 종료 |");
        System.out.println("================================================================");
    }

    public void selectMenu() {
        String menuIdx = sc.nextLine();

        switch(menuIdx) {
            case "1":
                addProduct();
                break;
            case "2":
                updateProduct();
                break;
            case "3":
                deleteProduct();
                break;
            case "4":
                showProducts();
                break;
            case "0":
                System.out.println("프로그램을 종료합니다.");
                break;
            default:
                System.out.println("해당하는 메뉴가 없습니다.");
        }
    }

    public void showProducts() {
        System.out.println("------------");
        System.out.println("상품 조회");
        System.out.println("------------");

        System.out.print("조회할 상품 ID: ");
        String productId = sc.nextLine();

        ProductData product = productManager.getProductById(productId);
        if(product == null) {
            System.out.println("해당 상품이 존재하지 않습니다.");
            return;
        }

        System.out.println("상품명: " + product.getProductName());
        System.out.println("상세 설명: " + product.getProductDescription());
        System.out.println("판매 시작일: " + product.getProductStartDate());
        System.out.println("판매 종료일: " + product.getProductEndDate());
        System.out.println("가격: " + product.getProductPrice());
        System.out.println("재고: " + product.getProductStock());
    }

    public void addProduct() {
        System.out.println("--------");
        System.out.println("상품 등록");
        System.out.println("--------");

        System.out.print("상품명: ");
        String name = sc.nextLine();

        System.out.println("상세 설명: ");
        String description = sc.nextLine();

        System.out.println("판매 시작일: ");
        String startDate = sc.nextLine();
        System.out.println("판매 종료일: ");
        String endDate = sc.nextLine();

        System.out.print("가격: ");
        int price = Integer.parseInt(sc.nextLine());

        System.out.print("재고: ");
        int stock = Integer.parseInt(sc.nextLine());

        ProductData product = new ProductData();
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductStartDate(startDate);
        product.setProductEndDate(endDate);
        product.setProductPrice(price);
        product.setProductStock(stock);

        productManager.addProduct(product);
    }

    public void updateProduct() {
        System.out.println("------------");
        System.out.println("상품 정보 수정");
        System.out.println("------------");

        System.out.print("수정할 상품 ID: ");
        String productId = sc.nextLine();

        ProductData product = productManager.getProductById(productId);
        if(product == null) {
            System.out.println("해당 상품이 존재하지 않습니다.");
            return;
        }

        while(true) {
            System.out.println("###############################");
            System.out.println("[현재 상품 정보]");
            System.out.println("상품명: " + product.getProductName());
            System.out.println("상세 설명: " + product.getProductDescription());
            System.out.println("판매 시작일: " + product.getProductStartDate());
            System.out.println("판매 종료일: " + product.getProductEndDate());
            System.out.println("가격: " + product.getProductPrice());
            System.out.println("재고: " + product.getProductStock());
            System.out.println("###############################");

            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("1. 상품명 | 2. 상세 설명 | 3. 판매 시작일 | 4. 판매 종료일 | 5. 가격 | 6. 재고 | 0. 종료");

            String choice = sc.nextLine();
            if(choice.equals("0")) {
                break;
            }

            switch(choice) {
                case "1":
                    System.out.print("새로운 상품명: ");
                    product.setProductName(sc.nextLine());
                    break;
                case "2":
                    System.out.print("새로운 상세 설명: ");
                    product.setProductDescription(sc.nextLine());
                    break;
                case "3":
                    System.out.print("새로운 판매 시작일: ");
                    product.setProductStartDate(sc.nextLine());
                    break;
                case "4":
                    System.out.print("새로운 판매 종료일: ");
                    product.setProductEndDate(sc.nextLine());
                    break;
                case "5":
                    System.out.print("새로운 가격: ");
                    product.setProductPrice(Integer.parseInt(sc.nextLine()));
                    break;
                case "6":
                    System.out.print("새로운 재고: ");
                    product.setProductStock(Integer.parseInt(sc.nextLine()));
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void deleteProduct() {
        System.out.println("------------");
        System.out.println("상품 삭제");
        System.out.println("------------");

        System.out.print("삭제할 상품 ID: ");
        String productId = sc.nextLine();

        if(productManager.deleteProduct(productId)) {
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("해당 상품이 존재하지 않습니다.");
        }
    }
}
