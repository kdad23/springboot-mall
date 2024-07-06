package com.jason.springboot_mall.dao.impl;

import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.dao.ProductDao;
import com.jason.springboot_mall.dto.ProductQueryParams;
import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.rowmapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDaoImpl implements ProductDao
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        String sql="SELECT  count(*) " +
                "FROM product WHERE 1=1 ";
        Map<String, Object>map=new HashMap<>();

        // 查詢條件
        sql=addFilteringSql(sql, map, productQueryParams);
//        if(productQueryParams.getCategory() != null)
//        {
//            sql =sql + " AND category = :category";
//            map.put("category", productQueryParams.getCategory().name());
//        }
//        if(productQueryParams.getSearch() != null)
//        {
//            sql =sql + " AND product_name LIKE :search";
//            // 模糊查詢一定要寫在map 值裡面
//            map.put("search", "%" + productQueryParams.getSearch() + "%");
//        }



        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map,
                Integer.class);

        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql="SELECT  product_id, product_name, category, image_url, price, " +
                "stock, " +
                "description, created_date, last_modified_date " +
                "FROM product WHERE 1=1 ";

        Map<String, Object>map=new HashMap<>();

        // 查詢條件
        sql=addFilteringSql(sel, map, productQueryParams);

//        if(productQueryParams.getCategory() != null)
//        {
//            sql =sql + " AND category = :category ";
//            map.put("category", productQueryParams.getCategory().name());
//        }
//        if(productQueryParams.getSearch() != null)
//        {
//            sql =sql + " AND product_name LIKE :search ";
//            // 模糊查詢一定要寫在map 值裡面
//            map.put("search", " % " + productQueryParams.getSearch() + " % ");
//        }

        // 排序用
        // 因為 OrderBy 和 Sort 有預設值所以不需要判斷是否會為null
        sql= sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();

        // 分頁用
        // 因為 limit 和 offset 有預設值所以不需要判斷是否會為null
        sql= sql + " LIMIT :limit OFFSET :offset " ;
        map.put("limit", productQueryParams.getLimit() );
        map.put("offset", productQueryParams.getOffset() );

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql="SELECT  product_id, product_name, category, image_url, price, " +
                "stock, " +
                "description, created_date, last_modified_date " +
                "FROM product WHERE product_id=:productId";

        Map<String, Object>map=new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductMapper());

        if(!productList.isEmpty())
        {
            return productList.get(0);
        }
        else
        {
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql="INSERT INTO  product( product_name, category, image_url, price, " +
                "stock, " +
                "description, created_date, last_modified_date )" +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description,"+
                ":createDate, :lastModifiedDate)";

        Map<String, Object>map=new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now=new Date();
        map.put("createDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int productId=keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        String sql="UPDATE product SET  " +
                "product_name= :productName, " +
                "category= :category, " +
                "image_url= :imageUrl," +
                " price= :price, " +
                "stock= :stock," +
                " description= :description, " +
                "last_modified_date= :lastModifiedDate " +
                "WHERE product_id= :productId ";

        Map<String, Object>map=new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now=new Date();

        map.put("lastModifiedDate", now);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql="DELETE FROM product " +
                "WHERE product_id= :productId ";
        Map<String, Object>map=new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams
    {
        // 查詢條件
        if(productQueryParams.getCategory() != null)
        {
            sql =sql + " AND category = :category ";
            map.put("category", productQueryParams.getCategory().name());
        }
        if(productQueryParams.getSearch() != null)
        {
            sql =sql + " AND product_name LIKE :search ";
            // 模糊查詢一定要寫在map 值裡面
            map.put("search", " % " + productQueryParams.getSearch() + " % ");
        }
        return sql;
    }




}
