package br.com.viacep.model;

public interface UrlPath {
    String BASE_URI = "https://viacep.com.br/";
    String CEP_PATH = "ws/{CEP}/json/";
    String ENDERECO_PATH = "ws/{UF}/{CIDADE}/{LOGRADOURO}/json/";
}
