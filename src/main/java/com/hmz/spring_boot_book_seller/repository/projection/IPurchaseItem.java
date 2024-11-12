package com.hmz.spring_boot_book_seller.repository.projection;

public interface IPurchaseItem {
    String getTitle();
    Double getPrice();
    String getPurchaseTime();
}
