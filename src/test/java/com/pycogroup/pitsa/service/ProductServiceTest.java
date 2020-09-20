package com.pycogroup.pitsa.service;


import com.pycogroup.pitsa.model.Product;
import com.pycogroup.pitsa.model.SizePrice;
import com.pycogroup.pitsa.repository.ProductRepository;
import com.pycogroup.pitsa.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final int PRO_ID = 1;
    private static final int CAT_ID = 4;
    private static final String NAME = "Pizza4p Seafood";
    private static final String IMG_URL = "Url Picture";
    private static final SizePrice SIZE_PRICE = new SizePrice(20000,40000,60000);
    private static final String DESCRIPTION = "..of best quality";
    /**************************************************************************************
     *
     * Verify the invocation of dependencies
     * Capture parameter values.
     * Verify the parameters.
     *
     **************************************************************************************/

    @Test
    public void createNew() {
        //prepare to capture a TourRating Object
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        //invoke createNew
        SizePrice sizePrice = new SizePrice(20000,40000,60000);
        Product product = new Product(PRO_ID,CAT_ID,NAME,SIZE_PRICE,IMG_URL,DESCRIPTION);
        productService.addProduct(product);

        //verify tourRatingRepository.save invoked once and capture the TourRating Object
        verify(productRepositoryMock).save(productArgumentCaptor.capture());

        //verify the attributes of the Tour Rating Object
        assertThat(productArgumentCaptor.getValue().getId(), is(PRO_ID));
        assertThat(productArgumentCaptor.getValue().getCatId(), is(CAT_ID));
        assertThat(productArgumentCaptor.getValue().getName(), is(NAME));
        assertThat(productArgumentCaptor.getValue().getImgUrl(), is(IMG_URL));
        assertThat(productArgumentCaptor.getValue().getPrice(),is(SIZE_PRICE));
        assertThat(productArgumentCaptor.getValue().getDescription(),is(DESCRIPTION));
    }
}
