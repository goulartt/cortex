# Currency Converter - Codex Challange

This applications consumes an API from Banco Central do Brasil. The documentation for this external API is available [here](https://dadosabertos.bcb.gov.br/dataset/taxas-de-cambio-todos-os-boletins-diarios).

## Installation

To be able to run the project you must have installed Java 11 and Redis in your machine.

Once you have done that, you will proceed the following command into source folder:

`.\mvnw spring-boot:run`

P.S: Wheter you have credentials to access your redis service, you must provide it into application.properties.

## Usage

The GET endpoint is available on:
`localhost:8080/currency/convert`

You must provide the following query params:

- dataCotacao: MM-dd-YYYY
- moedaOrigem
- moedaDestino
- valor: must be a double value

The available coins to use are:
DKK,
NOK,
SEK,
USD,
AUD,
CAD,
EUR,
CHF,
JPY,
GBP,
BRL
