# Currency Converter - Codex Challange

## Installation

To be able to run the project you must have installed Java 11 and Redis in your machine.

Once you have done that, you will proceed the following command into source folder:

`.\mvnw spring-boot:run`

P.S: Wheter you have credentials to access your redis service, you must provide it into application.properties.

## Usage

The endpoint is available on:
`localhost:8080/currency/convert`

You must provide the following query params:

- dataCotacao: MM-dd-YYYY
- moedaOrigem
- moedaDestino
- valor: deve ser um valor double

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
