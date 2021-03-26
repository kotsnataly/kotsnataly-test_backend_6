package ru.geekbrains;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import ru.geekbrains.java4.lesson6.db.dao.CategoriesMapper;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;
import ru.geekbrains.java4.lesson6.db.model.Categories;
import ru.geekbrains.java4.lesson6.db.model.CategoriesExample;
import ru.geekbrains.java4.lesson6.db.model.Products;
import ru.geekbrains.java4.lesson6.db.model.ProductsExample;
import ru.geekbrains.util.DbUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Less6ProductsTests {

    static Long createdProductId;
    static Categories categories;
    static CategoriesExample categoriesExample;
    static CategoriesMapper categoriesMapper = DbUtils.getCategoriesMapper();
    static Products products;
    static ProductsExample productsExample;
    static ProductsMapper productsMapper = DbUtils.getProductsMapper();

    @BeforeEach
    void createProductForDatabase() {
        Products products = new Products();
        //эта строка пойдет в setTitle.
        LocalTime lt = LocalTime.now();
        products.setCategory_id(2L);
        products.setTitle(lt.toString());
        //рандомная цена
        products.setPrice(((int) (Math.random() * 1000 + 1)));
        productsMapper.insert(products);
        productsExample = new ProductsExample();
        productsExample.createCriteria().andTitleEqualTo(lt.toString());
        //записываем ID для для удаления после тестов.
        createdProductId = productsMapper.selectByExample(productsExample).get(0).getId();
        System.out.println("Inserted to table product " +createdProductId);
    }


    @Test
        // проверка на наличие товаров с отрицательной ценой
    void checkNegativePriceNegativeTest() {
        List<Products> productsList = new ArrayList<>();
        productsExample = new ProductsExample();
        productsExample.createCriteria().andPriceLessThan(0);
        productsList = productsMapper.selectByExample(productsExample);
        assertThat(productsList.size() != 0);
    }

    @Test
        // проверка на то что нет записей с null id
    void checkForNullIdRecord() {
        List<Products> productsList = new ArrayList<>();
        productsExample = new ProductsExample();
        productsExample.createCriteria().andIdIsNull();
        productsList = productsMapper.selectByExample(productsExample);
        assertThat(productsList.size()).isEqualTo(0);
    }

    @Test
        // проверка на наличие записей с null в колонке category
    void negativeCheckIfCategoryOfProductIsNull() {
        List<Products> productsList = new ArrayList<>();
        productsExample = new ProductsExample();
        productsExample.createCriteria().andCategory_idIsNull();
        productsList = productsMapper.selectByExample(productsExample);
        assertThat(productsList.size()).isNotEqualTo(0);
    }

    @Test //тест обновления update товара в таблице.
    void update() {
        List<Products> productsList = new ArrayList<>();
        productsExample = new ProductsExample();
        //критерий отбора - ID  - createdProductId
        productsExample.createCriteria().andIdEqualTo(createdProductId);
        // выборка по тому id который мы создаем перед каждым тестом
        productsList = productsMapper.selectByExample(productsExample);
        //так как id уникальный в листе только одна позиция
        Products toUpdate = productsList.get(0);
        toUpdate.setPrice(2_000_000_000);
        toUpdate.setTitle("Price changed to unreal condition");
        //используем update
        productsMapper.updateByExample(toUpdate, productsExample);
        System.out.println("Updated table of id " + createdProductId);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        if (testInfo.getTags().contains("Skip")) {
            System.out.println("skipping");
            return;
        }
        if (createdProductId != null) {
            productsMapper.deleteByPrimaryKey(createdProductId);
            System.out.println("Deleted product " + createdProductId);
        }
    }
}
