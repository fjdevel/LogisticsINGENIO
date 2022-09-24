package com.ingenio.logisticIngenio.dtos;

import com.ingenio.logisticIngenio.models.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class CustomerDataDTO {

    @ApiModelProperty(position = 0)
    private String name;

    @ApiModelProperty(position = 1)
    private String address;

    @ApiModelProperty(position = 2)
    private String num_doc;

    @ApiModelProperty(position = 3)
    private String username;
    @ApiModelProperty(position = 4)
    private String email;
    @ApiModelProperty(position = 5)
    private String password;

}
