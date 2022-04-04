package io.matematyk60.rpn

class RpnTest extends munit.FunSuite {

  private val delta = 0.05

  test("""Rpn.evaluate `-4 5 +` """) {
    val expression = "-4 5 +"

    assertEqualsDouble(Rpn.evaluate(expression), 1.0, delta)
  }

  test("""Rpn.evaluate `5 2 /` """) {
    val expression = "5 2 /"

    assertEqualsDouble(Rpn.evaluate(expression), 2.5, delta)
  }

  test("""Rpn.evaluate `5 2.5 /` """) {
    val expression = "5 2.5 /"

    assertEqualsDouble(Rpn.evaluate(expression), 2.0, delta)
  }

  test("""Rpn.evaluate `5 1 2 + 4 * 3 - +` """) {
    val expression = "5 1 2 + 4 * 3 - +"

    assertEqualsDouble(Rpn.evaluate(expression), 14.0, delta)
  }

  test("""Rpn.evaluate `4 2 5 * + 1 3 2 * + /` """) {
    val expression = "4 2 5 * + 1 3 2 * + /"

    assertEqualsDouble(Rpn.evaluate(expression), 2.0, delta)
  }

  test("""Rpn.evaluate `4 2 5 * + 1 3 2 * max`""") {
    val expression = "4 2 5 * + 1 3 2 * max"

    assertEqualsDouble(Rpn.evaluate(expression), 14, delta)
  }

  test("All operators should have unique terms") {
    val allOperators         = Operator.values.map(_.term)
    val allDistinctOperators = Operator.values.map(_.term).distinct

    assertEquals(allOperators, allDistinctOperators)
  }

}
