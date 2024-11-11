package com.ms_open_feign.ms_open_feign.client;

import com.ms_open_feign.ms_open_feign.utils.response.ResponseCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-company", url = "https://api.apis.net.pe/v2/sunat")
public interface ClientCompany {
    @GetMapping("/ruc/full")
    ResponseCompany getCompanyOfClient(@RequestHeader(name = "Authorization") String authorization, @RequestParam(name = "numero") String numero);
}
