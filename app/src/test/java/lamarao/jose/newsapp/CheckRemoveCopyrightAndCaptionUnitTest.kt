package lamarao.jose.newsapp

import org.junit.Assert.assertEquals
import org.junit.Test

class CheckRemoveCopyrightAndCaptionUnitTest {

  private val stringWithImageCaption =
      "Biden arrived in Geneva on TuesdayImage caption: Biden arrived in Geneva on Tuesday\r\nWell be bringing you the latest\r\ndevelopments as they happen - and this is how we expect the day to pan out. Thing… [+1239 chars]"
  private val expectedStringWithoutImageCaption =
      "Well be bringing you the latest\rdevelopments as they happen - and this is how we expect the day to pan out. Thing… [+1239 chars]"
  private val stringWithMediaCaption =
      "media caption'A celebration of life. A celebration of freedom': What you need to know about Juneteenth\r\nThe US Senate has unanimously passed a bill to make Juneteenth, the day that marks the end of s… [+2113 chars]"
  private val expectedStringWithoutMediaCaption =
      "The US Senate has unanimously passed a bill to make Juneteenth, the day that marks the end of s… [+2113 chars]"
  private val stringWithImageCopyright =
      "image copyrightGetty Images\r\nAirbnb paid a tourist \$7m (£5m) after she was allegedly raped at knifepoint at a rental property in New York City, according to media reports.\r\nBloomberg News reported an… [+1804 chars]"
  private val expectedStringWithoutImageCopyright =
      "Airbnb paid a tourist \$7m (£5m) after she was allegedly raped at knifepoint at a rental property in New York City, according to media reports.\rBloomberg News reported an… [+1804 chars]"
  private val stringWithAuthor =
      "By Michelle RobertsHealth editor, BBC News online\r\nExactly a … [+2678 chars]"
  private val expectedStringWithoutAuthor = "Exactly a … [+2678 chars]"
  private val stringWithImageCaptionAndImageCopyright =
      "image copyrightGetty Images\r\nimage captionImages showed smoke and fire as strikes hit in Gaza City\r\nIsrael says it has launched air strikes against Hamas targets in the Gaza Strip, after incendiary b… [+1536 chars]"
  private val expectedStringWithImageCaptionAndImageCopyright =
      "Israel says it has launched air strikes against Hamas targets in the Gaza Strip, after incendiary b… [+1536 chars]"
  private val stringWithImageCaptionAndImageCopyrightAndAuthor =
      "By Laura BickerBBC News, Seoul\r\nimage copyrightGetty Images\r\nimage captionMore than 30,000 cases of filming with the use of hidden cameras were reported to police between 2013 and 2018\r\nKyung-mi (not… [+9354 chars]"
  private val expectedStringWithImageCaptionAndImageCopyrightAndAuthor =
      "Kyung-mi (not… [+9354 chars]"
  private val normalString =
      "Presidents Biden and Putin have arrived in Geneva for their first summit, with US-Russian relations under severe strain.\r\nThey were seen shaking hands ahead of the talks."
  private val expectedNormalString =
      "Presidents Biden and Putin have arrived in Geneva for their first summit, with US-Russian relations under severe strain.\r\nThey were seen shaking hands ahead of the talks."

  @Test
  fun checkIfRemovesImageCaption() {
    val actualString = removeCopyrightAndCaption(stringWithImageCaption)
    assertEquals(expectedStringWithoutImageCaption, actualString)
  }

  @Test
  fun checkIfRemovesMediaCaption() {
    val actualString = removeCopyrightAndCaption(stringWithMediaCaption)
    assertEquals(expectedStringWithoutMediaCaption, actualString)
  }

  @Test
  fun checkIfRemovesImageCopyright() {
    val actualString = removeCopyrightAndCaption(stringWithImageCopyright)
    assertEquals(expectedStringWithoutImageCopyright, actualString)
  }

  @Test
  fun checkIfRemovesAuthor() {
    val actualString = removeCopyrightAndCaption(stringWithAuthor)
    assertEquals(expectedStringWithoutAuthor, actualString)
  }

  @Test
  fun checkIfRemoveImageCaptionAndImageCopyright() {
    val actualString = removeCopyrightAndCaption(stringWithImageCaptionAndImageCopyright)
    assertEquals(expectedStringWithImageCaptionAndImageCopyright, actualString)
  }

  @Test
  fun checkIfRemoveImageCaptionAndImageCopyrightAndAuthor() {
    val actualString = removeCopyrightAndCaption(stringWithImageCaptionAndImageCopyrightAndAuthor)
    assertEquals(expectedStringWithImageCaptionAndImageCopyrightAndAuthor, actualString)
  }

  @Test
  fun checkNormalString() {
    val actualString = removeCopyrightAndCaption(normalString)
    assertEquals(expectedNormalString, actualString)
  }
}
