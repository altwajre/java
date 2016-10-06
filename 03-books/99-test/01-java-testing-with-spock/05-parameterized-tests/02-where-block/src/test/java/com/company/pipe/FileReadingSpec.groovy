package com.company.pipe

import com.company.ImageNameValidator
import spock.lang.Specification
import spock.lang.Unroll

class FileReadingSpec extends Specification {

    // 5.18 Using existing data generators
    @Unroll("Checking image name #pictureFile")
    def "Valid images are PNG and JPEG files"(){

        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that all filenames are accepted"
        validator.isValidImageExtension(pictureFile)

        where: "sample image names are"
        pictureFile << new File("src/test/resources/validImageNames.txt").readLines()

    }
}
