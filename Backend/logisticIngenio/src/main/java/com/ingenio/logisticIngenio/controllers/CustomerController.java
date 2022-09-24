package com.ingenio.logisticIngenio.controllers;

import com.ingenio.logisticIngenio.dtos.CustomerDataDTO;
import com.ingenio.logisticIngenio.dtos.CustomerResponseDTO;
import com.ingenio.logisticIngenio.models.AppUser;
import com.ingenio.logisticIngenio.models.AppUserRole;
import com.ingenio.logisticIngenio.models.Customer;
import com.ingenio.logisticIngenio.services.CustomerService;
import com.ingenio.logisticIngenio.services.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Api(tags = "customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ModelMapper modelMapper;
    private final CustomerService customerService;

    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "${CustomerController.register}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "No esta autorizado"),
            @ApiResponse(code = 400, message = "Algo esta pasando con el servidor"),
            @ApiResponse(code = 422, message = "Algunos datos podrian tener un formato incorrecto")})
    public Customer register(@ApiParam("New Customer") @RequestBody CustomerDataDTO customer){
        Customer newCustomer = modelMapper.map(customer,Customer.class);
        AppUser client = modelMapper.map(customer,AppUser.class);
			client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));
			newCustomer.setUser(userService.saveCustomer(client));
        return customerService.createCustomer(newCustomer);
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${CustomerController.profile}", response = CustomerResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "No esta autorizado"),
            @ApiResponse(code = 400, message = "Algo esta pasando con el servidor"),
            @ApiResponse(code = 422, message = "Algunos datos podrian tener un formato incorrecto"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public CustomerResponseDTO perfilCliente(HttpServletRequest req){
        AppUser user = userService.whoami(req);
        return modelMapper.map(customerService.findByUser(user), CustomerResponseDTO.class);

    }

    @GetMapping(value = "/{documento}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${CustomerController.search}", response = CustomerResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "No esta autorizado"),
            @ApiResponse(code = 400, message = "Algo esta pasando con el servidor"),
            @ApiResponse(code = 422, message = "Algunos datos podrian tener un formato incorrecto"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public CustomerResponseDTO search(@ApiParam("Document") @PathVariable String document) {
        return modelMapper.map(customerService.findCustomerDoc(document),CustomerResponseDTO.class);
    }

    @GetMapping(value="/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${CustomerController.list}", response = ArrayList.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "No esta autorizado"),
            @ApiResponse(code = 400, message = "Algo esta pasando con el servidor"),
            @ApiResponse(code = 422, message = "Algunos datos podrian tener un formato incorrecto"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<Customer> listarClientes(){
        return customerService.findAll();
    }


}
