package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    void create(int userId, int productId, int quantity);
    void update(int userId, int productId, int quantity);
    void clearCart(int userId);
}
