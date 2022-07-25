package com.teckmils.warehousemanagementsystem.domain.material.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddListOfMaterials(@NotEmpty Collection<AddMaterialStockItem> inventory) {
}
