package com.example.MerchantService.service.impl;

import com.example.MerchantService.dto.MerchantDTO;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.proxy.MerchantProxy;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.response.APIResponse;
import com.example.MerchantService.service.MerchantService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    MerchantProxy merchantProxy;

    @Override
    public ResponseEntity<APIResponse<String>> addMerchantDetails(Merchant merchant, String merchantPassword) {
        List<Merchant> merchantList = (List<Merchant>) merchantRepository.findAll();
        AtomicInteger flag = new AtomicInteger(0);
        AtomicReference<String> message = new AtomicReference<>("");

        merchantList.stream().forEach(merchants -> {
            if ((merchants.getMerchantEmail()).equals(merchant.getMerchantEmail())){
                message.set("Already Exists");
                flag.set(1);
            }
        });
        if (flag.get() == 0){
            Merchant merchantObj = merchantRepository.save(merchant);
            MerchantDTO merchantDTOCreated = new MerchantDTO();
            merchantDTOCreated.setMerchantId(merchantObj.getMerchantId());
            merchantDTOCreated.setMerchantName(merchantObj.getMerchantName());
            merchantDTOCreated.setMerchantAddress(merchantObj.getMerchantAddress());
            merchantDTOCreated.setMerchantEmail(merchantObj.getMerchantEmail());
            merchantDTOCreated.setMerchantImage(merchantObj.getMerchantImage());
            merchantDTOCreated.setMerchantRating(merchantObj.getMerchantRating());
            merchantDTOCreated.setMobileNo(merchantObj.getMobileNo());
            merchantDTOCreated.setMerchantPassword(merchantPassword);
            merchantProxy.loginMerchant(merchantDTOCreated);
            return new ResponseEntity<>(new APIResponse<>(1000,"SUCCESS",merchantProxy.loginMerchant(merchantDTOCreated).getBody().getMessage()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(400,message.get()), HttpStatus.OK);
    }

    @Override
    public Merchant getProfile(String id) {
        return merchantRepository.findById(id).get();
    }

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String parseToken(String token) {

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String id = (String) body.get("userId");
        return id;

    }

    @Override
    public MerchantDTO merchantResponse(Merchant merchant,String password) {
        MerchantDTO merchantDTOResponse = new MerchantDTO();
        merchantDTOResponse.setMerchantId(merchant.getMerchantId());
        merchantDTOResponse.setMerchantName(merchant.getMerchantName());
        merchantDTOResponse.setMerchantAddress(merchant.getMerchantAddress());
        merchantDTOResponse.setMerchantEmail(merchant.getMerchantEmail());
        merchantDTOResponse.setMerchantImage(merchant.getMerchantImage());
        merchantDTOResponse.setMerchantRating(merchant.getMerchantRating());
        merchantDTOResponse.setMobileNo(merchant.getMobileNo());
        merchantDTOResponse.setMerchantPassword(password);
        return merchantDTOResponse;
    }

    @Override
    public Merchant getMerchant(String merchantId) {
        return merchantRepository.findById(merchantId).get();
    }

    @Override
    public String saveMerchant(Merchant merchant) {
        List<Merchant> merchantList = (List<Merchant>) merchantRepository.findAll();
        AtomicReference<String> merchantId = new AtomicReference<>("");
        AtomicInteger flag = new AtomicInteger();
        merchantList.stream().forEach(merchants -> {
            if ((merchants.getMerchantEmail()).equals(merchant.getMerchantEmail())){
                merchantId.set(merchants.getMerchantId());
                flag.set(1);
                return;
            }
        });
        if (flag.get() == 0) {
            Merchant merchantCreated = merchantRepository.save(merchant);
            return merchantCreated.getMerchantId();
        }
        return String.valueOf(merchantId);
    }
}
