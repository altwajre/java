package com.company

import com.company.simple.ITest
import com.company.simple.Result
import spock.lang.Specification

class CoolantSensorSpec extends Specification {

    def "ITest simple Stub()"() {
        given:
        Result pass = new Result(pass: true)
        Result fail = new Result(pass: false)

        ITest test = Stub(ITest)

        // >>> operator
        // first time test.run() is called returns pass
        // second time test.run() is called returns false
        test.run() >>> [pass, fail]

        when:
        println test.run().pass // first time pass; should see true
        println test.run().pass // second time fail; should see false

        then:
        test
    }

    def "If current temperature difference is within limits everything is ok"(){
        given: "that temperature readings are within limits"
        // Premade temperature readings
        TemperatureReadings prev = new TemperatureReadings(sensor1Data: 20, sensor2Data: 40, sensor3Data: 80)
        TemperatureReadings current = new TemperatureReadings(sensor1Data: 30, sensor2Data: 45, sensor3Data: 73)

        TemperatureReader reader = Stub(TemperatureReader) // Dummy interface implementation

        reader.getCurrentReadings() >>> [prev, current] // Instructing the dummy interface to return premade readings

        TemperatureMonitor monitor = new TemperatureMonitor(reader) // Class under test is injected with dummy interface

        when: "we ask the status of temperature control"
        // Class under test calls dummy interface
        monitor.readSensor()
        monitor.readSensor()

        then: "everything should be ok"
        // Assertion after two subsequent calls
        monitor.isTemperatureNormal()
    }

    def "If current temperature difference is more than 20 degrees the alarm should sound"(){
        given: "that temperature readings are not within limits"
        TemperatureReadings prev = new TemperatureReadings(sensor1Data: 20, sensor2Data: 40, sensor3Data: 80)
        TemperatureReadings current = new TemperatureReadings(sensor1Data: 30, sensor2Data: 10, sensor3Data: 73)

        TemperatureReader reader = Stub(TemperatureReader)

        reader.getCurrentReadings() >>> [prev, current]
        TemperatureMonitor monitor = new TemperatureMonitor(reader)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should sound"
        !monitor.isTemperatureNormal()
    }
}
