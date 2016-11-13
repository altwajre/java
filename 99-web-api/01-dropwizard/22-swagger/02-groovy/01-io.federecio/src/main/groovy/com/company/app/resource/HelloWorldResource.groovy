package com.company.app.resource

import com.company.app.model.CalculatorMessage
import com.company.app.provider.CalculatorProvider
import com.company.app.provider.FizzBuzzProvider
import com.google.inject.Inject
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import com.wordnik.swagger.annotations.ApiParam

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path('/helloWorld')
@Api('/helloWorld')
@Produces(MediaType.APPLICATION_JSON)
class HelloWorldResource {

    @Inject
    FizzBuzzProvider fizzBuzzProvider

    @Inject
    CalculatorProvider calculatorProvider

    @GET
    @ApiOperation(value = 'list of all the hello world examples')
    def helloWorldExamples() {
        [helloWorldExamples: ['FizzBuzz', 'Calculator']]
    }

    @GET
    @Path('/fizzBuzz')
    @ApiOperation(value = 'counts up to n')
    def fizzBuzz(@QueryParam('countTo') int countTo) {
        [countUpTo: countTo, counter: fizzBuzzProvider.count(countTo)]
    }

    @POST
    @Path('/calculator')
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = 'adds two numbers')
    def calculator(@ApiParam @Valid CalculatorMessage calculatorMessage) {
        [operation: calculatorMessage.operation.value,
         values: calculatorMessage.firstNumber + ' and ' + calculatorMessage.secondNumber,
         answer: calculatorProvider.calculate(calculatorMessage)]
    }
}
