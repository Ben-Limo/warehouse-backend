package com.teckmils.warehousemanagementsystem.domain.store.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddListOfStores (@NotEmpty Collection<AddStoreItem> stores) {
}
