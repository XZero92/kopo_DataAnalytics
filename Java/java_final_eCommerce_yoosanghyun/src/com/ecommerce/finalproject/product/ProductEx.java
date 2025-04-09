package com.ecommerce.finalproject.product;

import java.util.Scanner;

public class ProductEx {

    Scanner sc = new Scanner(System.in);
    ProductManager productManager = new ProductManager();

    public void run() {
        while (true) {
            showMenu();
            if(!selectMenu())
                break;
        }
    }

    public void showMenu() {
        System.out.println("===================================================================");
        System.out.println("| 1. 상품 등록 | 2. 상품 수정 | 3. 상품 삭제 | 4. 상품 조회 | 0. 뒤로가기 |");
        System.out.println("===================================================================");
        System.out.print("메뉴를 선택하세요> ");
    }

    public boolean selectMenu() {
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
                findProducts();
                break;
            case "0":
                return false;
            default:
                System.out.println("해당하는 메뉴가 없습니다.");
        }
        return true;
    }

    public void findProducts() {
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
        System.out.println("###############################");
        System.out.println("[현재 상품 정보]");
        System.out.println("상품명: " + product.getProductName());
        System.out.println("상세 설명: " + product.getProductDescription());
        System.out.println("판매 시작일: " + product.getProductStartDate());
        System.out.println("판매 종료일: " + product.getProductEndDate());
        System.out.println("가격: " + product.getProductPrice());
        System.out.println("재고: " + product.getProductStock());
        System.out.println("배송비: " + product.getDeliveryFee());
        System.out.println("###############################");
    }

    public void addProduct() {
        System.out.println("--------");
        System.out.println("상품 등록");
        System.out.println("--------");

        System.out.print("상품명: ");
        String name = sc.nextLine();
        System.out.print("상세 설명: ");
        String description = sc.nextLine();
        System.out.print("판매 시작일: ");
        String startDate = sc.nextLine();
        System.out.print("판매 종료일: ");
        String endDate = sc.nextLine();
        System.out.print("가격: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.print("재고: ");
        int stock = Integer.parseInt(sc.nextLine());
        System.out.print("배송비: ");
        int deliveryFee = Integer.parseInt(sc.nextLine());

        ProductData product = new ProductData();
        product.setProductCode(productManager.generateProductCode());
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductStartDate(startDate);
        product.setProductEndDate(endDate);
        product.setProductPrice(price);
        product.setProductStock(stock);
        product.setDeliveryFee(deliveryFee);

        System.out.println("상품 코드는 " + product.getProductCode() + "입니다.");
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

        ProductData tmpData = new ProductData(product);

        while(true) {
            System.out.println("###############################");
            System.out.println("[현재 상품 정보]");
            System.out.println("상품명: " + tmpData.getProductName());
            System.out.println("상세 설명: " + tmpData.getProductDescription());
            System.out.println("판매 시작일: " + tmpData.getProductStartDate());
            System.out.println("판매 종료일: " + tmpData.getProductEndDate());
            System.out.println("가격: " + tmpData.getProductPrice());
            System.out.println("재고: " + tmpData.getProductStock());
            System.out.println("###############################");

            System.out.println("1. 상품명 | 2. 상세 설명 | 3. 판매 시작일 | 4. 판매 종료일 | 5. 가격 | 6. 재고 | 0. 종료 및 적용");
            System.out.print("수정할 항목을 선택하세요> ");

            String choice = sc.nextLine();
            if(choice.equals("0")) {
                break;
            }

            switch(choice) {
                case "1":
                    System.out.print("새로운 상품명: ");
                    tmpData.setProductName(sc.nextLine());
                    break;
                case "2":
                    System.out.print("새로운 상세 설명: ");
                    tmpData.setProductDescription(sc.nextLine());
                    break;
                case "3":
                    System.out.print("새로운 판매 시작일: ");
                    tmpData.setProductStartDate(sc.nextLine());
                    break;
                case "4":
                    System.out.print("새로운 판매 종료일: ");
                    tmpData.setProductEndDate(sc.nextLine());
                    break;
                case "5":
                    System.out.print("새로운 가격: ");
                    tmpData.setProductPrice(Integer.parseInt(sc.nextLine()));
                    break;
                case "6":
                    System.out.print("새로운 재고: ");
                    tmpData.setProductStock(Integer.parseInt(sc.nextLine()));
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
        if(productManager.updateProduct(productId, tmpData)) {
            System.out.println("상품 정보가 수정되었습니다.");
        } else {
            System.out.println("상품 정보 수정에 실패했습니다.");
        }
    }

    public void deleteProduct() {
        System.out.println("------------");
        System.out.println("상품 삭제");
        System.out.println("------------");

        System.out.print("삭제할 상품 ID: ");
        String productId = sc.nextLine();

        ProductData product = productManager.getProductById(productId);

        if(product == null) {
            System.out.println("해당 상품이 존재하지 않습니다.");
            return;
        } else {
            System.out.println("###############################");
            System.out.println("[현재 상품 정보]");
            System.out.println("상품명: " + product.getProductName());
            System.out.println("상세 설명: " + product.getProductDescription());
            System.out.println("판매 시작일: " + product.getProductStartDate());
            System.out.println("판매 종료일: " + product.getProductEndDate());
            System.out.println("가격: " + product.getProductPrice());
            System.out.println("재고: " + product.getProductStock());
            System.out.println("배송비: " + product.getDeliveryFee());
            System.out.println("###############################");
            System.out.print("정말로 삭제하시겠습니까? (y/n): ");
            String confirm = sc.nextLine();
            if(!confirm.equalsIgnoreCase("y")) {
                System.out.println("삭제가 취소되었습니다.");
                return;
            }
        }

        if(productManager.deleteProduct(productId)) {
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("해당 상품이 존재하지 않습니다.");
        }
    }
}
