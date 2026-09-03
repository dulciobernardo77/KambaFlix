package com.KambaFlix.mapper;

import com.KambaFlix.Controller.request.CategoryRequest;
import com.KambaFlix.Controller.response.CategoryResponse;
import com.KambaFlix.Entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public  static  Category toCategory(CategoryRequest categoryRequest){
        return Category
                .builder()
                .nome(categoryRequest.nome())
                .build();
    }

    public static CategoryResponse toCategoryResponce(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .nome(category.getNome())
                .build();
    }
}
