package com.pycogroup.pitsa.domain;

import com.pycogroup.pitsa.model.Product;
import com.pycogroup.pitsa.model.SizePrice;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ProductTest {
    private static final int PRO_ID = 1;
    private static final int CAT_ID = 4;
    private static final String NAME = "Pizza4p Seafood";
    private static final String IMG_URL = "Url Picture";
    private static final SizePrice SIZE_PRICE_1 = new SizePrice(20000,40000,60000);
    private static final String DESCRIPTION = "..of best quality";

    @Test
    public void testConstructorAndGetters() throws Exception {
        Product product = new Product(PRO_ID,CAT_ID,NAME,SIZE_PRICE_1,IMG_URL,DESCRIPTION);

        assertNull(product.get_id());
        assertThat(product.getId(), is(PRO_ID));
        assertThat(product.getCatId(), is(CAT_ID));
        assertThat(product.getName(), is(NAME));
        assertThat(product.getImgUrl(), is(IMG_URL));
        assertThat(product.getPrice(), is(SIZE_PRICE_1));
        assertThat(product.getDescription(), is(DESCRIPTION));
    }

    @Test
    public void equalsHashcodeVerify() {
        Product product1 = new Product(PRO_ID,CAT_ID,NAME,SIZE_PRICE_1,IMG_URL,DESCRIPTION);
        Product product2 = new Product(PRO_ID,CAT_ID,NAME,SIZE_PRICE_1,IMG_URL,DESCRIPTION);

        assertThat(product1, is(product2));
        assertThat(product1.hashCode(), is(product2.hashCode()));
    }
}
