package io.mockapi;

import io.mockapi.util.HttpCodes;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCarrinhoComprasApi {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://5d9cc58566d00400145c9ed4.mockapi.io";

    }

    @Test
    @Description("Verificar os nomes dos produtos contidos no carrinho")
    public void verificarNomesItensNoCarrinho() {
        String nomePrimeiroItemEsperado = "demo_2";
        String nomeSegundoItemEsperado = "demo_1";
        String nomeTerceiroItemEsperado = "demo_7";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("sku[0]", is(nomePrimeiroItemEsperado))
                .body("sku[1]", is(nomeSegundoItemEsperado))
                .body("sku[2]", is(nomeTerceiroItemEsperado));
    }

    @Test
    @Description("Verificar a quantidade de itens que tem no carrinho")
    public void verificarQuantidadeItensNoCarrinho() {

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("color", hasSize(3));
    }

    @Test
    @Description("Verificar as cores dos diferentes itens que tem no carrinho")
    public void verificarCoresItensNocarrinho() {
        String corPrimeiroItemEsperada = "Black";
        String corSegundoItemEsperada = "Orange";
        String corTerceiroItemEsperada = "Yellow";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("color[0]", is(corPrimeiroItemEsperada))
                .body("color[1]", is(corSegundoItemEsperada))
                .body("color[2]", is(corTerceiroItemEsperada));
    }

    @Test
    @Description("Verificar os tamanhos dos diferentes itens no carrinho")
    public void verificarTamanhosItensNocarrinho() {
        String tamanhoPrimeiroItemEsperado = "S";
        String tamanhoSegundoItemEsperado = "S";
        String tamanhoTerceiroItemEsperado = "S";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("size[0]", is(tamanhoPrimeiroItemEsperado))
                .body("size[1]", is(tamanhoSegundoItemEsperado))
                .body("size[2]", is(tamanhoTerceiroItemEsperado));
    }

    @Test
    @Description("Verificar o preço de cada item no carrinho")
    public void verificarPrecosItensNocarrinho() {
        double precoPrimeiroItemEsperado = 27.00;
        double precoSegundoItemEsperado = 16.51;
        double precoTerceiroItemEsperado = 16.40;

        List<String> lista =
                given()
                        .when()
                        .get("/shopping_cart")
                        .then()
                        .statusCode(HttpCodes.OK)
                        .extract()
                        .path("price");

        assertEquals(Double.parseDouble(lista.get(0)), precoPrimeiroItemEsperado);
        assertEquals(Double.parseDouble(lista.get(1)), precoSegundoItemEsperado);
        assertEquals(Double.parseDouble(lista.get(2)), precoTerceiroItemEsperado);
    }

    @Test
    @Description("Verificar a taxa de envio para os produtos contidos no carrinho")
    public void verificarTaxaDeEnvioDosItens() {
        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("total_shipping as double", is(2.0));
    }

    @Test
    @Description("Verificar a soma total dos itens no carrinho")
    public void verificarValorTotalDosProdutos() {
        double totalEsperado = 59.91f;
        double totalObtido = 0.0;
        List<String> list =
                given()
                        .when()
                        .get("/shopping_cart")
                        .then()
                        .statusCode(HttpCodes.OK)
                        .extract()
                        .path("price");

        for (String price : list) {
            totalObtido += Double.parseDouble(price);
        }
        assertEquals(totalEsperado, totalObtido, 0.01);
    }

    @Test
    @Description("Verificar os dados referente ao primeiro item no carrinho")
    public void verificarDadosPrimeiroItemNoCarrinho() {
        String nomePrimeiroItemEsperado = "demo_2";
        String corPrimeiroItemEsperado = "Black";
        String tamanhoPrimeiroItemEsperado = "S";
        String precoPrimeiroItemEsperado = "27.00";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("sku[0]", is(nomePrimeiroItemEsperado))
                .body("color[0]", is(corPrimeiroItemEsperado))
                .body("size[0]", is(tamanhoPrimeiroItemEsperado))
                .body("price[0]", is(precoPrimeiroItemEsperado));
    }

    @Test
    @Description("Verificar os dados referente ao segundo item no carrinho")
    public void verificarDadosSegundoItemNoCarrinho() {
        String nomeSegundoItemEsperado = "demo_1";
        String corSegundoItemEsperado = "Orange";
        String tamanhoSegundoItemEsperado = "S";
        String precoSegundoItemEsperado = "16.51";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("sku[1]", is(nomeSegundoItemEsperado))
                .body("color[1]", is(corSegundoItemEsperado))
                .body("size[1]", is(tamanhoSegundoItemEsperado))
                .body("price[1]", is(precoSegundoItemEsperado));
    }

    @Test
    @Description("Verificar os dados referente ao terceiro item no carrinho")
    public void verificarDadosTerceiroItemNoCarrinho() {
        String nomeTerceiroItemEsperado = "demo_7";
        String corTerceiroItemEsperado = "Yellow";
        String tamanhoTerceiroItemEsperado = "S";
        String precoTerceiroItemEsperado = "16.40";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("sku[2]", is(nomeTerceiroItemEsperado))
                .body("color[2]", is(corTerceiroItemEsperado))
                .body("size[2]", is(tamanhoTerceiroItemEsperado))
                .body("price[2]", is(precoTerceiroItemEsperado));
    }

    @Test
    @Description("Verificar qual é o maior preço dos itens no carrinho")
    public void verificarMaiorPrecoNoCarrinho() {
        String maiorPrecoEsperado = "27.00";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("price.findAll{it != null}.max()", is(maiorPrecoEsperado));
    }

    @Test
    @Description("Verificar qual é o menor preço dos itens no carrinho")
    public void verificarMenorPrecoNoCarrinho() {
        String menorPrecoEsperado = "16.40";

        given()
                .when()
                .get("/shopping_cart")
                .then()
                .statusCode(HttpCodes.OK)
                .body("price.findAll{it != null}.min()", is(menorPrecoEsperado));
    }

}
