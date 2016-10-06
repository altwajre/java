package com.company

import spock.lang.Specification

class SimpleParameterizedTest extends Specification {

    // 5.2 Simple Spock parameterized test
    def "Valid images are PNG and JPEG files"(){
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator() // Class under test

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where: "sample image names are" // where: block contains parameters for multiple scenarios
        // Input and expected output for each scenario in each line
        pictureFile       || validPicture // First line of block is always the names of the parameters
        "scenery.jpg"     || true
        "house.jpeg"      || true
        "car.png"         || true
        "sky.tiff"        || false
        "dance_bunny.gif" || false
    }

}
