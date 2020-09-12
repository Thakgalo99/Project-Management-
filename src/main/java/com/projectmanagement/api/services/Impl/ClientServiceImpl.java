package com.projectmanagement.api.services.Impl;

import com.google.common.base.Strings;
import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.exceptions.CommonServiceException;
import com.projectmanagement.api.repositories.ClientRepository;
import com.projectmanagement.api.services.common.ICommonService;
import com.projectmanagement.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClientServiceImpl implements ICommonService<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Utils utils;

    //region Find Client
    @Override
    public List<Client> findAll(int page, int limit) {
        if(page>0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Client> clientPage=clientRepository.findAll(pageable);

        List<Client> clientList=clientPage.getContent();
        return clientList;
    }

    @Override
    public Client findById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public Client findByUid(String uid) {
        Client client = clientRepository.findByUid(uid);
        return client;
    }
    //endregion

    //region Save Client
    @Override
    public Client save(Client entity) {

        Client getClient =clientRepository.findByEmail(entity.getEmail());
        if(getClient != null){
            throw new CommonServiceException("Client already existe !");
        }
        entity.setUid(utils.generateStringId(32));
        entity.setDateCreation(new Date());
        Client client=clientRepository.save(entity);
        return client;
    }
    //endregion

    //region Update Client
    @Override
    public Client update(Client entity) {
        Client client=findByUid(entity.getUid());
        if(client == null){
            throw new CommonServiceException("Client uid not existe !");
        }

        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setEmail(entity.getEmail());
        client.setPhone(entity.getPhone());
        client.setCompanyName(entity.getCompanyName());

        Client newClient=clientRepository.save(client);
        return newClient;
    }

    @Override
    public Client update(Client entity, String uid) {
        Client client=findByUid(uid);
        if(client == null){
            throw new CommonServiceException("Client uid not existe !");
        }

        client.setFirstName(entity.getFirstName());
        client.setLastName(entity.getLastName());
        client.setEmail(entity.getEmail());
        client.setPhone(entity.getPhone());
        client.setCompanyName(entity.getCompanyName());

        Client newClient=clientRepository.save(client);
        return newClient;
    }
    //endregion

    //region Delete Client
    @Override
    public void delete(Client entity) {
        Integer clientListSize=clientRepository.findAll().size();

        if(clientListSize <1) {
            throw new CommonServiceException("Client list already empty !");
        }
        clientRepository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Client client=findByUid(uid);

        if(client == null){
            throw new CommonServiceException("Client uid not existe !");
        }
        clientRepository.delete(client);
    }

    @Override
    public void deleteAll() {
//        Integer clientListSize=clientRepository.findAll().size();
//
//        if(clientListSize <1) {
//            throw new CommonServiceException("Client list already empty !");
//        }
        clientRepository.deleteAll();
    }
    //endregion

    //region Count Client
    @Override
    public long count() {
        return clientRepository.count();
    }


    @Override
    public List<Client> findAllByCriteria(int page, int limit, String search) {
        if(page>0) page -=1;

        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Client> clientPage;
        String getSearchKeyWord=search;
        if(getSearchKeyWord==null){
            clientPage=clientRepository.findAll(pageable);
        }
        else if(getSearchKeyWord.isEmpty()){
            clientPage=clientRepository.findAll(pageable);
        }else{
            clientPage=clientRepository.findAllClientByCriteria(pageable,search);
        }

        List<Client> clientList=clientPage.getContent();
        return clientList;
    }
    //endregion
}
