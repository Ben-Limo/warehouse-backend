package com.teckmils.warehousemanagementsystem.domain.category.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddListOfCategories(@NotEmpty Collection<AddCategoryItem> categories) {
}
