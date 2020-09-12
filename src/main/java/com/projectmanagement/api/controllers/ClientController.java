package com.projectmanagement.api.controllers;

import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.exceptions.ResourceNotFoundException;
import com.projectmanagement.api.responces.APIStatus;
import com.projectmanagement.api.services.common.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(APIName.CLIENT)
@RestController
public class ClientController {

    @Autowired
    ICommonService<Client> iCommonService;

    //region Get Client
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Client>> getAllClients (
            @RequestParam(value = "page", defaultValue = "1",required = false) int page,
            @RequestParam(value = "limit", defaultValue = "15" ,required = false) int limit,@RequestParam(value = "search",required = false) String search) throws Exception{

        List<Client> clientList=iCommonService.findAllByCriteria(page, limit,search);

        if (clientList.isEmpty()){
            throw  new ResourceNotFoundException(APIStatus.ERR_NO_CONTENT.getDescription());
        }

        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }




    @GetMapping(APIName.CLIENT_BY_CLIENT_UID)
    public ResponseEntity<Client> getClientByUid(@PathVariable("clientuid") String clientuid) throws Exception{


        Client client = iCommonService.findByUid(clientuid);

        if (client !=null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException(APIStatus.ERR_SESSION_NOT_FOUND.getDescription());
        }
    }
    //endregion

    //region Save Client
    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client)throws Exception{
        if(client.getFirstName().isEmpty() || client.getLastName().isEmpty()){
            throw new CommonServiceException("First name or Last name can't be null");
        }
        Client newClienty=iCommonService.save(client);
        return new ResponseEntity<>(newClienty,HttpStatus.CREATED);
    }
    //endregion

    //region Update Client
    @PutMapping(
            path =APIName.CLIENT_BY_CLIENT_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable("clientuid") String clientuid)throws Exception{
        if(client.getFirstName().isEmpty() || client.getLastName().isEmpty()){
            throw new CommonServiceException("First name or Last name can't be null");
        }
        if(clientuid.isEmpty()){
            throw new CommonServiceException("Client uid can't be empty");
        }

        Client newClient=iCommonService.update(client,clientuid);
        return new ResponseEntity<>(newClient,HttpStatus.CREATED);
    }
    //endregion

    //region Delete Client By Uid
    @DeleteMapping(
            path =APIName.CLIENT_BY_CLIENT_UID,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Client> deleteClient(@PathVariable("clientuid") String clientuid)throws Exception{

        if(clientuid.isEmpty()){
            throw new CommonServiceException("Client uid can't be empty");
        }

        iCommonService.deleteByUid(clientuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region Delete Client All
    @DeleteMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Client> deleteAllClients()throws Exception{

        iCommonService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //endregion
}
