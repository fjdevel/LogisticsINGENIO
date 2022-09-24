package com.ingenio.logisticIngenio.dtos;

import com.ingenio.logisticIngenio.models.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CustomerResponseDTO {
    @ApiModelProperty(position = 1)
    private String name;

    @ApiModelProperty(position = 2)
    private String address;

    @ApiModelProperty(position = 3)
    private String num_doc;

}
