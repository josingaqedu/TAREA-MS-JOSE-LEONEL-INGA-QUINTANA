package com.ms_retrofit.ms_retrofit.service.impl;

import com.ms_retrofit.ms_retrofit.client.ClientCompany;
import com.ms_retrofit.ms_retrofit.client.impl.ClientCompanyServiceImpl;
import com.ms_retrofit.ms_retrofit.entity.CompanyEntity;
import com.ms_retrofit.ms_retrofit.redis.RedisService;
import com.ms_retrofit.ms_retrofit.repository.CompanyRepository;
import com.ms_retrofit.ms_retrofit.service.CompanyService;
import com.ms_retrofit.ms_retrofit.utils.constants.Constants;
import com.ms_retrofit.ms_retrofit.utils.redis.Converter;
import com.ms_retrofit.ms_retrofit.utils.response.ResponseCompany;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final RedisService redisService;

    ClientCompany clientCompany = ClientCompanyServiceImpl.getRetrofit().create(ClientCompany.class);

    @Value("${token.api}")
    private String token;

    public CompanyServiceImpl(CompanyRepository companyRepository, RedisService redisService) {
        this.companyRepository = companyRepository;
        this.redisService = redisService;
    }

    @Override
    public CompanyEntity getCompany(String ruc) {
        return getData(ruc);
    }

    private CompanyEntity getData(String ruc) {
        CompanyEntity companyEntityFinal = null;

        String redisInfo = redisService.getData(Constants.REDIS_KEY_API_COMPANY + ruc);

        if (Objects.nonNull(redisInfo)) {
            companyEntityFinal = Converter.convertToObject(redisInfo, CompanyEntity.class);
        } else {
            Call<ResponseCompany> clientRetrofit = prepareClientRetrofit(ruc);

            Response<ResponseCompany> executeClient = null;

            try {
                executeClient = clientRetrofit.execute();
            } catch (Exception e) {
                throw new RuntimeException("Error getting company data");
            }

            ResponseCompany responseCompany = null;

            if (executeClient.isSuccessful() && Objects.nonNull(executeClient.body())) {
                responseCompany = executeClient.body();
            }

            if (Objects.nonNull(responseCompany)) {
                CompanyEntity companyEntity = saveCompany(ruc, responseCompany);
                String companyString = Converter.convertToString(companyEntity);
                redisService.setData(Constants.REDIS_KEY_API_COMPANY + ruc, companyString, Constants.REDIS_TTL);
                companyEntityFinal = companyEntity;
            }
        }

        return companyEntityFinal;
    }

    private CompanyEntity saveCompany(String ruc, ResponseCompany responseCompany) {
        CompanyEntity existCompany = companyRepository.findByNumeroDocumento(ruc);

        if(Objects.nonNull(existCompany)) {
            // set data
            setDataCompany(existCompany, responseCompany);

            // set audit data
            existCompany.setActualizadoPor(Constants.USER_UPDATED);
            existCompany.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

            // update company and return
            return companyRepository.save(existCompany);
        } else {
            CompanyEntity companyEntity = new CompanyEntity();

            // set data
            setDataCompany(companyEntity, responseCompany);

            // set audit data
            companyEntity.setCreadoPor(Constants.USER_CREATED);
            companyEntity.setFechaCreacion(new Timestamp(System.currentTimeMillis()));

            // save company and return
            return  companyRepository.save(companyEntity);
        }
    }

    private void setDataCompany(CompanyEntity companyEntity, ResponseCompany responseCompany) {
        companyEntity.setRazonSocial(responseCompany.getRazonSocial());
        companyEntity.setTipoDocumento(responseCompany.getTipoDocumento());
        companyEntity.setNumeroDocumento(responseCompany.getNumeroDocumento());
        companyEntity.setEstado(responseCompany.getEstado());
        companyEntity.setCondicion(responseCompany.getCondicion());
        companyEntity.setDireccion(responseCompany.getDireccion());
        companyEntity.setUbigeo(responseCompany.getUbigeo());
        companyEntity.setViaTipo(responseCompany.getViaTipo());
        companyEntity.setViaNombre(responseCompany.getViaNombre());
        companyEntity.setZonaCodigo(responseCompany.getZonaCodigo());
        companyEntity.setZonaTipo(responseCompany.getZonaTipo());
        companyEntity.setNumero(responseCompany.getNumero());
        companyEntity.setInterior(responseCompany.getInterior());
        companyEntity.setLote(responseCompany.getLote());
        companyEntity.setDpto(responseCompany.getDpto());
        companyEntity.setManzana(responseCompany.getManzana());
        companyEntity.setKilometro(responseCompany.getKilometro());
        companyEntity.setDistrito(responseCompany.getDistrito());
        companyEntity.setProvincia(responseCompany.getProvincia());
        companyEntity.setDepartamento(responseCompany.getDepartamento());
        companyEntity.setEsAgenteRetencion(responseCompany.getEsAgenteRetencion());
        companyEntity.setTipo(responseCompany.getTipo());
        companyEntity.setActividadEconomica(responseCompany.getActividadEconomica());
        companyEntity.setNumeroTrabajadores(responseCompany.getNumeroTrabajadores());
        companyEntity.setTipoFacturacion(responseCompany.getTipoFacturacion());
        companyEntity.setTipoContabilidad(responseCompany.getTipoContabilidad());
        companyEntity.setComercioExterior(responseCompany.getComercioExterior());
    }

    private Call<ResponseCompany> prepareClientRetrofit(String ruc) {
        String bearerToken = Constants.BEARER + token;
        return clientCompany.getCompany(bearerToken, ruc);
    }
}
