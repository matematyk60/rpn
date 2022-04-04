package io.matematyk60.rpn

import enumeratum.{Enum, EnumEntry}

import scala.annotation.tailrec

object Rpn {

  def evaluate(expression: String): Double =
    evaluateRec(stack = List.empty, terms = expression.split(" ").toList)

  @tailrec
  private def evaluateRec(stack: List[Double], terms: List[String]): Double = terms match {
    case Nil                                           => stack.head
    case term :: tail if term.toDoubleOption.isDefined => evaluateRec(term.toDouble :: stack, tail)
    case opTerm :: termTail                            =>
      val operator = Operator.values
        .find(_.term == opTerm)
        .getOrElse(throw new IllegalArgumentException(s"Unexpected term [$opTerm]"))

      operator match {
        case operator: Operator.BinaryOperator =>
          val x1 :: x2 :: tail = stack
          val result           = operator.calc(x1, x2)
          evaluateRec(result :: tail, termTail)

        case operator: Operator.NAryOperator =>
          val result = operator.calc(stack)
          evaluateRec(result :: Nil, termTail)
      }
  }
}

sealed trait Operator extends EnumEntry {
  val term: String
}

object Operator extends Enum[Operator] {
  override def values: IndexedSeq[Operator] = findValues

  sealed trait BinaryOperator extends Operator {
    def calc(x1: Double, x2: Double): Double
  }

  case object Add      extends BinaryOperator {
    override def calc(x1: Double, x2: Double): Double = x2 + x1
    override val term: String                         = "+"
  }
  case object Subtract extends BinaryOperator {
    override def calc(x1: Double, x2: Double): Double = x2 - x1
    override val term: String                         = "-"
  }
  case object Multiply extends BinaryOperator {
    override def calc(x1: Double, x2: Double): Double = x2 * x1
    override val term: String                         = "*"
  }
  case object Divide   extends BinaryOperator {
    override def calc(x1: Double, x2: Double): Double = x2 / x1
    override val term: String                         = "/"
  }

  sealed trait NAryOperator extends Operator {
    def calc(xs: List[Double]): Double
  }

  case object Max extends NAryOperator {
    override def calc(xs: List[Double]): Double = xs.max
    override val term: String                   = "max"
  }
}
