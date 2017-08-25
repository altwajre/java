package com.company

import com.company.simple.Monitor
import com.company.simple.Reactor
import spock.lang.Specification

class ImprovedCoolantSensorSpec extends Specification {
    def "Mock test"(){
        given:
        Reactor reactor = Mock(Reactor)
        Monitor monitor = new Monitor(reactor: reactor)

        when:
        monitor.readSensor(true)
        monitor.readSensor(true)

        monitor.readSensor(false)

        then:
        // * - after this test is finished, expect the number of times the shutdownReactor() method was called is zero
        2 * reactor.pass()
        1 * reactor.fail()
    }

    def "If current temperature difference is within limits everything is ok"(){
        given: "that temperature readings are within limits"
        TemperatureReadings prev = new TemperatureReadings(sensor1Data:20,sensor2Data:40,sensor3Data:80)
        TemperatureReadings current = new TemperatureReadings(sensor1Data:30,sensor2Data:45,sensor3Data:73);
        TemperatureReader reader = Stub(TemperatureReader)

        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl control = Mock(ReactorControl)
        ImprovedTemperatureMonitor monitor = new ImprovedTemperatureMonitor(reader, control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "everything should be ok"
        // * - after this test is finished, expect the number of times the shutdownReactor() method was called is zero
        0 * control.shutdownReactor()
        0 * control.activateAlarm()
    }

    def "If current temperature difference is more than 20 degrees the alarm sounds"() {
        given: "that temperature readings are not within limits"
        TemperatureReadings prev = new TemperatureReadings(sensor1Data:20,sensor2Data:40,sensor3Data:80)
        TemperatureReadings current = new TemperatureReadings(sensor1Data:30,sensor2Data:10,sensor3Data:73);
        TemperatureReader reader = Stub(TemperatureReader)

        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl control = Mock(ReactorControl)
        ImprovedTemperatureMonitor monitor = new ImprovedTemperatureMonitor(reader, control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should sound"
        // * - after this test is finished, expect the number of times the shutdownReactor() method was called is zero
        0 * control.shutdownReactor()
        1 * control.activateAlarm()
    }

    def "If current temperature difference is more than 50 degrees the reactor shuts down"() {
        given: "that temperature readings are not within limits"
        TemperatureReadings prev = new TemperatureReadings(sensor1Data:20,sensor2Data:40,sensor3Data:80)
        TemperatureReadings current = new TemperatureReadings(sensor1Data:30,sensor2Data:10,sensor3Data:160);
        TemperatureReader reader = Stub(TemperatureReader)

        reader.getCurrentReadings() >>> [prev, current]

        ReactorControl control = Mock(ReactorControl)
        ImprovedTemperatureMonitor monitor = new ImprovedTemperatureMonitor(reader, control)

        when: "we ask the status of temperature control"
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should sound"
        // * - after this test is finished, expect the number of times the shutdownReactor() method was called is zero
        1 * control.shutdownReactor()
        1 * control.activateAlarm()
    }

    def "Testing of all 3 sensors with temperatures that rize and fail"(){

        given: "various temperature readings"
        // Input temperature with parameters
        TemperatureReadings prev =
                new TemperatureReadings(sensor1Data:previousTemp[0], sensor2Data:previousTemp[1], sensor3Data:previousTemp[2])
        TemperatureReadings current =
                new TemperatureReadings(sensor1Data:currentTemp[0], sensor2Data:currentTemp[1], sensor3Data:currentTemp[2]);

        TemperatureReader reader = Stub(TemperatureReader) // Creation of dummy interface

        reader.getCurrentReadings() >>> [prev, current] // Instrumenting return value of interface

        ReactorControl control = Mock(ReactorControl)
        // Class under test is injected with mock and stub
        ImprovedTemperatureMonitor monitor = new ImprovedTemperatureMonitor(reader,control)

        when: "we ask the status of temperature control"
        // Class under test calls stub and mock behind the scenes
        monitor.readSensor()
        monitor.readSensor()

        then: "the alarm should sound and the reactor should shut down if needed"
        // Verification of mock using parameters
        shutDown * control.shutdownReactor()
        alarm * control.activateAlarm()

        where: "possible temperatures are:"
        // pass array as parameter
        previousTemp | currentTemp       ||  alarm | shutDown
        [20, 30, 40]| [25, 15, 43.2]     ||     0  | 0
        [20, 30, 40]| [13.3, 37.8, 39.2] ||     0  | 0
        [20, 30, 40]| [50, 15, 43.2]     ||     1  | 0
        [20, 30, 40]| [-20, 15, 43.2]    ||     1  | 0
        [20, 30, 40]| [100, 15, 43.2]    ||     1  | 1
        [20, 30, 40]| [-80, 15, 43.2]    ||     1  | 1
        [20, 30, 40]| [20, 55, 43.2]     ||     1  | 0
        [20, 30, 40]| [20, 8  , 43.2]    ||     1  | 0
        [20, 30, 40]| [21, 100, 43.2]    ||     1  | 1
        [20, 30, 40]| [22, -40, 43.2]    ||     1  | 1
        [20, 30, 40]| [20, 35, 76]       ||     1  | 0
        [20, 30, 40]| [20, 31  ,13.2]    ||     1  | 0
        [20, 30, 40]| [21, 33, 97]       ||     1  | 1
        [20, 30, 40]| [22, 39, -22]      ||     1  | 1
    }
}
