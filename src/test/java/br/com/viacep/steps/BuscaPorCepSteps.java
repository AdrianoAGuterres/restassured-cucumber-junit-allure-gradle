package br.com.viacep.steps;

import br.com.viacep.model.UrlPath;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.*;

public class BuscaPorCepSteps {

    private static RequestSpecification request;

    private void definir_path(){
        request = null;
        baseURI = UrlPath.BASE_URI;
        basePath = UrlPath.CEP_PATH;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Dado("^que o usuario insere o CEP \"(.*?)\" e é válido$")
    public void que_o_usuario_insere_o_CEP_e_eh_válido(String cep) {
        definir_path();
        request = given().pathParam("CEP", cep.replace("-", ""));
        int statusCodeActual = request.given().get().statusCode();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCodeActual);
    }

    @Dado("que o usuario iserir um CEP que não exista na base dos Correios")
    public void que_o_usuario_iserir_um_CEP_que_nao_exista_na_base_dos_correios() {
        definir_path();
        request = given().pathParam("CEP", "11111111");
        int statusCodeActual = request.given().get().statusCode();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCodeActual);
    }

    @Dado("que o usuario iserir um CEP com formato inválido")
    public void que_o_usuario_iserir_um_CEP_com_formato_invalido() {
        definir_path();
        request = given().pathParam("CEP", "84814aaa");
    }

    @Quando("o servico é consultado e retorna os dados em formato JSON")
    public void o_servico_eh_consultado_e_retorna_os_dados_em_formato_JSON() {
        request.then().contentType(ContentType.JSON);
    }

    @Quando("o servico é consultado e retorna em formato HTML")
    public void o_servico_eh_consultado_e_retorna_em_formato_HTML() {
        request.then().contentType(ContentType.HTML);
    }

    @Então("contém os dados \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\", \"(.*?)\" e \"(.*?)\"$")
    public void contem_os_dados_e_CEP_logradouro_complemento_bairro_localidade_uf_e_ibge(String cep,
                                                                                         String logradouro,
                                                                                         String complemento,
                                                                                         String bairro,
                                                                                         String localidade,
                                                                                         String uf,
                                                                                         String ibge) {
        JsonPath jsonPath = request.log().all().get().body().jsonPath();

        Assert.assertEquals(cep, jsonPath.get("cep"));
        Assert.assertEquals(logradouro, jsonPath.get("logradouro"));
        Assert.assertEquals(complemento, jsonPath.get("complemento"));
        Assert.assertEquals(bairro, jsonPath.get("bairro"));
        Assert.assertEquals(localidade, jsonPath.get("localidade"));
        Assert.assertEquals(uf, jsonPath.get("uf"));
        Assert.assertEquals(ibge, jsonPath.get("ibge"));
    }

    @Então("é retornado um atributo de erro")
    public void eh_retornado_um_atributo_de_erro() {
       boolean actual = request.log().all().get().body().jsonPath().get("erro");

       Assert.assertTrue(actual);
    }

    @Então("é retornado uma mensagem de erro")
    public void eh_retornado_uma_mensagem_de_erro() {
        int statusCodeActual = request.given().get().statusCode();

        Assert.assertEquals(HttpURLConnection.HTTP_BAD_REQUEST, statusCodeActual);
    }
}
