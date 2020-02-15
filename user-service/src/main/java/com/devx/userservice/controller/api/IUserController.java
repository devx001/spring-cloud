package com.devx.userservice.controller.api;

import com.devx.commonuser.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Validated
@Api(tags = "/user")
public interface IUserController {

    @ApiOperation(
            value = "Gets users",
            notes = "On call, to get users",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<List<User>> findAll();

    @ApiOperation(
            value = "Get user by id",
            notes = "On call, to get user",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<User> findById(
        @ApiParam(name = "id", value = "id user")
            Integer id
    ) throws InterruptedException;

    @ApiOperation(
            value = "Get user by id",
            notes = "On call, to get user",
            response = User.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
            ,
            @ApiResponse(code = 404, message = "Data not found")
            ,
            @ApiResponse(code = 502, message = "Remote MULE service not available")
    })
    ResponseEntity<User> createUser(
        @ApiParam(name = "user", value = "object user")
            User user
    );

}
