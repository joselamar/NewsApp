package lamarao.jose.newsapp

import io.kotest.matchers.shouldBe
import java.util.stream.Stream
import lamarao.jose.newsapp.ui.common.removeCopyrightAndCaption
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class CheckRemoveCopyrightAndCaption {

  @ParameterizedTest
  @MethodSource("stringArguments")
  fun verifyCopyrightAndCaptionOutputs(testString: String, expectedString: String) {
    removeCopyrightAndCaption(testString) shouldBe expectedString
  }

  companion object {
    @JvmStatic
    fun stringArguments(): Stream<Arguments> {
      return Stream.of(
          Arguments.of(
              "Biden arrived in Geneva on TuesdayImage caption: Biden arrived in Geneva on Tuesday\r\nWell be bringing you the latest\r\ndevelopments as they happen - and this is how we expect the day to pan out. Thing… [+1239 chars]",
              "Well be bringing you the latest\rdevelopments as they happen - and this is how we expect the day to pan out. Thing… [+1239 chars]"),
          Arguments.of(
              "media caption'A celebration of life. A celebration of freedom': What you need to know about Juneteenth\r\nThe US Senate has unanimously passed a bill to make Juneteenth, the day that marks the end of s… [+2113 chars]",
              "The US Senate has unanimously passed a bill to make Juneteenth, the day that marks the end of s… [+2113 chars]"),
          Arguments.of(
              "image copyrightGetty Images\r\nAirbnb paid a tourist \$7m (£5m) after she was allegedly raped at knifepoint at a rental property in New York City, according to media reports.\r\nBloomberg News reported an… [+1804 chars]",
              "Airbnb paid a tourist \$7m (£5m) after she was allegedly raped at knifepoint at a rental property in New York City, according to media reports.\rBloomberg News reported an… [+1804 chars]"),
          Arguments.of(
              "By Michelle RobertsHealth editor, BBC News online\r\nExactly a … [+2678 chars]",
              "Exactly a … [+2678 chars]"),
          Arguments.of(
              "image copyrightGetty Images\r\nimage captionImages showed smoke and fire as strikes hit in Gaza City\r\nIsrael says it has launched air strikes against Hamas targets in the Gaza Strip, after incendiary b… [+1536 chars]",
              "Israel says it has launched air strikes against Hamas targets in the Gaza Strip, after incendiary b… [+1536 chars]"),
          Arguments.of(
              "By Laura BickerBBC News, Seoul\r\nimage copyrightGetty Images\r\nimage captionMore than 30,000 cases of filming with the use of hidden cameras were reported to police between 2013 and 2018\r\nKyung-mi (not… [+9354 chars]",
              "Kyung-mi (not… [+9354 chars]"),
          Arguments.of(
              "Presidents Biden and Putin have arrived in Geneva for their first summit, with US-Russian relations under severe strain.\r\nThey were seen shaking hands ahead of the talks.",
              "Presidents Biden and Putin have arrived in Geneva for their first summit, with US-Russian relations under severe strain.\r\nThey were seen shaking hands ahead of the talks."))
    }
  }
}
