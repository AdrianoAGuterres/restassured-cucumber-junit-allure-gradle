package br.com.viacep.steps;

import br.com.viacep.model.Endereco;
import br.com.viacep.model.UrlPath;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.HttpURLConnection;
import java.util.List;

import static io.restassured.RestAssured.*;

public class BuscaPorEnderecoSteps {


    private static RequestSpecification request;
    private static List<Endereco> enderecosList;

    @Dado("que o usuario iserir RS, Gravatai e Barroso")
    public void que_o_usuario_iserir_RS_Gravatai_e_Barroso() {
        request = null;
        enderecosList = null;

        baseURI = UrlPath.BASE_URI;
        basePath = UrlPath.ENDERECO_PATH;

        request = given()
                .pathParam("UF", "RS")
                .pathParam("CIDADE", "Gravatai")
                .pathParam("LOGRADOURO", "Barroso");

        int statusCodeActual = request.given().get().statusCode();

        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCodeActual);
    }

    @Então("é retornado uma lista com 2 resultados")
    public void eh_retornado_uma_lista_com_2_resultados() {
        enderecosList = request.get().body().jsonPath().getList("", Endereco.class);
        int quantidade_atual = enderecosList.size();

        Assert.assertEquals(2, quantidade_atual);
    }

    @Então("o primeiro resultado contém os dados 94085-170, Rua Ari Barroso, Morada do Vale I, Gravataí, RS e 4309209")
    public void o_primeiro_resultado_contem_os_dados_Rua_Ari_Barroso_Morada_do_Vale_I_Gravataí_RS_e() {
        Endereco endereco = enderecosList.get(0);

        String cepExpected = "94085-170";
        String logradouroExpected = "Rua Ari Barroso";
        String complementoExpected = "";
        String bairroExpected = "Morada do Vale I";
        String localidadeExpected = "Gravataí";
        String ufExpected = "RS";
        String ibgeExpected = "4309209";

        Assert.assertEquals(cepExpected, endereco.getCep());
        Assert.assertEquals(logradouroExpected, endereco.getLogradouro());
        Assert.assertEquals(complementoExpected, endereco.getComplemento());
        Assert.assertEquals(bairroExpected, endereco.getBairro());
        Assert.assertEquals(localidadeExpected, endereco.getLocalidade());
        Assert.assertEquals(ufExpected, endereco.getUf());
        Assert.assertEquals(ibgeExpected, endereco.getIbge());
    }

    @Então("o segundo resultado contém os dados 94175-000, Rua Almirante Barroso, Recanto Corcunda, Gravataí, RS e 4309209")
    public void o_segundo_resultado_contem_os_dados_Rua_Almirante_Barroso_Recanto_Corcunda_Gravataí_RS_e() {
        Endereco endereco = enderecosList.get(1);

        String cepExpected = "94175-000";
        String logradouroExpected = "Rua Almirante Barroso";
        String complementoExpected = "";
        String bairroExpected = "Recanto Corcunda";
        String localidadeExpected = "Gravataí";
        String ufExpected = "RS";
        String ibgeExpected = "4309209";

        Assert.assertEquals(cepExpected, endereco.getCep());
        Assert.assertEquals(logradouroExpected, endereco.getLogradouro());
        Assert.assertEquals(complementoExpected, endereco.getComplemento());
        Assert.assertEquals(bairroExpected, endereco.getBairro());
        Assert.assertEquals(localidadeExpected, endereco.getLocalidade());
        Assert.assertEquals(ufExpected, endereco.getUf());
        Assert.assertEquals(ibgeExpected, endereco.getIbge());
    }


}
