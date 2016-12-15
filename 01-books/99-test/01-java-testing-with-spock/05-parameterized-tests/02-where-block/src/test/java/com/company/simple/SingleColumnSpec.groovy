package com.company.simple

import com.company.ImageNameValidator
import spock.lang.Specification

class SingleColumnSpec extends Specification {

    def "Tiff, gif, raw,mov and bmp are invalid extensions (orig)"() {

        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where: "sample image names are"
        pictureFile        || validPicture
        "screenshot.bmp"   || false
        "IMG3434.raw"      || false
        "christmas.mov"    || false
        "sky.tiff"         || false
        "dance_bunny.gif"  || false

    }

    // 5.6 Data tables with one column
    def "Tiff, gif, raw,mov and bmp are invalid extensions"() {

        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        // Output parameter is always false for this test. All images are invalid.
        !validator.isValidImageExtension(pictureFile)

        where: "sample image names are"
        pictureFile        || _
        "screenshot.bmp"   || _
        "IMG3434.raw"      || _
        "christmas.mov"    || _
        "sky.tiff"         || _
        "dance_bunny.gif"  || _

    }

}
